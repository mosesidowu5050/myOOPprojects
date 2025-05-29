package data.repository;

import data.model.AccessToken;


public interface AccessTokenRepository {

    AccessToken save(AccessToken accessToken);
    AccessToken validateAccessToken(String otpCode);
    AccessToken findAccessTokenById(long id);

    AccessToken otpExpiredDate(AccessToken accessToken);
    long count();
    void deleteById(long id);
}
