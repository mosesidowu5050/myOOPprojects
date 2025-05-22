package services;

import data.model.AccessToken;
import data.model.Visitor;
import data.repository.AccessTokenRepository;
import data.repository.AccessTokens;
import data.repository.ResidentRepository;
import dtos.request.LoginServiceRequest;
import dtos.request.ResidentServicesRequest;
import dtos.responses.LoginServiceResponse;
import dtos.responses.ResidentServicesResponse;

import static utils.Mapper.*;

public class ResidentServicesImpl implements ResidentServices {

    private ResidentRepository residentRepository;
    private AccessTokenService accessTokenService;


    public ResidentServicesImpl(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
        this.accessTokenService = new AccessTokenImpl();
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
    public AccessToken generateAccessToken(Visitor visitor, AccessToken accessToken) {
        visitor.setFullName(visitor.getFullName());
        visitor.setHomeAddress(visitor.getHomeAddress());
        visitor.setPhoneNumber(visitor.getPhoneNumber());

        accessToken.setVisitor(visitor);

        return accessTokenService.generateAccessToken(accessToken);
    }

}
