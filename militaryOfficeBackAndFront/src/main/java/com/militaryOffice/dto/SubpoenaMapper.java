package com.militaryOffice.dto;

import com.militaryOffice.model.Subpoena;
import com.militaryOffice.services.CitizenService;
import com.militaryOffice.services.MilitaryOfficeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SubpoenaMapper {

    private final MilitaryOfficeService militaryOfficeService;
    private final CitizenService citizenService;

    public SubpoenaDto mapToSubpoenaDto(Subpoena subpoena){
        ModelMapper modelMapper = new ModelMapper();
        SubpoenaDto subpoenaDto = modelMapper.map(subpoena, SubpoenaDto.class);
        subpoenaDto.setUserId(subpoena.getIdUser().getId());
        subpoenaDto.setMilitaryOfficeId(subpoena.getMilitaryOffice().getId());
        return subpoenaDto;
    }

    public Subpoena mapToSubpoena(SubpoenaDto subpoenaDto){
        ModelMapper modelMapper = new ModelMapper();
        Subpoena subpoena = modelMapper.map(subpoenaDto, Subpoena.class);
        System.out.println("sssasdasda - " + subpoenaDto);
        subpoena.setIdUser(citizenService.getCitizenById(subpoenaDto.getUserId()));
        subpoena.setMilitaryOffice(militaryOfficeService.getMilitaryOfficeById(subpoenaDto.getMilitaryOfficeId()));
        return subpoena;
    }

}
