package services;

import dtos.request.LoginServiceRequest;
import dtos.request.ResidentServicesRequest;
import dtos.responses.ResidentServicesResponse;

public interface ResidentServices {

    ResidentServicesResponse register(ResidentServicesRequest residentServicesRequest);
    String login(LoginServiceRequest loginServiceRequest);


}
