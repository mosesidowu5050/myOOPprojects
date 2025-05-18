package services;

import data.repository.ResidentRepository;
import dtos.request.LoginServiceRequest;
import dtos.request.ResidentRegisterRequest;
import dtos.responses.LoginServiceResponse;
import dtos.responses.ResidentRegisterResponse;
import utils.Mapper;

import static utils.Mapper.mapLogin;

public class ResidentServicesImpl implements ResidentServices {

    private ResidentRepository residentRepository;

    public ResidentServicesImpl(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
        Mapper.setResidentRepository(residentRepository);
    }

    @Override
    public ResidentRegisterResponse register(ResidentRegisterRequest residentServicesRequest) {
        return Mapper.map(residentServicesRequest);
    }

    @Override
    public LoginServiceResponse login(LoginServiceRequest request) {
        return mapLogin(request);
    }


}
