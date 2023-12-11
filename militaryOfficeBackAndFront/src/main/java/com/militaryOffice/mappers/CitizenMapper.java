package com.militaryOffice.mappers;


import com.militaryOffice.dto.CitizenDto;
import com.militaryOffice.model.Citizen;
import com.militaryOffice.model.FamilyStatus;
import com.militaryOffice.model.MilitaryStatus;
import com.militaryOffice.services.MilitaryOfficeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CitizenMapper {

    private final MilitaryOfficeService militaryOfficeService;

    public CitizenDto mapToCitizenDto(Citizen citizen){
        ModelMapper modelMapper = new ModelMapper();
        CitizenDto citizenDto = modelMapper.map(citizen, CitizenDto.class);
        System.out.println(citizenDto);
        return citizenDto;
    }

    public Citizen mapToCitizen(CitizenDto citizenDto){
        ModelMapper modelMapper = new ModelMapper();
        Citizen citizen = modelMapper.map(citizenDto, Citizen.class);
        citizen.setMilitaryOffice(militaryOfficeService.getMilitaryOfficeById(citizenDto.getMilitaryOfficeId()));
        citizen.setFamilyStatus(FamilyStatus.getByValue(citizenDto.getFamilyStatus()));
        citizen.setStatus(MilitaryStatus.getByValue(citizenDto.getStatus()));
        return citizen;
    }


}
