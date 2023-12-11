package com.militaryOffice.services;

import com.militaryOffice.model.Citizen;
import com.militaryOffice.repositories.CitizenRepository;
import com.militaryOffice.security.AccountDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CitizenService {

    private final CitizenRepository citizenRepository;

    @Transactional
    public Citizen getCitizen(String passport){
        System.out.println(passport);
        Citizen citizen = citizenRepository.findByPassport(passport);
        if(citizen==null)
            throw new IllegalArgumentException();
        return citizen;
    }


    public Citizen getCitizenByAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountDetails accountDetails = (AccountDetails)authentication.getPrincipal();
        return getCitizen(accountDetails.getAccount().getPassport());
    }

    @Transactional
    public List<Citizen> getAllCitizen(){
        return citizenRepository.findAll();
    }

    @Transactional
    public Citizen getCitizenById(int id){
        Optional<Citizen> citizen = citizenRepository.findById(id);
        if(citizen.isEmpty()) throw new IllegalArgumentException();
        return citizen.get();
    }

    @Transactional
    public void save(Citizen citizen) {
        citizenRepository.save(citizen);
    }

    @Transactional
    public void deleteUser(int id){
        Optional<Citizen> citizenOptional = citizenRepository.findById(id);
        if(citizenOptional.isEmpty()) throw new IllegalArgumentException();
        Citizen citizen = citizenOptional.get();
        citizen.setIsArchive(true);
        citizenRepository.save(citizen);
    }
    @Transactional
    public void restoreUser(int id){
        Optional<Citizen> citizenOptional = citizenRepository.findById(id);
        if(citizenOptional.isEmpty()) throw new IllegalArgumentException();
        Citizen citizen = citizenOptional.get();
        citizen.setIsArchive(false);
        citizenRepository.save(citizen);
    }

    public void saveSizeForm(Citizen user, int id) {
        Citizen citizen = getCitizenById(id);
        citizen.setSizeForm(user.getSizeForm());
        citizen.setSizeBelt(user.getSizeBelt());
        citizen.setSizeShoe(user.getSizeShoe());
        citizen.setSizeGlove(user.getSizeGlove());
        citizen.setSizeHeight(user.getSizeHeight());
        citizen.setSizeContraindication(user.getSizeContraindication());
        citizenRepository.save(citizen);
    }
}
