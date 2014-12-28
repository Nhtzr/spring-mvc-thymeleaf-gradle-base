package mx.net.nhtzr.base.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("SlowService")
public class SlowNothingService implements NothingService {

    private final Logger log = LoggerFactory.getLogger(SlowNothingService.class);

    @Override
    public void doNothing() {
        try {
            log.trace("SlowService#doNothing() starts");
            Thread.sleep(5000);
            log.trace("SlowService#doNothing() ends");
        } catch (InterruptedException e) {
            log.error("SlowService#doNothing() error", e);
        }
    }
}
