package utils;

import data.model.AccessToken;
import data.model.Resident;
import data.model.Visitor;
import data.repository.*;
import dtos.request.LoginServiceRequest;
import dtos.request.ResidentServicesRequest;
import dtos.request.VisitorInformationRequest;
import dtos.responses.LoginServiceResponse;
import dtos.responses.ResidentServicesResponse;
import services.AccessTokenImpl;
import services.AccessTokenService;

public class Mapper {


    public static Resident accessTokenMapper(ResidentServicesRequest request) {
        Resident resident = new Resident();
        resident.setFullName(request.getFullName());
        resident.setHomeAddress(request.getHomeAddress());
        resident.setPhoneNumber(request.getPhoneNumber());

        return resident;
    }

    public static ResidentServicesResponse response (Resident resident) {
        ResidentServicesResponse response = new ResidentServicesResponse();
        response.setId(resident.getId());
        response.setFullName(resident.getFullName());
        response.setPhoneNumber(resident.getPhoneNumber());

        return response;
    }

    public static LoginServiceResponse MapResponse () {
        LoginServiceResponse response = new LoginServiceResponse();
        response.setMessage("Login successful");
        return response;
    }

    public static Visitor visitorMapper(VisitorInformationRequest visitorRequest) {
        Visitor visitor = new Visitor();
        visitor.setFullName(visitorRequest.getFullName());
        visitor.setPhoneNumber(visitorRequest.getPhoneNumber());
        visitor.setHomeAddress(visitorRequest.getHomeAddress());

        return visitor;
    }

    public static AccessToken mapAccessToken(AccessToken token) {
        token.setOtpCode(token.getOtpCode());
        token.setOtpCreatedOn(token.getOtpCreatedOn());

        return token;
    }
}
