package util;

import dao.UserDAO;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;

import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {
    public static UserDAO getUserDAO() {
        Properties property = new Properties();
        try {
            property.load(UserDaoFactory.class.getClassLoader().getResourceAsStream(
                    "config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String daotype = property.getProperty("daotype");
        if (daotype.equalsIgnoreCase("UserHibernateDAO")) {
            return UserHibernateDAO.getInstance();
        }
        if (daotype.equalsIgnoreCase("UserJdbcDAO")){
            return UserJdbcDAO.getInstance();
        }
        return null;
    }
}
