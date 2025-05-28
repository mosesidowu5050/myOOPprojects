package services;

import data.model.AccessToken;
import dtos.request.LoginServiceRequest;
import dtos.request.ResidentServicesRequest;
import dtos.request.VisitorInformationRequest;
import dtos.responses.LoginServiceResponse;
import dtos.responses.ResidentServicesResponse;

public interface ResidentServices {

    ResidentServicesResponse register(ResidentServicesRequest residentServicesRequest);
    LoginServiceResponse login(LoginServiceRequest loginServiceRequest);
    AccessToken inviteVisitorAndGenerateToken(Long residentId, VisitorInformationRequest visitorRequest);

}
