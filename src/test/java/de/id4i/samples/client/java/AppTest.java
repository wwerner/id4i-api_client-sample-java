package de.id4i.samples.client.java;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AppTest 
{
    @Test
    public void jwtTokenIsInitialized()
    {
        App app = new App();
        String token = app.createAccessToken("test-api-key", "my secret");
        Jwt<Header, Claims> jwt = Jwts.parser()
            .setSigningKey("my secret").parse(token);

        assertThat(jwt.getHeader().getType(), is("API"));
        assertThat(jwt.getBody().getSubject(), is("test-api-key"));
        assertTrue("Expiration date must be in the future",
            jwt.getBody().getExpiration().getTime() > System.currentTimeMillis());
    }
}
