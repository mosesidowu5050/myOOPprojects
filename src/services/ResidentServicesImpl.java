package services;

import data.model.Resident;
import data.repository.ResidentRepository;
import data.repository.Residents;
import dtos.request.LoginServiceRequest;
import dtos.request.ResidentServicesRequest;
import dtos.responses.LoginServiceResponse;
import dtos.responses.ResidentServicesResponse;

import static utils.Mapper.loginMap;
import static utils.Mapper.map;

public class ResidentServicesImpl implements ResidentServices {

    private ResidentRepository residentRepository = new Residents();


    @Override
    public ResidentServicesResponse register(ResidentServicesRequest residentServicesRequest) {
        return map(residentServicesRequest);
    }

    @Override
    public LoginServiceResponse login(LoginServiceRequest request) {
        return loginMap(request);
    }

}
