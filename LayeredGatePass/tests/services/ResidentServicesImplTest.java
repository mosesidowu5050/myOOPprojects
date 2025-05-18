package services;

import data.repository.ResidentRepository;
import data.repository.Residents;
import dtos.request.LoginServiceRequest;
import dtos.request.ResidentRegisterRequest;
import dtos.responses.LoginServiceResponse;
import dtos.responses.ResidentRegisterResponse;
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
        ResidentRegisterRequest registerRequest = new ResidentRegisterRequest();
        registerRequest.setFullName("Moses Idowu");
        registerRequest.setHomeAddress("Lagos");
        registerRequest.setPhoneNumber("0704445566");
        residentServices.register(registerRequest);

        assertEquals(1, residentRepository.count());
    }

    @Test
    void testRegisterMultipleResidents_shouldSaveAll() {
        ResidentRegisterRequest registerRequest1 = new ResidentRegisterRequest();
        registerRequest1.setFullName("Moses Idowu");
        registerRequest1.setHomeAddress("Lagos");
        registerRequest1.setPhoneNumber("0701112233");
        residentServices.register(registerRequest1);

        ResidentRegisterRequest registerRequest2 = new ResidentRegisterRequest();
        registerRequest2.setFullName("MD Empire");
        registerRequest2.setHomeAddress("Lagos");
        registerRequest2.setPhoneNumber("0704445566");
        residentServices.register(registerRequest2);

        assertEquals(2, residentRepository.count());
    }

    @Test
    void testRegisterAndLogin_shouldReturnSuccessfulLogin() {
        ResidentRegisterRequest registerRequest = new ResidentRegisterRequest();
        registerRequest.setFullName("Moses Idowu");
        registerRequest.setHomeAddress("Lagos");
        registerRequest.setPhoneNumber("0700000000");

        ResidentRegisterResponse registerResponse = residentServices.register(registerRequest);

        assertNotNull(registerResponse);
        assertTrue(registerResponse.getId() > 0);

        LoginServiceRequest loginRequest = new LoginServiceRequest();
        loginRequest.setId(registerResponse.getId());

        LoginServiceResponse loginResponse = residentServices.login(loginRequest);
        assertEquals("Login successful", loginResponse.getMessage());
    }


    @Test
    public void testResidentRegistrationAndLogin() {
        ResidentRegisterRequest request = new ResidentRegisterRequest();
        request.setFullName("Moses Idowu");
        request.setHomeAddress("Nairobi");
        request.setPhoneNumber("0721234567");

        ResidentRegisterResponse response = residentServices.register(request);

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

