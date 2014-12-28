package mx.net.nhtzr.base.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("AsyncService")
public class AsyncNothingService implements NothingService {

    private final Logger log = LoggerFactory.getLogger(AsyncNothingService.class);

    @Async
    @Override
    public void doNothing() {
        try {
            log.trace("AsyncService#doNothing() starts");
            Thread.sleep(5000);
            log.trace("AsyncService#doNothing() ends");
        } catch (InterruptedException e) {
            log.error("AsyncService#doNothing() error", e);
        }
    }
}
