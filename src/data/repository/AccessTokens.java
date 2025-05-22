package data.repository;

import data.model.AccessToken;

import java.util.ArrayList;
import java.util.List;

public class AccessTokens implements AccessTokenRepository {

    private static long tokenCounter = 0;
    private static List<AccessToken> accessTokens = new ArrayList<>();


    @Override
    public AccessToken save(AccessToken accessToken) {
        accessTokens.add(accessToken);
        return accessToken;
    }

    @Override
    public AccessToken findAccessToken(String accessToken) {
        for (AccessToken token : accessTokens) {
            if (token.receiveAccessToken().equals(accessToken)) {
                return token;
            }
        }
        return null;
    }

    @Override
    public AccessToken validateAccessToken(AccessToken accessToken) {
        return null;
    }

    @Override
    public AccessToken otpExpiredDate(AccessToken accessToken) {
        return null;
    }

    @Override
    public long count() {
        return accessTokens.size();
    }
}
