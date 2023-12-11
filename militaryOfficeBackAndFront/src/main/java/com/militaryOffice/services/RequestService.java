package com.militaryOffice.services;

import com.militaryOffice.mappers.RequestMapper;
import com.militaryOffice.model.*;
import com.militaryOffice.repositories.DocumentRepository;
import com.militaryOffice.repositories.MilitaryOfficeRepository;
import com.militaryOffice.repositories.RequestServiceRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RequestService {

    private final RequestServiceRepository requestServiceRepository;
    private final CitizenService citizenService;
    private final NotificationService notificationService;
    private final MilitaryOfficeRepository militaryOfficeRepository;
    private final EmployeeService employeeService;
    private final DocumentRepository documentRepository;
    private final RequestMapper requestMapper;

    public boolean isActiveChange() {
        List<UserRequest> userRequestList = requestServiceRepository.findAll();
        for(UserRequest userRequest: userRequestList){
            if(userRequest.getIdUser().getId().equals(citizenService.getCitizenByAuthentication().getId())){
                if(userRequest.getType().equals(TypeRequest.CHANGEOFFICE)){
                    if(userRequest.getStatus().equals(UserRequestStatus.PROCESSING)) return true;
                }
            }
        }
        return false;
    }

    public boolean isActiveCertificate() {
        List<UserRequest> userRequestList = requestServiceRepository.findAll();
        for(UserRequest userRequest: userRequestList){
            System.out.println(userRequest.getType() + "-----" + userRequest.getStatus());
            if(userRequest.getIdUser().getId().equals(citizenService.getCitizenByAuthentication().getId())){
                if(userRequest.getType().equals(TypeRequest.GETSERTIFICATE)){
                    if(userRequest.getStatus().equals(UserRequestStatus.PROCESSING)) return true;
                }
            }
        }
        return false;
    }


    public void changeOffice(UserRequest request) {
        request.setStatus(UserRequestStatus.PROCESSING);
        request.setIdUser(citizenService.getCitizenByAuthentication());
        request.setType(TypeRequest.CHANGEOFFICE);
        requestServiceRepository.save(request);
    }

    public void getCertificate(UserRequest requests) {
        requests.setType(TypeRequest.GETSERTIFICATE);
        requests.setIdUser(citizenService.getCitizenByAuthentication());
        requests.setStatus(UserRequestStatus.PROCESSING);
        requestServiceRepository.save(requests);
    }

    public List<UserRequest> getAllRequests(){
        List<UserRequest> userRequestList = requestServiceRepository.findAll();
        List<UserRequest> requests = new ArrayList<>();
        for(UserRequest userRequest:userRequestList){
            if(userRequest.getStatus().equals(UserRequestStatus.PROCESSING))
                requests.add(userRequest);
        }
        return requests;
    }

    public void deleteChange(UserRequest tmp) {
        for (Citizen user : citizenService.getAllCitizen()) {
            if (user.getId() == tmp.getIdUser().getId()) {
                for (UserRequest userRequests : requestServiceRepository.findAll()) {
                    if (userRequests.getId() == tmp.getId() && tmp.getType().equals(userRequests.getType())){
                        userRequests.setStatus(UserRequestStatus.FAILED);
                        requestServiceRepository.save(userRequests);
                        if(tmp.getType().equals(TypeRequest.CHANGEOFFICE))
                            notificationService.sendNotification(NotificationStatus.FAILCHANGE, tmp.getIdUser());
                        if(tmp.getType().equals(TypeRequest.GETSERTIFICATE))
                            notificationService.sendNotification(NotificationStatus.FAILDOCUMENT, tmp.getIdUser());
                    }
                }
            }
        }
    }


    public void acceptChange(UserRequest tmp) {
        for(Citizen users: citizenService.getAllCitizen()){
            if(tmp.getIdUser().getId()==users.getId()){
                for(MilitaryOffice militaryOffice: militaryOfficeRepository.findAll()){
                    if(militaryOffice.getId().equals(Integer.parseInt(tmp.getData()))){
                        users.setMilitaryOffice(militaryOffice);
                        citizenService.save(users);
                        for(UserRequest userRequests: requestServiceRepository.findAll()){
                            if(userRequests.getId()==tmp.getId()) {
                                userRequests.setStatus(UserRequestStatus.SUCCESS);
                                requestServiceRepository.save(userRequests);
                                notificationService.sendNotification(NotificationStatus.SUCCESSCHANGE, tmp.getIdUser());
                               }
                        }
                        return;
                    }
                }
            }
        }
    }

    @SneakyThrows
    public void saveFile(MultipartFile file, Document document) {
        document.setEmployee(employeeService.getEmployeeByAuthentication());
        document.setFile(file.getBytes());
        documentRepository.save(document);
        notificationService.sendNotification(NotificationStatus.SUCCESSDOCUMENT, document.getUser());

        for(UserRequest requestService: requestServiceRepository.findAll()){
            if(requestService.getIdUser().getId()==document.getUser().getId() && requestService.getStatus().equals(UserRequestStatus.PROCESSING)){
                requestService.setStatus(UserRequestStatus.SUCCESS);
                requestServiceRepository.save(requestService);
                break;
            }
        }
    }

    public List<RequestDto> getAllRequestsDto() {
        List<RequestDto> requestDtoList = new ArrayList<>();
        List<UserRequest> allRequests = getAllRequests();
        for(UserRequest userRequest: allRequests){
            requestDtoList.add(requestMapper.mapToRequestDto(userRequest));
        }
        return requestDtoList;
    }
}
