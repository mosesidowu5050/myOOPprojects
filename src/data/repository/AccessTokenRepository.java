package data.repository;

import data.model.AccessToken;

import java.util.List;

public interface AccessTokenRepository {


    AccessToken save(AccessToken accessToken);
    AccessToken validateAccessToken(AccessToken accessToken);
    AccessToken findAccessToken(String accessToken);
    AccessToken otpExpiredDate(AccessToken accessToken);
    long count();
}
