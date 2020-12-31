package com.adriandevv.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Representa la acción de logout
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {

    /**
     * Método que se encarga de hacer logout de la cuenta del usuario mediante la eliminación de cookies y sesión
     * @param request El objeto de la petición
     * @param response El objeto de la respuesta
     * @return Redirige a la vista de login
     */
    @GetMapping
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login";
    }
}
