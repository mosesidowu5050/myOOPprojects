package services;

import data.model.AccessToken;
import data.model.Resident;
import data.model.Visitor;
import dtos.request.LoginServiceRequest;
import dtos.request.ResidentServicesRequest;
import dtos.responses.LoginServiceResponse;
import dtos.responses.ResidentServicesResponse;

public interface ResidentServices {

    ResidentServicesResponse register(ResidentServicesRequest residentServicesRequest);
    LoginServiceResponse login(LoginServiceRequest loginServiceRequest);

    AccessToken generateAccessToken(Visitor visitor, AccessToken accessToken);
}
