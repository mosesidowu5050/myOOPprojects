package services;

import data.model.Resident;
import data.repository.ResidentRepository;
import data.repository.Residents;
import dtos.request.LoginServiceRequest;
import dtos.request.ResidentServicesRequest;
import dtos.responses.ResidentServicesResponse;
import static utility.Mapper.map;

public class ResidentServicesImpl implements ResidentServices {

    private ResidentRepository residentRepository = new Residents();

    @Override
    public ResidentServicesResponse register(ResidentServicesRequest residentServicesRequest) {
        ResidentServicesResponse residentServicesResponse = new ResidentServicesResponse();
        map(residentServicesRequest);

        return residentServicesResponse;
    }

    @Override
    public String login(LoginServiceRequest loginServiceRequest) {
//        LoginServiceRequest loginRequest = new LoginServiceRequest();
        Resident resident = new Resident();



        return null;
    }

}
