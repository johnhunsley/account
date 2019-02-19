package com.hunsley.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hunsley.account.model.Account;
import com.hunsley.account.model.CurrentAccount;
import com.hunsley.account.model.SavingsAccount;
import com.hunsley.account.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Iterator;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("service")
public class AccountServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testSaveAccounts() throws Exception {
        CurrentAccount currentAccount = new CurrentAccount(103.34, 1.50, 400.00);
        SavingsAccount savingsAccount = new SavingsAccount(103.34, 2.5, 200000.00);

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
}
