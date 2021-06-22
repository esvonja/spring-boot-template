package au.com.developer.api.web.controller.rest;

import au.com.developer.api.service.WelcomeService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

@RestController
@RequestMapping(path = "/api/v1")
public class ApiControllerV1 {

    @Autowired
    private WelcomeService service;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${ap.env}")
    private String apEnv;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiControllerV1.class);

    @GetMapping("/welcome")
    public String getWelcome() {
        LOGGER.debug("Welcome handler");
        return service.getWelcomeMessage();
    }

    @GetMapping("/service")
    public String getService() {
        // Note that this is just for testing the AWS Xray RestTemplate
        return service.getWelcomeMessage();
    }

    @RequestMapping(value = "/**")
    public ResponseEntity defaultHandler(HttpServletRequest request, @RequestHeader HttpHeaders headers) throws NoHandlerFoundException {
        // This is the fall through handler for anything on this controller path that is not
        // explicitly mapped.  Without this, the CustomRestExceptionHandler won't fire, as the URL
        // would not be mapped to a @RestController.  This means the default exception handler will
        // be invoked which will return a HTML error page.
        throw new NoHandlerFoundException(request.getMethod(), request.getRequestURI(), headers);
    }

    private HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }

    private String getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
