package data.model;
import java.time.LocalDateTime;


public class AccessToken {
    private int id;
    private String otpCode;
    private LocalDateTime otpCreatedOn = LocalDateTime.now();
    private LocalDateTime otpExpiredDate;
    private Resident resident;
    private Visitor visitor;

    public AccessToken() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String receiveAccessToken() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }
    public String getOtpCode() {
        return otpCode;
    }

    public LocalDateTime getOtpCreatedOn() {
        return otpCreatedOn;
    }

    public LocalDateTime getOtpExpiredOn() {
        return otpExpiredDate;
    }

    public void setOtpExpiredOn(LocalDateTime otpExpiredOn) {
        this.otpExpiredDate = otpExpiredOn;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
    public Visitor getVisitor() {
        return visitor;
    }
    public void setResident(Resident resident) {
        this.resident = resident;
    }
    public Resident getResident() {
        return resident;
    }
}
