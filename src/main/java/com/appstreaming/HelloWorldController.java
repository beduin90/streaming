package com.appstreaming;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by beduin90 on 04.06.2016.
 */
@Controller
public class HelloWorldController {

    @RequestMapping( value =" / " )
    public String  home()
    {
        return "text";
    }
}
