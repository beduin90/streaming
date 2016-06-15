package com.appstreaming.controllers;




        import org.springframework.stereotype.Controller;
        import org.springframework.ui.ModelMap;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StreamController {

    @RequestMapping( value =" / " )
    public String  home()
    {
        return "test";
    }


    @RequestMapping(value="/login"  )
    public String printHello() {
        return "login";
    }
}
