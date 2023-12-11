package com.militaryOffice.services;

import com.militaryOffice.dto.SubpoenaDto;
import com.militaryOffice.dto.SubpoenaMapper;
import com.militaryOffice.model.Citizen;
import com.militaryOffice.model.Subpoena;
import com.militaryOffice.repositories.SubpoenaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubpoenaService {

    private final SubpoenaRepository subpoenaRepository;
    private final CitizenService citizenService;
    private final SubpoenaMapper subpoenaMapper;

    public List<Subpoena> getAllSubpoena(){
        Citizen citizen = citizenService.getCitizenByAuthentication();
        List<Subpoena> subpoenas = subpoenaRepository.findAllByidUser(citizen);
        return subpoenas;
    }

    public List<Subpoena> getAllSubpoenaByIdUser(int id){
        Citizen citizen = citizenService.getCitizenById(id);
        List<Subpoena> subpoenas = subpoenaRepository.findAllByidUser(citizen);
        return subpoenas;
    }

    public void saveWithUserId(Subpoena subpoena, int id){
        subpoena.setIdUser(citizenService.getCitizenById(id));
        subpoenaRepository.save(subpoena);
    }
    public void deleteSubpoenaDto(SubpoenaDto subpoenaDto){
        subpoenaRepository.delete(subpoenaMapper.mapToSubpoena(subpoenaDto));
    }

    public Subpoena getSubpoenaById(int id) {
        return subpoenaRepository.findById(id);
    }
}
