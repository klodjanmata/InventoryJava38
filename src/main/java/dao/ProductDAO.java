package dao;

import dbconnection.HibernateUtils;
import entities.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAO {

    public void save(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(Product product) {
        Transaction transaction = null;
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        }catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(Product product) {
        Transaction transaction = null;
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
            System.out.print("Product deleted successfully");
        }catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            System.out.println("Product cannot be deleted");
            e.printStackTrace();
        }
    }

    public List<Product> findAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Product").list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Product> findByCategory(int categoryId) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery("from Product where categoryId = :categoryId", Product.class);
            query.setParameter("categoryId", categoryId);
            return query.list();
        }
    }
}
