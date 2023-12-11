package com.militaryOffice.services;

import com.militaryOffice.dto.ServiceDto;
import com.militaryOffice.mappers.ServiceMapper;
import com.militaryOffice.model.Citizen;
import com.militaryOffice.repositories.ServiceRepository;
import com.militaryOffice.security.AccountDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final CitizenService citizenService;
    private final ServiceMapper serviceMapper;


    public List<com.militaryOffice.model.Service> getAllServices(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
        Citizen citizen = citizenService.getCitizen(accountDetails.getAccount().getPassport());
        List<com.militaryOffice.model.Service> services = serviceRepository.findAllByidUser(citizen);
        return services;
    }

    public List<com.militaryOffice.model.Service> getAllServicesById(int id){
        Citizen citizen = citizenService.getCitizenById(id);
        List<com.militaryOffice.model.Service> services = serviceRepository.findAllByidUser(citizen);
        return services;
    }

    public void saveServiceDtoWithIdUSer(ServiceDto serviceDto, int id){
        com.militaryOffice.model.Service service = serviceMapper.mapToServiceDto(serviceDto);
        service.setIdUser(citizenService.getCitizenById(id));
        serviceRepository.save(service);
    }

    public void deleteServiceDto(ServiceDto serviceDto){
        com.militaryOffice.model.Service service = serviceMapper.mapToServiceDto(serviceDto);
        serviceRepository.delete(service);
    }

    public com.militaryOffice.model.Service getServiceById(int id){
        return serviceRepository.findById(id);
    }
}
