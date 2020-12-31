package com.adriandevv.services;

import com.adriandevv.domain.UserDTO;
import com.adriandevv.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * El servicio de los objetos de tipo usuario
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */
@Service
public class UserService {
    private UserRepository userRepository;

    /**
     * Constructor del objeto de servicio de tipos usuario
     * @param userRepository El repositorio de usuarios
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Método delegado que comprueba si un correo existe
     * @param email El correo del usuario
     * @return Un valor booleano
     * @throws SQLException Lanzada en caso de existir algún problema con SQL
     */
    public boolean userExistsEmail(String email) throws SQLException {
        return userRepository.userExistsEmail(email);
    }

    /**
     * Método delegado que comprueba si un username existe
     * @param username El username del usuario
     * @return un valor booleano
     * @throws SQLException Lanzada en caso de existir algún problema con SQL
     */
    public boolean userExistsUsername(String username) throws SQLException {
        return userRepository.userExistsUsername(username);
    }

    /**
     * Método delegado que guarda el usuario en la base de datos
     * @param user El objeto DTO del usuario
     * @throws SQLException Lanzada en caso de existir algún problema con SQL
     */
    public void save(UserDTO user) throws SQLException {
        userRepository.save(user);
    }
}
