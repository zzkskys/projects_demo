package cn.zzk.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleService.class);

    @Scheduled(fixedRate = 1_000)
    public void test1() {
        LOGGER.info("hello");
    }
}
