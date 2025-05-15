package utility;

import data.model.Resident;
import data.repository.ResidentRepository;
import data.repository.Residents;
import dtos.request.ResidentServicesRequest;
import dtos.responses.ResidentServicesResponse;

public class Mapper {

    private static ResidentRepository residentRepository = new Residents();
    private static ResidentServicesResponse residentServicesResponse = new ResidentServicesResponse();

    public static void map(ResidentServicesRequest residentServicesRequest){
        Resident resident = new Resident();
        resident.setFullName(residentServicesRequest.getFullName());
        resident.setHomeAddress(residentServicesRequest.getHomeAddress());
        resident.setPhoneNumber(residentServicesRequest.getPhoneNumber());

        Resident savedResident = residentRepository.save(resident);

        residentServicesResponse.setId(savedResident.getId());
        residentServicesResponse.setFullName(savedResident.getFullName());
        residentServicesResponse.setEmail(savedResident.getPhoneNumber());
        residentServicesResponse.setAddress(savedResident.getHomeAddress());
    }
}
