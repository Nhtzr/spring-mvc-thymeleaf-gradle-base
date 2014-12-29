package mx.net.nhtzr.base.Controller;

import mx.net.nhtzr.base.Entity.HelloEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class FormExampleController {

    private final Logger log = LoggerFactory.getLogger(FormExampleController.class);

    @RequestMapping("/form/")
    public ModelAndView index() {
        return new ModelAndView("form", "helloEntity", new HelloEntity());
    }

    @RequestMapping(value = "/date", method = RequestMethod.POST)
    @ResponseBody
    public HelloEntity postDate(@RequestParam("timestamp") Date timestamp) {
        log.trace("#postDate() starts");
        log.debug("#postDate() got timestamp {}", new SimpleDateFormat("YYYY").format(timestamp));

        HelloEntity entity = new HelloEntity();
        entity.setInit(timestamp);
        entity.setEnd(new Date());
        entity.setMessage("Hello");

        return entity;
    }

    @RequestMapping(value = "/entity", method = RequestMethod.POST)
    @ResponseBody
    public HelloEntity postEntity(@ModelAttribute HelloEntity entity) {
        if (entity == null) {
            log.debug("#postEntity() got null");
            return null;
        }

        log.trace("#postEntity() starts");
        log.debug("#postEntity() got entity {}", entity);
        return entity;
    }

    @RequestMapping(value = "/ajax", method = RequestMethod.POST)
    @ResponseBody
    public HelloEntity ajaxEntity(@RequestBody HelloEntity entity) {
        return entity;
    }

}
