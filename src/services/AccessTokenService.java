package services;

import data.model.AccessToken;

public interface AccessTokenService {

    AccessToken generateAccessToken(AccessToken accessToken);

}
