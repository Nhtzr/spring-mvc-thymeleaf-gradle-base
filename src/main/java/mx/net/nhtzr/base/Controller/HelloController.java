package mx.net.nhtzr.base.Controller;

import mx.net.nhtzr.base.Annotation.EndsFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    private final Logger log = LoggerFactory.getLogger(HelloController.class);

    @EndsFlow
    @RequestMapping(value = "/hello/{name:.*}", method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {
        log.info("#hello() starts");
        return new ModelAndView("index", "name", name);
    }

}
