package com.adriandevv.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Controlador utilizado para redirigir a index
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */
@Controller
@RequestMapping("/")
public class RedirectController {

    @GetMapping
    public RedirectView redirect(){
        return new RedirectView("./index");
    }
}
