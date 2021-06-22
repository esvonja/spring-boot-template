package au.com.developer.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class RequestLoggingFilterConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger("ap.http.rest.controller");

    @Bean
    public AbstractRequestLoggingFilter loggingFilter() {
        AbstractRequestLoggingFilter filter = new AbstractRequestLoggingFilter() {
            @Override
            protected void beforeRequest(HttpServletRequest httpServletRequest, String s) {
                // Do nothing
            }

            @Override
            protected void afterRequest(HttpServletRequest httpServletRequest, String s) {
                LOGGER.debug(s);
            }
        };
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setAfterMessagePrefix("\n===========================request begin================================================\n");
        filter.setAfterMessageSuffix("\n============================request end=================================================");
        return filter;
    }
}
