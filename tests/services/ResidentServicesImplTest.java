package services;

import data.model.Resident;
import data.repository.ResidentRepository;
import data.repository.Residents;
import dtos.request.ResidentServicesRequest;
import dtos.responses.ResidentServicesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResidentServicesImplTest {

    private ResidentRepository residentRepository;
    private ResidentServices residentServices;

    @BeforeEach
    public void setup(){
        residentRepository = new Residents();
        residentServices = new ResidentServicesImpl();
    }

    @Test
    public void savedOneResident_registerRequest_countOneTest(){
        Resident resident = new Resident();
        ResidentServicesRequest residentServicesRequest = new ResidentServicesRequest();
        residentServicesRequest.setFullName("Moses Idowu");
        residentServicesRequest.setHomeAddress("Nairobi");
        residentServicesRequest.setPhoneNumber("0721234567");

        resident.setFullName(residentServicesRequest.getFullName());
        resident.setHomeAddress(residentServicesRequest.getHomeAddress());
        resident.setPhoneNumber(residentServicesRequest.getPhoneNumber());
        residentRepository.save(resident);

        ResidentServicesResponse response = residentServices.register(residentServicesRequest);
        response.setFullName(residentServicesRequest.getFullName());
        response.setAddress(residentServicesRequest.getHomeAddress());
        response.setId(resident.getId());

        assertEquals(1, response.getId());
//        assertEquals(1, response.equals(response));

        assertEquals("Moses Idowu", response.getFullName());
        assertEquals("Nairobi", response.getAddress());
        assertEquals(1, residentRepository.count());
    }

    @Test
    public void savedOneResident_registerRequest_responseToResident_loginToAccount_countOne_findByIdTest(){
        Resident resident = new Resident();
        ResidentServicesRequest residentServicesRequest = new ResidentServicesRequest();
        residentServicesRequest.setFullName("Moses Idowu");
        residentServicesRequest.setHomeAddress("Nairobi");
        residentServicesRequest.setPhoneNumber("0721234567");

        resident.setFullName(residentServicesRequest.getFullName());
        resident.setHomeAddress(residentServicesRequest.getHomeAddress());
        resident.setPhoneNumber(residentServicesRequest.getPhoneNumber());
        residentRepository.save(resident);


    }
}

