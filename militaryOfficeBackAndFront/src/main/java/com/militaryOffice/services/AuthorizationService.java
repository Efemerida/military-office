package com.militaryOffice.services;

import com.militaryOffice.model.Account;
import com.militaryOffice.repositories.AccountRepository;
import com.militaryOffice.repositories.CitizenRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorizationService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final CitizenRepository citizenRepository;

    @Transactional
    public void register(Account account){
        System.out.println("Password is was");
        System.out.println(account);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }


}
