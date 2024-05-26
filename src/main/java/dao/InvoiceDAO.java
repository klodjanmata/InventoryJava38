package dao;

import dbconnection.HibernateUtils;
import entities.Invoice;
import entities.Product;
import entities.Sale;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class InvoiceDAO {
    public void save(Invoice invoice) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(invoice);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Invoice> findAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Product").list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
