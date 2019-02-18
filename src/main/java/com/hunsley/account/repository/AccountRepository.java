package com.hunsley.account.repository;

import com.hunsley.account.model.Account;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * <p>
 *     A REST resource repository which is only active when the 'service' profile is active. This enables the
 *     package to be used as a dependency for the model only
 * </p>
 */
@Profile("service")
@RepositoryRestResource(collectionResourceRel = "accounts", path = "accounts")
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
}
