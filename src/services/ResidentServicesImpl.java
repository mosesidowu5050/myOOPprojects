package services;

import data.model.AccessToken;
import data.model.Resident;
import data.model.Visitor;
import data.repository.*;
import dtos.request.LoginServiceRequest;
import dtos.request.ResidentServicesRequest;
import dtos.request.VisitorInformationRequest;
import dtos.responses.LoginServiceResponse;
import dtos.responses.ResidentServicesResponse;
import utils.Mapper;

import java.util.Optional;

import static utils.Mapper.*;

public class ResidentServicesImpl implements ResidentServices {

    private ResidentRepository residentRepository;
    private AccessTokenService accessTokenService;
    private VisitorRepository visitorRepository;


    public ResidentServicesImpl(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
        this.accessTokenService = new AccessTokenImpl();
        this.visitorRepository = new Visitors();
    }

    @Override
    public ResidentServicesResponse register(ResidentServicesRequest residentServicesRequest) {
        verifyPhoneNumber(residentServicesRequest);
        Resident resident = Mapper.accessTokenMapper(residentServicesRequest);
        Resident savedResident = residentRepository.save(resident);

        return response(savedResident);
    }

    @Override
    public LoginServiceResponse login(LoginServiceRequest loginServiceRequest) {
        validateResidentIDAndPhoneNumber(loginServiceRequest);
        return Mapper.MapResponse();
    }

    @Override
    public AccessToken inviteVisitorAndGenerateToken(Long residentId, VisitorInformationRequest visitorRequest) {
        Resident resident = validateResidentId(residentId);
        Visitor visitor = visitorMapper(visitorRequest);
        Visitor visitorSaved = visitorRepository.save(visitor);

        AccessToken token = accessTokenService.generateAccessToken(visitorSaved);
        AccessToken savedToken = mapAccessToken(token);
        savedToken.setResident(resident);
        savedToken.setVisitor(visitor);

        return savedToken;
    }

    private void verifyPhoneNumber(ResidentServicesRequest residentServicesRequest) {
        boolean phoneNumberExist = residentRepository.confirmPhoneNumber(residentServicesRequest.getPhoneNumber());
        if (phoneNumberExist) throw new IllegalArgumentException("Phone number already exist");
    }


    private void validateResidentIDAndPhoneNumber(LoginServiceRequest loginServiceRequest) {
        Optional<Resident> confirmResidentID = residentRepository.findById(loginServiceRequest.getId());
        Resident confirmResidentPhoneNumber = residentRepository.findResidentByPhoneNumber(loginServiceRequest.getPhoneNumber());

        if (confirmResidentID.isEmpty() || confirmResidentPhoneNumber == null) {
            throw new RuntimeException("Invalid Id or Phone Number");
        }
    }


    private Resident validateResidentId(Long residentId) {
        return residentRepository.findById(residentId)
                .orElseThrow(() -> new IllegalArgumentException("Resident not found"));
    }


}
