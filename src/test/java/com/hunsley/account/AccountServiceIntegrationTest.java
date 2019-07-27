package com.hunsley.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hunsley.account.model.Account;
import com.hunsley.account.model.AccountsResponse;
import com.hunsley.account.model.CurrentAccount;
import com.hunsley.account.model.SavingsAccount;
import com.hunsley.account.repository.AccountRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("service")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    final int uid = 10;

    @Test
    public void A_testSaveAccounts() throws Exception {
        CurrentAccount currentAccount = new CurrentAccount(uid, "MyCurrentAccount", 103.34, 1.50, 400.00);
        SavingsAccount savingsAccount = new SavingsAccount(uid, "MySavingsAccount", 103.34, 2.5, 200000.00);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();

        mockMvc.perform(
                post("/accounts")
                        .content(mapper.writeValueAsString(currentAccount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is2xxSuccessful());

        mockMvc.perform(
                post("/accounts")
                        .content(mapper.writeValueAsString(savingsAccount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is2xxSuccessful());

        int count = 0;
        for (Account account : accountRepository.findAll()) {
            System.out.println(account);
            count++;
        }

        assertEquals(2, count);
    }

    /**
     * GET accounts/search/findByUid?uid=<uid>
     */
    @Test
    public void B_testFindAccountsByUid() throws Exception {
        final byte[] responseBody = mockMvc.perform(get("/accounts/search/findByUid").param("uid", Integer.toString(uid)))
                .andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$._embedded.currentAccounts[0].uid").value(uid))
                .andExpect(jsonPath("$._embedded.savingsAccounts[0].uid").value(uid))
                .andReturn().getResponse().getContentAsByteArray();
        ObjectMapper mapper = new ObjectMapper();
        AccountsResponse responsObj = mapper.readValue(responseBody, AccountsResponse.class);
        assertEquals(responsObj.getEmbeddedCurrentAccounts().size(), 1);
        assertEquals(responsObj.getEmbeddedSavingsAccounts().size(), 1);
    }

    /**
     * GET accounts/search/findByUid?uid=<uid>
     */
    @Test
    public void C_testFindAccountsByUid_WithUnownedAccount() throws Exception {
        SavingsAccount savingsAccount = new SavingsAccount(101, "MyBigSavingsAccount", 103.34, 2.5, 200000.00);
        accountRepository.save(savingsAccount);

        CurrentAccount currentAccount = new CurrentAccount(666, "MyLittleCurrentAccount", 103.34, 1.50, 400.00);
        accountRepository.save(currentAccount);

        final byte[] responseBody = mockMvc.perform(get("/accounts/search/findByUid").param("uid", Integer.toString(uid)))
                .andDo(print()).andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsByteArray();
        ObjectMapper mapper = new ObjectMapper();
        AccountsResponse responsObj = mapper.readValue(responseBody, AccountsResponse.class);
        assertEquals(responsObj.getEmbeddedCurrentAccounts().size(), 1);
        assertEquals(responsObj.getEmbeddedSavingsAccounts().size(), 1);
    }

    @Test
    public void D_testPageAccountsByNameQuery() throws Exception {
        final byte[] responseBody = mockMvc.perform(get("/accounts/search/findByName")
                    .param("name", "My")
                    .param("page","0")
                    .param("size", "20"))
                .andDo(print())
                .andReturn().getResponse().getContentAsByteArray();
        ObjectMapper mapper = new ObjectMapper();
        AccountsResponse responsObj = mapper.readValue(responseBody, AccountsResponse.class);
        assertEquals(responsObj.getEmbeddedCurrentAccounts().size(), 2);
        assertEquals(responsObj.getEmbeddedSavingsAccounts().size(), 2);

    }
}
