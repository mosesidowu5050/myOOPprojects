package services;

import data.model.AccessToken;
import data.model.Visitor;
import data.repository.AccessTokenRepository;
import data.repository.AccessTokens;

import java.util.Random;

public class AccessTokenImpl implements AccessTokenService {

    private AccessTokenRepository accessTokenRepository = new AccessTokens();

    @Override
    public AccessToken generateAccessToken(Visitor visitor) {
            Random random = new Random();
            AccessToken accessToken = new AccessToken();
            int randomNumber = random.nextInt(1000);
            String generateOtp = String.format("%04d", randomNumber);
            accessToken.setOtpCode(generateOtp);
            AccessToken otp = accessTokenRepository.save(accessToken);

            return otp;
    }



}
