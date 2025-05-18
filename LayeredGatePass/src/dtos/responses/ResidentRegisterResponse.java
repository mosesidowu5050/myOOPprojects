package dtos.responses;

import java.util.Objects;

public class ResidentRegisterResponse {

    private long id;
    private String fullName;
    private String email;
    private String address;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResidentRegisterResponse residentServicesResponse)) return false;
        return id == residentServicesResponse.id && Objects.equals(fullName, residentServicesResponse.fullName)
                && Objects.equals(email, residentServicesResponse.email)
                && Objects.equals(address, residentServicesResponse.address);
    }

}
