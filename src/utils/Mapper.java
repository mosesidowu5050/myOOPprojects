package utils;

import data.model.Resident;
import data.repository.ResidentRepository;
import data.repository.Residents;
import dtos.request.LoginServiceRequest;
import dtos.request.ResidentServicesRequest;
import dtos.responses.LoginServiceResponse;
import dtos.responses.ResidentServicesResponse;

import java.util.Optional;

public class Mapper {

    private static ResidentRepository residentRepository = new Residents();

    public static ResidentServicesResponse map(ResidentServicesRequest request) {
        Resident resident = new Resident();
        resident.setFullName(request.getFullName());
        resident.setHomeAddress(request.getHomeAddress());
        resident.setPhoneNumber(request.getPhoneNumber());

        isNotValidPhoneNumber(request);
        Resident savedResident = residentRepository.save(resident);

        ResidentServicesResponse response = new ResidentServicesResponse();
        response.setId(savedResident.getId());
        response.setFullName(savedResident.getFullName());
        response.setEmail(savedResident.getPhoneNumber());
        response.setAddress(savedResident.getHomeAddress());

        return response;
    } 

    public static LoginServiceResponse loginMap(LoginServiceRequest loginServiceRequest) {
        Optional<Resident> confirmResidentID = residentRepository.findById(loginServiceRequest.getId());
        Optional<Resident> confirmResidentPhoneNumber = residentRepository.findResidentByPhoneNumber(loginServiceRequest.getPhoneNumber());

        if (confirmResidentID.isEmpty() || confirmResidentPhoneNumber.isEmpty()) {
            throw new RuntimeException("Invalid Id or Phone Number");
        }
        LoginServiceResponse response = new LoginServiceResponse();
        response.setMessage("Login successful");
        return response;
    }

    private static void isNotValidPhoneNumber(ResidentServicesRequest residentServicesRequest) {
        boolean phoneNumberExist = residentRepository.confirmPhoneNumber(residentServicesRequest.getPhoneNumber());
        if (phoneNumberExist) throw new IllegalArgumentException("Phone number already exist");
    }


}
