package com.cntct.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import com.cntct.model.Contact;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.dropwizard.hibernate.AbstractDAO;

@Singleton
public class ContactDAO extends AbstractDAO<Contact> {

  @Inject
  public ContactDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public Contact get(Long id) {
    return super.get(id);
  }

  public Contact create(Contact contact) {
    contact.setCreatedAt(System.currentTimeMillis());
    contact.setUpdatedAt(System.currentTimeMillis());
    return persist(contact);
  }
  
  public Contact update(Contact contact) {
    contact.setUpdatedAt(System.currentTimeMillis());
    return persist(contact);
  }
  public List<Contact> list(int skip, int count) {
    Query<Contact> query = currentSession().createQuery("select contact from Contact contact", Contact.class);
    query.setFirstResult(skip);
    query.setMaxResults(count);
    return super.list(query);
  }
  
  public void delete(Contact contact){
    currentSession().delete(contact);
  }
}
