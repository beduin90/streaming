package com.appstreaming.controllers;

        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StreamController {

    @RequestMapping( value =" / " )
    public String  home()
    {
        return "index";
    }


    @RequestMapping(value="/login"  )
    public String login() {
        return "login";
    }

    @RequestMapping(value="/streams"  )
    public String getSTREAM() {
        return "streams";
    }



}
