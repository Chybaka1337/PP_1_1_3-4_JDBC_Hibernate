package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соединения с БД
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/my_db_test";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DIALECT = "org.hibernate.dialect.MySQL5Dialect";
    private static final SessionFactory SESSION = buildSessionFactory();

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (ClassNotFoundException| SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static SessionFactory buildSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = getConfig()
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("error");
        }
        return sessionFactory;
    }

    private static Configuration getConfig() {
        Properties prop = new Properties();
        prop.setProperty(Environment.DIALECT, DIALECT);
        prop.setProperty(Environment.DRIVER, DRIVER);
        prop.setProperty(Environment.URL, URL);
        prop.setProperty(Environment.USER, LOGIN);
        prop.setProperty(Environment.PASS, PASSWORD);
        prop.setProperty(Environment.HBM2DDL_AUTO, "update");
        prop.setProperty(Environment.SHOW_SQL, "false");
        return new Configuration().setProperties(prop);
    }

    public static SessionFactory getSessionFactory() {
        return SESSION;
    }
}
