// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import it.polito.ai.gas.business.Product;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect Product_Roo_Jpa_Entity {
    
    declare @type: Product: @Entity;
    
    declare @type: Product: @Table(name = "product");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer Product.id;
    
    public Integer Product.getId() {
        return this.id;
    }
    
    public void Product.setId(Integer id) {
        this.id = id;
    }
    
}
