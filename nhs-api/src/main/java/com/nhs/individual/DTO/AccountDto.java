package com.nhs.individual.DTO;

import com.nhs.individual.Constant.AccountProvider;
import com.nhs.individual.Constant.AccountStatus;
import com.nhs.individual.Domain.Account;
import com.nhs.individual.Domain.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

/**
 * DTO for {@link Account}
 */
@AllArgsConstructor
@Getter
@ToString
public class AccountDto  extends Account implements Serializable {
    private Integer id;
    private String username;
    private AccountStatus status;
    private AccountProvider provider;
    private Collection<Role> roles;
    public AccountDto(Account account){
        this.id=account.getId();
        this.username=account.getUsername();
        this.status=account.getStatus();
        this.provider=account.getProvider();
        this.roles=account.getRoles();
    }
}