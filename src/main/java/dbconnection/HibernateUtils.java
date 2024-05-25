package dbconnection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sessionFactory;

    static {
        try{
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }catch(Throwable e){
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
