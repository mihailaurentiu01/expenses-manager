package com.adriandevv.services;

import com.adriandevv.domain.Expense;
import com.adriandevv.repositories.RepositoryExpenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * El servicio de los objetos de tipo gasto
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */
@Service
public class ExpenseManager {
    private RepositoryExpenses repositoryExpenses;

    /**
     * Constructor del objeto de servicio de gastos
     * @param repositoryExpenses El repositorio de los objetos de tipo gasto
     */
    @Autowired
    public ExpenseManager(RepositoryExpenses repositoryExpenses) {
        this.repositoryExpenses = repositoryExpenses;
    }

    /**
     * Obtiene los objetos de tipo gasto
     * @return Una colección de objetos de tipo gasto
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public Collection<Expense> getAllExpenses() {
        return repositoryExpenses.getAllExpenses();
    }

    /**
     * Obtiene un gasto en concreto
     * @param id El id del gasto
     * @return Un objeto de tipo gasto
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Expense getExpense(int id) {
        return repositoryExpenses.getExpense(id);
    }

    /**
     * Borra un gasto
     * @param expense El objeto de tipo gasto
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteExpense(Expense expense) {
        repositoryExpenses.deleteExpense(expense);
    }

    /**
     * Añade un gasto
     * @param expense El objeto gasto a añadir
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public void addExpense(Expense expense) {
        repositoryExpenses.addExpense(expense);
    }
}
