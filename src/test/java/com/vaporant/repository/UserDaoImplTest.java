package com.vaporant.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vaporant.model.UserBean;

@ExtendWith(MockitoExtension.class)
class UserDaoImplTest {

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private UserDaoImpl userDao;

    @BeforeEach
    void setUp() throws SQLException {
        // Common setup if needed, but usually done in tests
    }

    @Test
    void testFindByCredSuccess() throws SQLException {
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(resultSet.isBeforeFirst()).thenReturn(true);
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        when(resultSet.getString("email")).thenReturn("test@test.com");
        when(resultSet.getString("CF")).thenReturn("CF123");
        when(resultSet.getString("nome")).thenReturn("Test");
        when(resultSet.getString("cognome")).thenReturn("User");
        when(resultSet.getString("numTelefono")).thenReturn("123456");
        when(resultSet.getInt("ID")).thenReturn(1);
        when(resultSet.getString("psw")).thenReturn("password");
        when(resultSet.getString("tipo")).thenReturn("user");
        when(resultSet.getDate("dataNascita")).thenReturn(Date.valueOf(LocalDate.now()));

        UserBean user = userDao.findByCred("test@test.com", "password");

        assertNotNull(user);
        assertEquals("test@test.com", user.getEmail());
        assertEquals("user", user.getTipo());

        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).setString(1, "test@test.com");
        verify(preparedStatement).setString(2, "password");
    }

    @Test
    void testFindByCredNotFound() throws SQLException {
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(resultSet.isBeforeFirst()).thenReturn(false);

        UserBean user = userDao.findByCred("wrong", "wrong");

        assertNull(user);
    }

    @Test
    void testSaveUser() throws SQLException {
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        UserBean user = new UserBean();
        user.setNome("Test");
        user.setCognome("User");
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setCodF("CF123");
        user.setNumTelefono("123456");
        user.setDataNascita(LocalDate.now());
        user.setTipo("user");

        int result = userDao.saveUser(user);

        assertEquals(1, result);
        verify(preparedStatement).setString(1, "Test");
        verify(preparedStatement).setString(3, "test@test.com");
    }

    @Test
    void testSQLException() throws SQLException {
        when(dataSource.getConnection()).thenThrow(new SQLException("DB Error"));

        assertThrows(SQLException.class, () -> {
            userDao.findByCred("test", "test");
        });
    }
}
