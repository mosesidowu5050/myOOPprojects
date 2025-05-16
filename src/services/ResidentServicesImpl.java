package services;

import data.model.Resident;
import data.repository.ResidentRepository;
import dtos.request.LoginServiceRequest;
import dtos.request.ResidentServicesRequest;
import dtos.responses.LoginServiceResponse;
import dtos.responses.ResidentServicesResponse;
import utils.Mapper;

import java.util.Optional;

public class ResidentServicesImpl implements ResidentServices {

    private ResidentRepository residentRepository;

    public ResidentServicesImpl(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
        Mapper.setResidentRepository(residentRepository);
    }

    @Override
    public ResidentServicesResponse register(ResidentServicesRequest residentServicesRequest) {
        return Mapper.map(residentServicesRequest);
    }

    @Override
    public LoginServiceResponse login(LoginServiceRequest request) {
        Optional<Resident> residentOpt = residentRepository.findById(request.getId());

        if (residentOpt.isEmpty()) {
            throw new RuntimeException("Invalid ID");
        }
        LoginServiceResponse response = new LoginServiceResponse();
        response.setMessage("Login successful");
        return response;
    }
}
