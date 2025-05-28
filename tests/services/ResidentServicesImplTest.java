package services;

import data.model.AccessToken;
import data.model.Resident;
import data.repository.ResidentRepository;
import data.repository.Residents;
import dtos.request.LoginServiceRequest;
import dtos.request.ResidentServicesRequest;
import dtos.request.VisitorInformationRequest;
import dtos.responses.LoginServiceResponse;
import dtos.responses.ResidentServicesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ResidentServicesImplTest {

    private ResidentRepository residentRepository;
    private ResidentServices residentServices;
    private ResidentServicesRequest registerRequest;
    private LoginServiceRequest loginRequest;

    @BeforeEach
    public void setUp() {
        residentRepository = new Residents();
        residentServices = new ResidentServicesImpl(residentRepository);
        registerRequest = new ResidentServicesRequest();
        loginRequest = new LoginServiceRequest();
        Residents.clear();
    }

    @Test
    void testRegisterOneResidents_countShouldBeOne() {
        registerRequest.setFullName("Moses Idowu");
        registerRequest.setHomeAddress("Lagos");
        registerRequest.setPhoneNumber("0704445566");
        residentServices.register(registerRequest);

        assertEquals(1, residentRepository.count());
    }

    @Test
    void testRegisterMultipleResidents_countShouldBeTwo_residentRegistered() {
        registerRequest.setFullName("Moses Idowu");
        registerRequest.setHomeAddress("Lagos");
        registerRequest.setPhoneNumber("0701112233");
        residentServices.register(registerRequest);
        assertEquals(1, residentRepository.count());

        ResidentServicesRequest registerRequest2 = new ResidentServicesRequest();
        registerRequest2.setFullName("MD Empire");
        registerRequest2.setHomeAddress("Lagos");
        registerRequest2.setPhoneNumber("0704445566");
        residentServices.register(registerRequest2);

        assertEquals(2, residentRepository.count());
    }

    @Test
    void testRegisterOneResidents_countShouldBeOne_giveResponseToResident() {
        registerRequest.setFullName("Moses Idowu");
        registerRequest.setHomeAddress("Lagos");
        registerRequest.setPhoneNumber("0704445566");

        ResidentServicesResponse response = residentServices.register(registerRequest);

        assertEquals(1, response.getId());
        assertEquals("Moses Idowu", response.getFullName());
        assertEquals("0704445566", response.getPhoneNumber());
        assertEquals(1, residentRepository.count());
    }


    @Test
    public void testResidentRegistrationAndLogin() {
        registerRequest.setFullName("Moses Idowu");
        registerRequest.setHomeAddress("Nairobi");
        registerRequest.setPhoneNumber("0721234567");

        Resident resident = new Resident();
        resident.setFullName(registerRequest.getFullName());
        resident.setHomeAddress(registerRequest.getHomeAddress());
        resident.setPhoneNumber(registerRequest.getPhoneNumber());
        residentRepository.save(resident);
        assertEquals(1, resident.getId());

        LoginServiceRequest loginRequest = new LoginServiceRequest();
        loginRequest.setId(1);
        loginRequest.setPhoneNumber("0721234567");
        residentRepository.findById(loginRequest.getId());
        residentRepository.findResidentByPhoneNumber(loginRequest.getPhoneNumber());
        LoginServiceResponse loginResponse = residentServices.login(loginRequest);
        assertEquals("Login successful", loginResponse.getMessage());
    }

    @Test
    void testLoginWithInvalidId_shouldReturnError() {
        loginRequest.setId(9999);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            residentServices.login(loginRequest);
        });
        assertEquals("Invalid Id or Phone Number", exception.getMessage());
    }

    @Test
    void testResidentCanRegister_withDifferentPhoneNumber() {
        registerRequest.setFullName("Moses Idowu");
        registerRequest.setHomeAddress("Lagos");
        registerRequest.setPhoneNumber("0704445566");
        residentServices.register(registerRequest);

        ResidentServicesRequest registerRequest2 = new ResidentServicesRequest();
        registerRequest2.setFullName("Moses Idowu");
        registerRequest2.setHomeAddress("Lagos");
        registerRequest2.setPhoneNumber("0704445568");
        residentServices.register(registerRequest2);
    }

    @Test
    void testResidentCannotRegister_withSamePhoneNumber() {
        registerRequest.setFullName("Moses Idowu");
        registerRequest.setHomeAddress("Lagos");
        registerRequest.setPhoneNumber("0704445566");
        residentServices.register(registerRequest);

        ResidentServicesRequest registerRequest2 = new ResidentServicesRequest();
        registerRequest2.setFullName("Moses Idowu");
        registerRequest2.setHomeAddress("Lagos");
        registerRequest2.setPhoneNumber("0704445566");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            residentServices.register(registerRequest2);
        });
        assertEquals("Phone number already exist", exception.getMessage());
    }

    @Test
    public void testRegister_loginResident_withInvalidResidentId_andPhoneNumber_throwException() {
        registerRequest.setFullName("Moses Idowu");
        registerRequest.setHomeAddress("Lagos");
        registerRequest.setPhoneNumber("0704445566");
        residentServices.register(registerRequest);
        assertEquals(1, residentRepository.count());

        Resident resident = residentRepository.findResidentByPhoneNumber("0704445566");
        assertNotNull(resident);
        Long residentId = resident.getId();

        LoginServiceRequest loginRequest = new LoginServiceRequest();
        loginRequest.setId(2);
        loginRequest.setPhoneNumber("10704445566");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            residentServices.login(loginRequest);
        });
        assertEquals("Invalid Id or Phone Number", exception.getMessage());
    }

    @Test
    void testRegisterResident_loginResident_residentInviteVisitor_residentGenerateAccessToken(){
        registerRequest.setFullName("Moses Idowu");
        registerRequest.setHomeAddress("Lagos");
        registerRequest.setPhoneNumber("0704445566");
        residentServices.register(registerRequest);
        assertEquals(1, residentRepository.count());

        Resident resident = residentRepository.findResidentByPhoneNumber("0704445566");
        assertNotNull(resident);
        Long residentId = resident.getId();

        LoginServiceRequest loginRequest = new LoginServiceRequest();
        loginRequest.setId(residentId);
        loginRequest.setPhoneNumber(resident.getPhoneNumber());
        LoginServiceResponse response = residentServices.login(loginRequest);
        assertEquals("Login successful", response.getMessage());

        VisitorInformationRequest visitorRequest = new VisitorInformationRequest();
        visitorRequest.setFullName("Majek Olamilekan");
        visitorRequest.setPhoneNumber("0704445523");
        visitorRequest.setHomeAddress("Lagos");

        AccessToken token = residentServices.inviteVisitorAndGenerateToken(residentId, visitorRequest);
        assertNotNull(token);
        assertEquals("Majek Olamilekan", token.getVisitor().getFullName());
        assertEquals("0704445523", token.getVisitor().getPhoneNumber());
        assertEquals("Lagos", token.getVisitor().getHomeAddress());
        assertEquals(residentId, token.getResident().getId());
    }


}
