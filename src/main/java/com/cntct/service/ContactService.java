package com.cntct.service;

import java.util.Date;
import java.util.List;
import com.cntct.dao.ContactDAO;
import com.cntct.model.Contact;
import com.cntct.request.CreateContactRequest;
import com.cntct.response.ContactResponse;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

public class ContactService {

  private ContactDAO contactDAO;

  @Inject
  public ContactService(ContactDAO contactDAO) {
    this.contactDAO = contactDAO;
  }

  public ContactResponse create(CreateContactRequest createContactRequest){
    Contact contact = Contact.builder()
                             .email(createContactRequest.getEmail())
                             .name(createContactRequest.getName())
                             .type(createContactRequest.getType())
                             .phone(createContactRequest.getPhone())
                             .build();
    
    contact = contactDAO.create(contact);
    return createContactResponse(contact);
  }
  
  public List<ContactResponse> list(int skip, int count) {
    List<ContactResponse> contactResponse = Lists.newArrayList();
    List<Contact> contacts = contactDAO.list(skip, count);

    for (Contact contact : contacts) {
      contactResponse.add(createContactResponse(contact));
    }

    return contactResponse;
  }

  public ContactResponse get(Long id) {
    return createContactResponse(fetch(id));
  }
  
  public ContactResponse updateName(Long id, String name){
    Contact contact = fetch(id);
    contact.setName(name);
    contact = contactDAO.update(contact);
    return createContactResponse(contact);
  }
  
  public ContactResponse updatePhone(Long id, String phone){
    Contact contact = fetch(id);
    contact.setPhone(phone);
    contact = contactDAO.update(contact);
    return createContactResponse(contact);
  }

  public ContactResponse delete(Long id){
    Contact contact = fetch(id);
    contactDAO.delete(contact);
    return createContactResponse(contact);
  }

  private Contact fetch(Long id) {
    return contactDAO.get(id);
  }
  
  private ContactResponse createContactResponse(Contact contact) {
    ContactResponse contactResponse = ContactResponse.builder()
                                                     .id(contact.getId())
                                                     .email(contact.getEmail())
                                                     .name(contact.getName())
                                                     .type(contact.getType())
                                                     .phone(contact.getPhone())
                                                     .createdAt(new Date(contact.getCreatedAt()))
                                                     .updatedAt(new Date(contact.getUpdatedAt()))
                                                     .build();
    return contactResponse;
  }
}
