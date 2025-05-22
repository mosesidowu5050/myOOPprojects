package services;

import data.model.AccessToken;
import data.model.Resident;
import data.model.Visitor;
import data.repository.ResidentRepository;
import data.repository.Residents;
import dtos.request.LoginServiceRequest;
import dtos.request.ResidentServicesRequest;
import dtos.responses.LoginServiceResponse;
import dtos.responses.ResidentServicesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static utils.Mapper.loginMap;

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
    void testRegisterMultipleResidents_countShouldBeTheNumber_OfResidentsRegistered() {
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
    void testRegisterResident_loginResident_residentInviteVisitor_residentGenerateAccessToken(){
        registerRequest.setFullName("Moses Idowu");
        registerRequest.setHomeAddress("Lagos");
        registerRequest.setPhoneNumber("0704445566");
        residentServices.register(registerRequest);
        assertEquals(1, residentRepository.count());

        Resident resident = new Resident();
        resident.setFullName(registerRequest.getFullName());
        resident.setHomeAddress(registerRequest.getHomeAddress());
        resident.setPhoneNumber(registerRequest.getPhoneNumber());
        residentRepository.save(resident);
        assertEquals(2, resident.getId());

        LoginServiceRequest loginRequest = new LoginServiceRequest();
        loginRequest.setId(2);
        loginRequest.setPhoneNumber("0704445566");
        residentRepository.findById(loginRequest.getId());
        residentRepository.findResidentByPhoneNumber(loginRequest.getPhoneNumber());
        LoginServiceResponse response = loginMap(loginRequest);
        assertEquals("Login successful", response.getMessage());

        Visitor visitor = new Visitor();
        visitor.setFullName("Majek Olamilekan");
        visitor.setHomeAddress("Lagos");
        visitor.setPhoneNumber("0704445523");
        resident.setVisitor(visitor);

        AccessToken accessToken = new AccessToken();
        accessToken.setVisitor(visitor);

        AccessToken otp = residentServices.generateAccessToken(visitor, accessToken);
        assertNotNull(otp);
    }
}

