package au.com.developer.api.service;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WelcomeService {

    @Value("${changeme.welcome.text}")
    private String welcomeMessage;

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    @Scheduled(cron = "${changeme.scheduledTaskThatRunsOnOneServer.cron}")
    @SchedulerLock(name = "scheduledTaskThatRunsOnOneServer", lockAtLeastFor = "1M", lockAtMostFor = "2M")
    public void scheduledTaskThatRunsOnOneServer() {
        log.info("Running scheduledTaskThatRunsOnOneServer");
    }
}
