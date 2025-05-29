package data.repository;

import data.model.AccessToken;
import data.model.Resident;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccessTokensTest {

    AccessTokenRepository accessTokenRepository;
    AccessTokens accessTokens;

    @BeforeEach
    public void setUp() {
        accessTokenRepository = new AccessTokens();
        accessTokens = new AccessTokens();
        accessTokens.deleteAll();

    }

    @Test
    public void testBeforeGenerateAccessToken_countIsZero() {
        assertEquals(0, accessTokenRepository.count());
    }

    @Test
    public void testGenerateAccessToken_countIsOne() {
        AccessToken accessToken = new AccessToken();
        accessTokenRepository.save(accessToken);
        assertEquals(1, accessTokenRepository.count());
    }

    @Test
    public void testGenerateAccessToken_findTokenById() {
        AccessToken accessToken = new AccessToken();
        AccessToken accessToken2 = new AccessToken();
        AccessToken token1 = accessTokenRepository.save(accessToken);
        AccessToken token2 = accessTokenRepository.save(accessToken2);
        AccessToken token = accessTokenRepository.findAccessTokenById(token1.getId());
        assertEquals(1, token.getId());
        AccessToken secondToken = accessTokenRepository.findAccessTokenById(token2.getId());
        assertEquals(2, secondToken.getId());
        assertEquals(2, accessTokenRepository.count());
    }

    @Test
    public void testGenerateAccessToken_deleteTokenById() {
        AccessToken accessToken = new AccessToken();
        AccessToken token = accessTokenRepository.save(accessToken);
        assertEquals(1, accessTokenRepository.count());
        accessTokenRepository.deleteById(token.getId());
        assertEquals(0, accessTokenRepository.count());
    }

    @Test
    public void testGenerateAccessToken_validateToken() {
        AccessToken accessToken = new AccessToken();
        accessToken.setOtpCode("1234");
        AccessToken token = accessTokenRepository.save(accessToken);
        assertEquals(1, accessTokenRepository.count());
        assertEquals(1, token.getId());
        AccessToken foundToken = accessTokenRepository.validateAccessToken(token.getOtpCode());
        assertEquals(token.getOtpCode(), foundToken.getOtpCode());
    }

}