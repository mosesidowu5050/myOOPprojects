package services;

import data.model.AccessToken;
import data.repository.*;
import dtos.request.LoginServiceRequest;
import dtos.request.ResidentServicesRequest;
import dtos.request.VisitorInformationRequest;
import dtos.responses.LoginServiceResponse;
import dtos.responses.ResidentServicesResponse;

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
        return map(residentServicesRequest);
    }

    @Override
    public LoginServiceResponse login(LoginServiceRequest request) {
        return loginMap(request);
    }

    @Override
    public AccessToken inviteVisitorAndGenerateToken(Long residentId, VisitorInformationRequest visitorRequest) {
        return map(residentId, visitorRequest);
    }



}
