package data.model;

public class Resident {
    private long id;
    private String fullName;
    private String homeAddress;
    private String phoneNumber;
    private Visitor visitor;
    private AccessToken accessCode;


    public Resident() {
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AccessToken getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(AccessToken accessCode) {
        this.accessCode = accessCode;
    }

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

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

}
