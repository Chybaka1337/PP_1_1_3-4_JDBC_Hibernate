package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection CONNECTION = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement st = CONNECTION.createStatement()) {
            st.execute(
                    "CREATE TABLE IF NOT EXISTS users ( " +
                    "`id` BIGINT(19) NOT NULL AUTO_INCREMENT," +
                    "`name` VARCHAR(45) NULL," +
                    "`lastName` VARCHAR(45) NULL," +
                    "`age` TINYINT(3) NULL," +
                    "PRIMARY KEY (`id`))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement st = CONNECTION.createStatement()) {
            st.execute("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {
        try (Statement st = CONNECTION.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES " +
                "WHERE TABLE_SCHEMA = 'my_db_test' " +
                "AND TABLE_NAME = 'users'"))
        {
            if (rs.next() && rs.getInt(1) == 1) {
                st.executeUpdate("DELETE FROM users");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
