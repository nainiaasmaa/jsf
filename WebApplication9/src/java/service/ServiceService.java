/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.IDao;
import entities.Service;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Lenovo
 */
public class ServiceService implements IDao<Service>{

    @Override
    public boolean create(Service o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Service o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Service o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.flush();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Service getById(int id) {
        Service machine  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        machine  = (Service) session.get(Service.class, id);
        session.getTransaction().commit();
        return machine;
    }

    @Override
    public List<Service> getAll() {
        List <Service> machines = null;
      
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        machines  = session.createQuery("from Service").list();
        session.getTransaction().commit();
        return machines;
    }
}
    
