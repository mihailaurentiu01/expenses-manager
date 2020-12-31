package com.adriandevv.repositories;

import com.adriandevv.domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Representa el repositorio de usuarios
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */
@Repository
public class UserRepository {
    private DataSource dataSource;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Construye un objeto de tipo Repositorio de usuarios
     * @param dataSource El origen de datos.
     * @param bCryptPasswordEncoder El encriptador de contraseñas
     */
    @Autowired
    public UserRepository(DataSource dataSource, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.dataSource = dataSource;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Comprueba si un usuario existe haciendo la comprobación en base a su nombre de usuario
     * @param username El nombre de usuario
     * @return true si el usuario existe o false en caso contrario
     * @throws SQLException Lanzada en caso de existir algún problema con SQL
     */
    public boolean userExistsUsername(String username) throws SQLException {
        Connection connection = dataSource.getConnection();

        String query = "SELECT username FROM users WHERE username = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            if (resultSet.getString("username").equals(username)) return true;
        }

        return false;
    }

    /**
     * Comprueba si un usuario existe haciendo la comprobación en base a su email
     * @param email El correo del usuario
     * @return true si el correo ya existe o false en caso contrario
     * @throws SQLException Lanzada en caso de existir algún problema con SQL
     */
    public boolean userExistsEmail(String email) throws SQLException {
        Connection connection = dataSource.getConnection();

        String query = "SELECT email FROM users WHERE email = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            if (resultSet.getString("email").equals(email)) return true;
        }

        return false;
    }

    /**
     * Guarda el objeto usuario en la base de datos
     * @param user El DTO del usuario
     * @throws SQLException Lanzada en caso de existir algún problema con SQL
     */
    public void save(UserDTO user) throws SQLException {
        Connection connection = dataSource.getConnection();

        String query = "INSERT INTO users VALUES(?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, 0);
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, bCryptPasswordEncoder.encode(user.getPassword()));
        preparedStatement.setInt(4, 1);
        preparedStatement.setString(5, user.getEmail());

        preparedStatement.executeUpdate();

        connection.close();

        this.setRole();
    }

    /**
     * Establece el rol del último usuario añadido
     * @throws SQLException Lanzada en caso de existir algún problema con SQL
     */
    private void setRole() throws SQLException {
        Connection connection = dataSource.getConnection();
        String query = "INSERT INTO roles VALUES(?, (SELECT MAX(id) FROM users), ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, 0);
        preparedStatement.setString(2, "ROLE_USER");

        preparedStatement.executeUpdate();
    }
}
