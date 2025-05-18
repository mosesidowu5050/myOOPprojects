package utils;

import data.model.Resident;
import data.repository.ResidentRepository;
import data.repository.Residents;
import dtos.request.LoginServiceRequest;
import dtos.request.ResidentServicesRequest;
import dtos.responses.ResidentServicesResponse;

public class Mapper {


    private static ResidentRepository residentRepository;

    public static void setResidentRepository(ResidentRepository repository) {
        residentRepository = repository;
    }

    public static ResidentServicesResponse map(ResidentServicesRequest request) {
        Resident resident = new Resident();
        resident.setFullName(request.getFullName());
        resident.setHomeAddress(request.getHomeAddress());
        resident.setPhoneNumber(request.getPhoneNumber());

        Resident savedResident = residentRepository.save(resident);

        ResidentServicesResponse response = new ResidentServicesResponse();
        response.setId(savedResident.getId());
        response.setFullName(savedResident.getFullName());
        response.setEmail(savedResident.getPhoneNumber());
        response.setAddress(savedResident.getHomeAddress());

        return response;
    }
}
