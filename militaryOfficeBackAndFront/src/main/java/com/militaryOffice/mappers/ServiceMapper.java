package com.militaryOffice.mappers;

import com.militaryOffice.dto.ServiceDto;
import com.militaryOffice.model.Service;
import com.militaryOffice.services.PlaceOfDutyService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ServiceMapper {

    private final PlaceOfDutyService placeOfDutyService;

    public ServiceDto mapToServiceDto(Service service){
        ModelMapper modelMapper = new ModelMapper();
        ServiceDto serviceDto = modelMapper.map(service, ServiceDto.class);
        serviceDto.setPlaceId(service.getIdPlace().getId());
        return serviceDto;
    }
    public Service mapToServiceDto(ServiceDto serviceDto){
        ModelMapper modelMapper = new ModelMapper();
        Service service = modelMapper.map(serviceDto, Service.class);
        service.setIdPlace(placeOfDutyService.getPlaceOfDutyById(serviceDto.getPlaceId()));
        return service;
    }


}
