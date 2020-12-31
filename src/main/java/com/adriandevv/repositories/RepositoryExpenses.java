package com.adriandevv.repositories;

import com.adriandevv.controllers.LoginController;
import com.adriandevv.domain.Expense;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Representa el repositorio de gastos
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */
@Repository
public class RepositoryExpenses {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Consigue todas las entidades de tipo gasto
     * @return Una colección de entidades de tipo gasto
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public Collection<Expense> getAllExpenses(){
        TypedQuery<Expense> expenseTypedQuery = entityManager.createQuery("SELECT e FROM Expense e WHERE e.user = :username", Expense.class);
        expenseTypedQuery.setParameter("username", LoginController.getUser());
        return expenseTypedQuery.getResultList();
    }

    /**
     * Obtiene un objeto de tipo gasto
     * @param id El id del gasto
     * @return Objeto de tipo gasto
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Expense getExpense(int id){
        return entityManager.find(Expense.class, id);
    }

    /**
     * Elimina el gasto
     * @param expense El objeto de tipo gasto
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteExpense(Expense expense){
        entityManager.remove(entityManager.merge(expense));
    }

    /**
     * Añade un gasto al usuario
     * @param expense Objeto de tipo gasto
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public void addExpense(Expense expense){
        entityManager.persist(expense);
    }
}
