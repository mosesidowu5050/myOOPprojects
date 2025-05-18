package data.model;
import java.time.LocalDateTime;


public class AccessCode {
    private int id;
    private String otpCode;
    private LocalDateTime otpCreatedOn = LocalDateTime.now();
    private LocalDateTime otpExpiredOn;

    public AccessCode() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public LocalDateTime getOtpCreatedOn() {
        return otpCreatedOn;
    }

    public LocalDateTime getOtpExpiredOn() {
        return otpExpiredOn;
    }

    public void setOtpExpiredOn(LocalDateTime otpExpiredOn) {
        this.otpExpiredOn = otpExpiredOn;
    }

}
