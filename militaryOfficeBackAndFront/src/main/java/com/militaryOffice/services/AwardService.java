package com.militaryOffice.services;

import com.militaryOffice.model.Award;
import com.militaryOffice.model.Citizen;
import com.militaryOffice.repositories.AwardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AwardService {

    private final AwardRepository awardRepository;
    private final CitizenService citizenService;

    @Transactional(readOnly = true)
    public List<Award> getAllAwards(){
        Citizen citizen = citizenService.getCitizenByAuthentication();
        List<Award> awards = awardRepository.findAllByidUser(citizen);
        return awards;
    }

    @Transactional
    public Award findById(int id){
        return awardRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Award> getAllAwardsById(int id){
        Citizen citizen = citizenService.getCitizenById(id);
        return awardRepository.findAllByidUser(citizen);
    }

    @Transactional
    public void delete(Award award){
        awardRepository.delete(award);
    }

    @Transactional
    public void save(Award award) {
        awardRepository.save(award);
    }
}
