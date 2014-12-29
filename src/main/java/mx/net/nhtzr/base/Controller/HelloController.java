package mx.net.nhtzr.base.Controller;

import mx.net.nhtzr.base.Annotation.EndsFlow;
import mx.net.nhtzr.base.Service.NothingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    private final Logger log = LoggerFactory.getLogger(HelloController.class);
    private final NothingService slowService;
    private final NothingService asyncService;

    @Autowired
    public HelloController(@Qualifier("SlowService") NothingService slowService, @Qualifier("AsyncService") NothingService asyncService) {
        this.slowService = slowService;
        this.asyncService = asyncService;
    }

    @EndsFlow
    @RequestMapping(value = "/hello/{name:.*}", method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {
        log.info("#hello() starts");
        asyncService.doNothing();
        return new ModelAndView("index", "name", name);
    }

    @RequestMapping("/hello-slow/{name:.*}")
    public ModelAndView helloSlow(@PathVariable("name") String name) {
        log.info("#hello() starts");
        slowService.doNothing();
        return new ModelAndView("index", "name", name);
    }

    @RequestMapping("/")
    public String index() {
        return "forward:/hello/world";
    }

}
