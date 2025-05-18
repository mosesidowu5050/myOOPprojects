package utils;

import data.model.Resident;
import data.repository.ResidentRepository;
import dtos.request.LoginServiceRequest;
import dtos.request.ResidentRegisterRequest;
import dtos.responses.LoginServiceResponse;
import dtos.responses.ResidentRegisterResponse;

import java.util.Optional;

public class Mapper {


    private static ResidentRepository residentRepository;

    public static void setResidentRepository(ResidentRepository repository) {
        residentRepository = repository;
    }

    public static ResidentRegisterResponse map(ResidentRegisterRequest request) {
        Resident resident = new Resident();
        resident.setFullName(request.getFullName());
        resident.setHomeAddress(request.getHomeAddress());
        resident.setPhoneNumber(request.getPhoneNumber());

        Resident savedResident = residentRepository.save(resident);

        ResidentRegisterResponse response = new ResidentRegisterResponse();
        response.setId(savedResident.getId());
        response.setFullName(savedResident.getFullName());
        response.setEmail(savedResident.getPhoneNumber());
        response.setAddress(savedResident.getHomeAddress());

        return response;
    }

    public static LoginServiceResponse mapLogin(LoginServiceRequest request){
        Optional<Resident> residentOpt = residentRepository.findById(request.getId());

        if (residentOpt.isEmpty()) {
            throw new RuntimeException("Invalid ID");
        }
        LoginServiceResponse response = new LoginServiceResponse();
        response.setMessage("Login successful");
        return response;
    }

}
