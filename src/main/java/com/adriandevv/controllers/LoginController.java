package com.adriandevv.controllers;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletRequest;
import java.security.Principal;
import java.util.Map;

/**
 * Controlador que representa la acción de login
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private static User user;

    /**
     * Método encargado de hacer login del usuario
     * @param request El objeto de la petición
     * @param model El modelo, se usa para pasar información a la vista
     * @param loggedUser El objeto del usuario
     * @return La vista
     */
    @GetMapping
    public String login(ServletRequest request, Model model, Principal loggedUser){
        if (loggedUser != null){
            return "./index";
        }

        Map<String, String[]> paramMap = request.getParameterMap();

        if (paramMap.containsKey("error")) {
            model.addAttribute("loginError", "Bad credentials");
        }

        return "login";
    }

    /**
     * Establece el usuario actual autenticado
     */
    private static void setUser(){
        user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * Obtiene el usuario actual
     * @return El username del usuario
     */
    public static String getUser(){
        setUser();
        return user.getUsername();
    }
}
