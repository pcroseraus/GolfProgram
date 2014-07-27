/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.dataaccess;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author roserp
 */
@Entity
@Table(name = "contact")
@NamedQueries({
    @NamedQuery(name="Contact.findAllWithDetail",
            query = "select distinct c from Contact c left join fetch c.contactTelDetails t"),
    @NamedQuery(name="Contact.findById",
            query = "select distinct c from Contact c left join fetch c.contactTelDetails t where c.id = :id")   
})
public class Contact implements Serializable{
    
    private Long id;
    private int version;
    private String firstName;
    private String lastName;
    private Date birthDate;
    
    private Set<ContactTelDetail> contactTelDetails = 
            new HashSet<ContactTelDetail>();
    
    
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="ID")
    public Long getId(){
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", version=" + version + ", firstName="
                + firstName + ", lastName=" + lastName + ", birthDate=" + 
                birthDate + '}';
    }
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "contact", cascade = CascadeType.ALL,
            orphanRemoval=true)
    public Set<ContactTelDetail> getContactTelDetails(){
        return this.contactTelDetails;
    }

    public void setContactTelDetails(Set<ContactTelDetail> contactTelDetails) {
        this.contactTelDetails = contactTelDetails;
    }
    
    public void addContactTelDetail(ContactTelDetail contactTelDetail){
        contactTelDetail.setContact(this);
        getContactTelDetails().add(contactTelDetail);
    }
    
    public void removeContactTelDetail(ContactTelDetail contactTelDetail){
        getContactTelDetails().remove(contactTelDetail);
    }
    
}