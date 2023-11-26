/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.IDao;
import entities.Employe;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Lenovo
 */
public class EmployeService implements IDao<Employe>{

    @Override
    public boolean create(Employe o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Employe o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Employe o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.flush();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Employe getById(int id) {
        Employe machine  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        machine  = (Employe) session.get(Employe.class, id);
        session.getTransaction().commit();
        return machine;
    }

    @Override
    public List<Employe> getAll() {
        List <Employe> machines = null;
      
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        machines  = session.createQuery("from Employe").list();
        session.getTransaction().commit();
        return machines;
    }
    
    public List<Object[]> nbemployes(){
        List<Object[]> employes = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employes  = session.createQuery("select count(e.service), e.service from Employe e group by e.service").list();
        session.getTransaction().commit();
        return employes;
    }
} 
    
