package services;

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

    @BeforeEach
    void setUp() {
        residentRepository = new Residents();
        residentServices = new ResidentServicesImpl(residentRepository);
    }


    @Test
    void testRegisterOneResidents_shouldSaveAll() {
        ResidentServicesRequest registerRequest = new ResidentServicesRequest();
        registerRequest.setFullName("Moses Idowu");
        registerRequest.setHomeAddress("Lagos");
        registerRequest.setPhoneNumber("0704445566");
        residentServices.register(registerRequest);

        assertEquals(1, residentRepository.count());
    }

    @Test
    void testRegisterMultipleResidents_shouldSaveAll() {
        ResidentServicesRequest registerRequest1 = new ResidentServicesRequest();
        registerRequest1.setFullName("Moses Idowu");
        registerRequest1.setHomeAddress("Lagos");
        registerRequest1.setPhoneNumber("0701112233");
        residentServices.register(registerRequest1);

        ResidentServicesRequest registerRequest2 = new ResidentServicesRequest();
        registerRequest2.setFullName("MD Empire");
        registerRequest2.setHomeAddress("Lagos");
        registerRequest2.setPhoneNumber("0704445566");
        residentServices.register(registerRequest2);

        assertEquals(2, residentRepository.count());
    }

    @Test
    void testRegisterAndLogin_shouldReturnSuccessfulLogin() {
        ResidentServicesRequest registerRequest = new ResidentServicesRequest();
        registerRequest.setFullName("Moses Idowu");
        registerRequest.setHomeAddress("Lagos");
        registerRequest.setPhoneNumber("0700000000");

        ResidentServicesResponse registerResponse = residentServices.register(registerRequest);

        assertNotNull(registerResponse);
        assertTrue(registerResponse.getId() > 0);

        LoginServiceRequest loginRequest = new LoginServiceRequest();
        loginRequest.setId(registerResponse.getId());

        LoginServiceResponse loginResponse = residentServices.login(loginRequest);
        assertEquals("Login successful", loginResponse.getMessage());
    }


    @Test
    public void testResidentRegistrationAndLogin() {
        ResidentServicesRequest request = new ResidentServicesRequest();
        request.setFullName("Moses Idowu");
        request.setHomeAddress("Nairobi");
        request.setPhoneNumber("0721234567");

        ResidentServicesResponse response = residentServices.register(request);

        assertNotNull(response);
        assertEquals("Moses Idowu", response.getFullName());
        assertEquals("Nairobi", response.getAddress());
        assertEquals(1, response.getId());

        LoginServiceRequest loginRequest = new LoginServiceRequest();
        loginRequest.setId(response.getId());

        LoginServiceResponse loginResponse = residentServices.login(loginRequest);

        assertNotNull(loginResponse);
        assertEquals("Login successful", loginResponse.getMessage());
    }

    @Test
    void testLoginWithInvalidId_shouldReturnError() {
        LoginServiceRequest loginRequest = new LoginServiceRequest();
        loginRequest.setId(9999);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            residentServices.login(loginRequest);
        });

        assertEquals("Invalid ID", exception.getMessage());
    }

}

