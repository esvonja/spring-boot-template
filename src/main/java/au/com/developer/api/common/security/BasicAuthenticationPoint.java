package au.com.developer.api.common.security;

import au.com.developer.api.common.error.CustomErrors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class BasicAuthenticationPoint extends BasicAuthenticationEntryPoint {

    @Value("${changeme.security.realm-name}")
    private String realmName;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) throws IOException {
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        CustomErrors errors = new CustomErrors(HttpStatus.UNAUTHORIZED, authEx.getLocalizedMessage(), null);
        PrintWriter writer = response.getWriter();
        writer.println(errors.toJson());
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName(realmName);
        super.afterPropertiesSet();
    }
}
