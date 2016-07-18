package com.appstreaming.controllers;

        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;

        import javax.swing.*;

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




    public static void createFrame()
    {
        JFrame aWindow = new JFrame("This is the Window Title");
        int windowWidth = 400;           // Window width in pixels
        int windowHeight = 150;          // Window height in pixels
        aWindow.setBounds(50, 100,       // Set position
                windowWidth, windowHeight);  // and size
        aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aWindow.setVisible(true);        // Display the window
    }




}



