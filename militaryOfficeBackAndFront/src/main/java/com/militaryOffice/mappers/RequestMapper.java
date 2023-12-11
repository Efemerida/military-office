package com.militaryOffice.mappers;

import com.militaryOffice.model.RequestDto;
import com.militaryOffice.model.UserRequest;
import com.militaryOffice.services.CitizenService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RequestMapper {

    private final CitizenService citizenService;

    public RequestDto mapToRequestDto(UserRequest userRequest){
        ModelMapper modelMapper = new ModelMapper();
        RequestDto requestDto = modelMapper.map(userRequest, RequestDto.class);
        requestDto.setUserId(userRequest.getIdUser().getId());
        return requestDto;
    }

    public UserRequest mapToRequest(RequestDto requestDto){
        ModelMapper modelMapper = new ModelMapper();
        UserRequest userRequest = modelMapper.map(requestDto, UserRequest.class);
        userRequest.setIdUser(citizenService.getCitizenById(requestDto.getUserId()));
        return userRequest;
    }

}
