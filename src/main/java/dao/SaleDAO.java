package dao;

import dbconnection.HibernateUtils;
import entities.Sale;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SaleDAO {

    public void save(Sale sale) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(sale);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
