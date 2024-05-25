package dao;

import dbconnection.HibernateUtils;
import entities.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDAO {

    public void save(Product product) {
        Transaction transaction = null;
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        }catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Product> getProducts() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Product").list();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
