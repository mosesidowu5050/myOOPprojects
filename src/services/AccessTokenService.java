package services;

import data.model.AccessToken;
import data.model.Visitor;

public interface AccessTokenService {

    AccessToken generateAccessToken(Visitor visitor);

}
