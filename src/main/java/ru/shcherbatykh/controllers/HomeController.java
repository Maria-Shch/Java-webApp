package ru.shcherbatykh.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/hello")
    public String index(@RequestParam(value = "name", required=false) String name, 
            @RequestParam(value = "surname", required=false) String surname, 
            Model model){
        String message = "hello, " + name + " " + surname;
        model.addAttribute("message", message);
        System.out.println(message);
        return "hello";
    }
    @GetMapping("/bye")
    public String bye(@RequestParam(value = "name", required=false) String name, 
            @RequestParam(value = "surname", required=false) String surname,
            Model model){
        String message = "bye, " + name + " " + surname;
        model.addAttribute("message", message);
        System.out.println(message);
        return "bye";
    }
    @GetMapping("/calculator")
    public String calculator(@RequestParam("a") double a, @RequestParam("b") double b, 
            @RequestParam("action") String action, Model model){
        double result;
        switch(action){
            case "addition": result = a+b; break;
            case "subtraction": result = a-b; break;
            case "multiplication": result = a*b; break;
            case "division": result = a/b; break;
            default: result = 0;
        }
        model.addAttribute("result", result);
        return "calculator";
    }
}
