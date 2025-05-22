package services;

import data.model.AccessToken;
import data.repository.AccessTokenRepository;
import data.repository.AccessTokens;

import java.util.Random;

public class AccessTokenImpl implements AccessTokenService {

    private AccessTokenRepository accessTokenRepository = new AccessTokens();

    @Override
    public AccessToken generateAccessToken(AccessToken accessToken) {
            Random random = new Random();
            int randomNumber = random.nextInt(1000);
            String generateOtp = String.format("%04d", randomNumber);
            accessToken.setOtpCode(generateOtp);
            AccessToken otp = accessTokenRepository.save(accessToken);

            System.out.println(otp);

            return otp;
    }

}
