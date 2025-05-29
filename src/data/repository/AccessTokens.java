package data.repository;

import data.model.AccessToken;

import java.util.ArrayList;
import java.util.List;

public class AccessTokens implements AccessTokenRepository {

    private static long tokenCounter = 0;
    private static List<AccessToken> accessTokens = new ArrayList<>();


    @Override
    public AccessToken save(AccessToken accessToken) {
        accessToken.setId(getTokenId());
        accessTokens.add(accessToken);
        return accessToken;
    }


    @Override
    public AccessToken validateAccessToken(String otpCode) {
        for (AccessToken token : accessTokens) {
            if (token.getOtpCode().equals(otpCode)) {
                return token;
            }
        }
        return null;
    }

    @Override
    public AccessToken findAccessTokenById(long id) {
        for (AccessToken token : accessTokens) {
            if (token.getId() == id) {
                return token;
            }
        }
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

    @Override
    public void deleteById(long id) {
        for(AccessToken token : accessTokens) {
            if (token.getId() == id) {
                accessTokens.remove(token);
                break;
            }
        }
    }

    public long getTokenId(){
        return ++tokenCounter;
    }

    public void deleteAll(){
        accessTokens.clear();
        tokenCounter = 0;
    }
}
