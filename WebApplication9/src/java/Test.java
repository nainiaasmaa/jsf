
import util.HibernateUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entities.Employe;
import entities.Service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author Lachgar
 */
public class Test {
    public static void main(String[] args) {
        // Create SessionFactory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        SessionFactory factory1 = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employe.class).buildSessionFactory();
        

        // Create Session
        Session session = factory.getCurrentSession();

        try {
            // Begin transaction
            session.beginTransaction();

            // Commit the transaction
            session.getTransaction().commit();

            System.out.println("Les tables sont crées!");
        } finally {
            factory.close();
        }
    }
}
    
