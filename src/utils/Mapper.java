package utils;

import data.model.AccessToken;
import data.model.Resident;
import data.model.Visitor;
import data.repository.*;
import dtos.request.LoginServiceRequest;
import dtos.request.ResidentServicesRequest;
import dtos.request.VisitorRequest;
import dtos.responses.LoginServiceResponse;
import dtos.responses.ResidentServicesResponse;
import services.AccessTokenImpl;
import services.AccessTokenService;

import java.util.Optional;

public class Mapper {

    private static ResidentRepository residentRepository = new Residents();
    private static VisitorRepository visitorRepository = new Visitors();
    private static AccessTokenService accessTokenService = new AccessTokenImpl();

    public static ResidentServicesResponse map(ResidentServicesRequest request) {
        Resident resident = new Resident();
        resident.setFullName(request.getFullName());
        resident.setHomeAddress(request.getHomeAddress());
        resident.setPhoneNumber(request.getPhoneNumber());

        verifyPhoneNumber(request);
        return response(resident);
    }

    private static ResidentServicesResponse response (Resident resident) {
        Resident registerResident = residentRepository.save(resident);

        ResidentServicesResponse response = new ResidentServicesResponse();
        response.setId(registerResident.getId());
        response.setFullName(registerResident.getFullName());
        response.setPhoneNumber(registerResident.getPhoneNumber());

        return response;
    }



    public static LoginServiceResponse loginMap(LoginServiceRequest loginServiceRequest) {
        Optional<Resident>confirmResidentID = residentRepository.findById(loginServiceRequest.getId());
        Resident confirmResidentPhoneNumber = residentRepository.findResidentByPhoneNumber(loginServiceRequest.getPhoneNumber());

        if (confirmResidentID.isEmpty() || confirmResidentPhoneNumber == null) {
            throw new RuntimeException("Invalid Id or Phone Number");
        }
        LoginServiceResponse response = new LoginServiceResponse();
        response.setMessage("Login successful");
        return response;
    }


    public static AccessToken map(Long residentId, VisitorRequest visitorRequest) {
        Resident resident = residentRepository.findById(residentId)
                .orElseThrow(() -> new IllegalArgumentException("Resident not found"));

        Visitor visitor = new Visitor();
        visitor.setFullName(visitorRequest.getFullName());
        visitor.setPhoneNumber(visitorRequest.getPhoneNumber());
        visitor.setHomeAddress(visitorRequest.getHomeAddress());
        visitorRepository.save(visitor);

        AccessToken token = accessTokenService.generateAccessToken(visitor);
        token.setResident(resident);
        token.setOtpCode(token.getOtpCode());
        token.setVisitor(visitor);
        return token;
    }

    private static void verifyPhoneNumber(ResidentServicesRequest residentServicesRequest) {
        boolean phoneNumberExist = residentRepository.confirmPhoneNumber(residentServicesRequest.getPhoneNumber());
        if (phoneNumberExist) throw new IllegalArgumentException("Phone number already exist");
    }

}
