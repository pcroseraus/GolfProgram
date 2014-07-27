/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.dataaccess;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author roserp
 */
@Repository("contactDao")
@Transactional
public class ContactDaoImpl implements ContactDao{
    
    //private Log log = LogFactory.getLog(ContactDaoImpl.class)
    private SessionFactory sessionFactory;
    
            
    private SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    
    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Contact> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Contact c").list();
    }

    @Override
    public List<Contact> findAllWithDetail() {
        return sessionFactory.getCurrentSession().getNamedQuery(
                                        "Contact.findAllWithDetail").list();
        
    }

    @Override
    public Contact findById(Long id) {
        return (Contact)sessionFactory.getCurrentSession().
                getNamedQuery("Contact.findById").setParameter("id", id).
                uniqueResult();
    }

    @Override
    public Contact save(Contact contact) {
        
        sessionFactory.getCurrentSession().saveOrUpdate(contact);
        return contact;
    }
    @Override
    public void delete(Contact contact) {
        sessionFactory.getCurrentSession().delete(contact);
    }
    
}
