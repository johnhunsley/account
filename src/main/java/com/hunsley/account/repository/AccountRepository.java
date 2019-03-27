package com.hunsley.account.repository;

import com.hunsley.account.model.Account;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * <p>
 *     A REST resource repository which is only active when the 'service' profile is active. This enables the
 *     package to be used as a dependency for the model only
 * </p>
 */
@Profile("service")
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "accounts", path = "accounts")
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

    /**
     * <p>
     *     GET accounts/search/findByUid?uid=<uid>
     * </p>
     * @param uid
     * @return a list of {@link Account} sub type instances
     */
    List<Account> findByUid(@Param("uid") Integer uid);

    @Query("select a from Account a where a.name like concat('%',:name,'%') order by a.name desc")
    Page<Account> findByName(@Param("name") String name, Pageable pageable);

}
