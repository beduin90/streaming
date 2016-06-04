package com.appstreaming.controllers;




        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

    @RequestMapping( value =" / " )
    public String  home()
    {
        return "test";
    }
}
