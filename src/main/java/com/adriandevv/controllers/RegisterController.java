package com.adriandevv.controllers;

import com.adriandevv.domain.UserDTO;
import com.adriandevv.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.SQLException;

/**
 * Controlador utilizado para registrar un nuevo usuario
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    private UserService userService;

    /**
     * Constructor del controlador
     * @param userService El servicio de usuarios
     */
    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Comprueba los valores que llegan desde el formulario
     * @param dataBinder El objeto de WebDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    /**
     * Método encargado del registro del usuario
     * @param userDTO El objeto del usuario
     * @param model El modelo para pasar info a la vista
     * @param loggedUser El objeto del usuario logado
     * @return La vista a la página de registro
     */
    @GetMapping
    public String register(@ModelAttribute UserDTO userDTO, Model model, Principal loggedUser){
        if (loggedUser != null){
            return "./index";
        }

        model.addAttribute("userDTO", userDTO);

        return "register";
    }

    /**
     * Método encargado de guardar un usuario en la base de datos
     * @param userDTO El objeto del usuario
     * @param bindingResult objeto utilizado para añadir errores de los campos de registro (en caso de que existan)
     * @return Vista a registro o a login
     */
    @PostMapping
    public String save(@Valid UserDTO userDTO, BindingResult bindingResult){
        try {
            if (userService.userExistsEmail(userDTO.getEmail())){
                bindingResult.addError(new FieldError("userDTO", "email", "Email address " +
                        "already taken!"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if (userService.userExistsUsername(userDTO.getUsername())){
                bindingResult.addError(new FieldError("userDTO", "username", "Username address " +
                        "already taken!"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (userDTO.getPassword() != null && userDTO.getPassword() != null){
            if (!userDTO.getPassword().equals(userDTO.getRpassword())){
                bindingResult.addError(new FieldError("userDTO","rpassword",
                        "Passwords must match"));
            }
        }

        if (bindingResult.hasErrors()){
            return "register";
        }

        try {
            userService.save(userDTO);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "redirect:/login";
    }
}
