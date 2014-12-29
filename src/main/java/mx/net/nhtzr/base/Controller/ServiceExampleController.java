package mx.net.nhtzr.base.Controller;

import mx.net.nhtzr.base.Service.NothingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ServiceExampleController {

    private final Logger log = LoggerFactory.getLogger(ServiceExampleController.class);
    private final NothingService slowService;
    private final NothingService asyncService;

    @Autowired
    public ServiceExampleController(@Qualifier("SlowService") NothingService slowService, @Qualifier("AsyncService") NothingService asyncService) {
        this.slowService = slowService;
        this.asyncService = asyncService;
    }

    @RequestMapping(value = "/hello-fast/{name}")
    public ModelAndView helloFast(@PathVariable("name") String name) {
        log.trace("#helloFast() starts");
        asyncService.doNothing();
        log.trace("#helloFast() ends");
        return new ModelAndView("index", "name", name);
    }

    @RequestMapping("/hello-slow/{name:.*}")
    public ModelAndView helloSlow(@PathVariable("name") String name) {
        log.trace("#helloSlow() starts");
        slowService.doNothing();
        log.trace("#helloSlow() ends");
        return new ModelAndView("index", "name", name);
    }

}
