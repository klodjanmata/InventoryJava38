package dao;

import dbconnection.HibernateUtils;
import entities.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CategoryDAO {
    public void save(Category category){
    Transaction transaction=null;
    try(Session session = HibernateUtils.getSessionFactory().openSession()) {
        transaction = session.beginTransaction();
        session.save(category);
        transaction.commit();
    }catch(Exception e) {
        if(transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
}
}
