package com.adriandevv.controllers;

import com.adriandevv.services.ExpenseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Este controlador maneja las entidades de tipo gasto (expense)
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */
@Controller
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseManager serviceExpenses;

    /**
     * Constructor del controlador
     * @param serviceExpenses El servicio de las entidades de tipo gasto
     */
    @Autowired
    public ExpenseController(ExpenseManager serviceExpenses) {
        this.serviceExpenses = serviceExpenses;
    }

    // MÉTODOS TRIVIALES QUE DEVUELVEN VISTAS

    @GetMapping("/manage")
    public String manageExpenses(){
        return "manage";
    }

    @GetMapping("/add")
    public String addExpense(){
        return "add";
    }


}
