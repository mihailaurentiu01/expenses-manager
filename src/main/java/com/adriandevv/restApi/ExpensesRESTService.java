package com.adriandevv.restApi;

import com.adriandevv.controllers.LoginController;
import com.adriandevv.domain.Expense;
import com.adriandevv.services.ExpenseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;


/**
 * Representa la API REST de la aplicación (Solo se puede acceder a la api si se dispone de un codigo csrf, el cuál se consigue haciendo
 * login)
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */
@RestController
@RequestMapping("/webapi")
public class ExpensesRESTService {
    private ExpenseManager expenseManager;

    /**
     * Constructor del objeto del servicio rest
     * @param expenseManager El manejador de gastos (servicio)
     */
    @Autowired
    public ExpensesRESTService(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;
    }

    /**
     * Obtiene una lista de gastos
     * @return Una colección de objetos de tipo gasto
     */
    @GetMapping("/expenses")
    public Collection<Expense> getAllExpenses() {
        return expenseManager.getAllExpenses();
    }

    /**
     * Elimina un gasto del usuario
     * @param id El id del gasto
     * @param session El objeto de tipo sesión, utilizado para establecer atributos a la hora de eliminar un gasto
     */
    @DeleteMapping("/delete/{id}")
    public void deleteExpense(@PathVariable("id") int id, HttpSession session){
        Expense expense = expenseManager.getExpense(id);
        expenseManager.deleteExpense(expense);

        session.setAttribute("itemDeleted", "Item: '" + expense.getName() + "' deleted");
    }

    /**
     * Añade un gasto para el usuario
     * @param expense El objeto de tipo gasto
     * @param session El objeto de tipo sesión, utilizado para establecer atributos a la hora de añadir un gasto
     */
    @PostMapping("/new")
    public void addExpense(@RequestBody Expense expense, HttpSession session){
        expense.setUser(LoginController.getUser());
        expenseManager.addExpense(expense);

        session.setAttribute("newItem", "Item: '" + expense.getName() + "' added");
    }

    /**
     * Obtiene el usuario actual
     * @return El usuario logado actual
     */
    private String getUsername(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }
}
