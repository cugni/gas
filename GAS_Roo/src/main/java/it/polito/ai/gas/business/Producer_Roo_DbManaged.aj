// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import it.polito.ai.gas.business.Producer;
import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.User;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

privileged aspect Producer_Roo_DbManaged {
    
    @OneToMany(mappedBy = "producer")
    private Set<Product> Producer.products;
    
    @ManyToOne
    @JoinColumn(name = "delegate", referencedColumnName = "id", nullable = false)
    private User Producer.delegate;
    
    @Column(name = "company_name", length = 40)
    private String Producer.companyName;
    
    @Column(name = "description", length = 40)
    private String Producer.description;
    
    @Column(name = "contact", length = 20)
    private String Producer.contact;
    
    @Column(name = "address", length = 40)
    private String Producer.address;
    
    @Column(name = "phone_number", length = 15)
    private String Producer.phoneNumber;
    
    @Column(name = "fax_number", length = 15)
    private String Producer.faxNumber;
    
    @Column(name = "email", length = 30)
    private String Producer.email;
    
    @Column(name = "payment_mode", length = 20)
    private String Producer.paymentMode;
    
    public Set<Product> Producer.getProducts() {
        return products;
    }
    
    public void Producer.setProducts(Set<Product> products) {
        this.products = products;
    }
    
    public User Producer.getDelegate() {
        return delegate;
    }
    
    public void Producer.setDelegate(User delegate) {
        this.delegate = delegate;
    }
    
    public String Producer.getCompanyName() {
        return companyName;
    }
    
    public void Producer.setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public String Producer.getDescription() {
        return description;
    }
    
    public void Producer.setDescription(String description) {
        this.description = description;
    }
    
    public String Producer.getContact() {
        return contact;
    }
    
    public void Producer.setContact(String contact) {
        this.contact = contact;
    }
    
    public String Producer.getAddress() {
        return address;
    }
    
    public void Producer.setAddress(String address) {
        this.address = address;
    }
    
    public String Producer.getPhoneNumber() {
        return phoneNumber;
    }
    
    public void Producer.setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String Producer.getFaxNumber() {
        return faxNumber;
    }
    
    public void Producer.setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }
    
    public String Producer.getEmail() {
        return email;
    }
    
    public void Producer.setEmail(String email) {
        this.email = email;
    }
    
    public String Producer.getPaymentMode() {
        return paymentMode;
    }
    
    public void Producer.setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
    
}
