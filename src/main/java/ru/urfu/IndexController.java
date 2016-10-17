package ru.urfu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author aarkaev
 * @since 08.08.2016
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
