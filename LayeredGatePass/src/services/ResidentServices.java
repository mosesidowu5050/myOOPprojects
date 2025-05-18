package services;

import dtos.request.LoginServiceRequest;
import dtos.request.ResidentRegisterRequest;
import dtos.responses.LoginServiceResponse;
import dtos.responses.ResidentRegisterResponse;

public interface ResidentServices {

    ResidentRegisterResponse register(ResidentRegisterRequest residentServicesRequest);
    LoginServiceResponse login(LoginServiceRequest loginServiceRequest);

//    AccessCode generateAccessCode(AccessCode accessCode);

}
