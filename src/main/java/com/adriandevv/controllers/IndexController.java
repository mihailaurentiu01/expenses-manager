package com.adriandevv.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador que representa la acción de inicio, es decir, una vez autentificados en la
 * aplicación
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @GetMapping
    public String mostrarEntidades(){
        return "index";
    }

}
