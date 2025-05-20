package services;

import data.model.Resident;
import data.repository.ResidentRepository;
import data.repository.Residents;
import dtos.request.LoginServiceRequest;
import dtos.request.ResidentServicesRequest;
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
        residentServices = new ResidentServicesImpl();
        registerRequest = new ResidentServicesRequest();
        loginRequest = new LoginServiceRequest();
    }


    @Test
    void testRegisterOneResidents_shouldSaveAll() {
        registerRequest.setFullName("Moses Idowu");
        registerRequest.setHomeAddress("Lagos");
        registerRequest.setPhoneNumber("0704445566");
        residentServices.register(registerRequest);

        assertEquals(1, residentRepository.count());
    }

    @Test
    void testRegisterMultipleResidents_shouldSaveAll() {
        registerRequest.setFullName("Moses Idowu");
        registerRequest.setHomeAddress("Lagos");
        registerRequest.setPhoneNumber("0701112233");
        residentServices.register(registerRequest);

        ResidentServicesRequest registerRequest2 = new ResidentServicesRequest();
        registerRequest2.setFullName("MD Empire");
        registerRequest2.setHomeAddress("Lagos");
        registerRequest2.setPhoneNumber("0704445566");
        residentServices.register(registerRequest2);

        assertEquals(2, residentRepository.count());
    }

    @Test
    void testRegisterAndLogin_shouldReturnSuccessfulLogin() {
        registerRequest.setFullName("Moses Idowu");
        registerRequest.setHomeAddress("Lagos");
        registerRequest.setPhoneNumber("0700000000");

        ResidentServicesResponse registerResponse = residentServices.register(registerRequest);

        assertNotNull(registerResponse);
        assertTrue(registerResponse.getId() > 0);

        loginRequest.setId(registerResponse.getId());

        LoginServiceResponse loginResponse = residentServices.login(loginRequest);
        assertEquals("Login successful", loginResponse.getMessage());
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

        LoginServiceRequest loginRequest2 = new LoginServiceRequest();
        loginRequest2.setId(1);
        loginRequest2.setPhoneNumber("0721234567");
        residentRepository.findById(loginRequest2.getId());
        residentRepository.findResidentByPhoneNumber(loginRequest2.getPhoneNumber());

        LoginServiceResponse loginResponse = residentServices.login(loginRequest2);

        assertEquals("Login successful", loginResponse.getMessage());
    }

    @Test
    void testLoginWithInvalidId_shouldReturnError() {
        loginRequest.setId(9999);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            residentServices.login(loginRequest);
        });

        assertEquals("Invalid ID", exception.getMessage());
    }

}

