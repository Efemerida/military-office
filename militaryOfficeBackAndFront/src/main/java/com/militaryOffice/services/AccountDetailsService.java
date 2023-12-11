package com.militaryOffice.services;

import com.militaryOffice.model.Account;
import com.militaryOffice.repositories.AccountRepository;
import com.militaryOffice.security.AccountDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final SecurityService securityService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByLogin(login);
        if(account.isEmpty()) throw new UsernameNotFoundException("User not found!");
        return new AccountDetails(account.get(), securityService);
    }
}
