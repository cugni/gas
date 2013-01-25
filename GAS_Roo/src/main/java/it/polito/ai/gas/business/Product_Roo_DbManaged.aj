// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import it.polito.ai.gas.business.Product;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect Product_Roo_DbManaged {
    
    @Column(name = "name", length = 30)
    @NotNull
    private String Product.name;
    
    @Column(name = "cost")
    @NotNull
    private Float Product.cost;
    
    @Column(name = "units", length = 20)
    @NotNull
    private String Product.units;
    
    @Column(name = "quantity")
    @NotNull
    private Float Product.quantity;
    
    @Column(name = "producer")
    @NotNull
    private Integer Product.producer;
    
    @Column(name = "description", length = 50)
    @NotNull
    private String Product.description;
    
    @Column(name = "dimensions", length = 10)
    @NotNull
    private String Product.dimensions;
    
    @Column(name = "transport_cost")
    @NotNull
    private Float Product.transportCost;
    
    @Column(name = "stock_quantity")
    @NotNull
    private Float Product.stockQuantity;
    
    @Column(name = "min_to_buy_order")
    private Float Product.minToBuyOrder;
    
    @Column(name = "min_to_buy_user")
    @NotNull
    private Float Product.minToBuyUser;
    
    @Column(name = "max_to_buy_user")
    @NotNull
    private Float Product.maxToBuyUser;
    
    @Column(name = "available_from")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date Product.availableFrom;
    
    @Column(name = "available_to")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date Product.availableTo;
    
    public String Product.getName() {
        return name;
    }
    
    public void Product.setName(String name) {
        this.name = name;
    }
    
    public Float Product.getCost() {
        return cost;
    }
    
    public void Product.setCost(Float cost) {
        this.cost = cost;
    }
    
    public String Product.getUnits() {
        return units;
    }
    
    public void Product.setUnits(String units) {
        this.units = units;
    }
    
    public Float Product.getQuantity() {
        return quantity;
    }
    
    public void Product.setQuantity(Float quantity) {
        this.quantity = quantity;
    }
    
    public Integer Product.getProducer() {
        return producer;
    }
    
    public void Product.setProducer(Integer producer) {
        this.producer = producer;
    }
    
    public String Product.getDescription() {
        return description;
    }
    
    public void Product.setDescription(String description) {
        this.description = description;
    }
    
    public String Product.getDimensions() {
        return dimensions;
    }
    
    public void Product.setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }
    
    public Float Product.getTransportCost() {
        return transportCost;
    }
    
    public void Product.setTransportCost(Float transportCost) {
        this.transportCost = transportCost;
    }
    
    public Float Product.getStockQuantity() {
        return stockQuantity;
    }
    
    public void Product.setStockQuantity(Float stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    public Float Product.getMinToBuyOrder() {
        return minToBuyOrder;
    }
    
    public void Product.setMinToBuyOrder(Float minToBuyOrder) {
        this.minToBuyOrder = minToBuyOrder;
    }
    
    public Float Product.getMinToBuyUser() {
        return minToBuyUser;
    }
    
    public void Product.setMinToBuyUser(Float minToBuyUser) {
        this.minToBuyUser = minToBuyUser;
    }
    
    public Float Product.getMaxToBuyUser() {
        return maxToBuyUser;
    }
    
    public void Product.setMaxToBuyUser(Float maxToBuyUser) {
        this.maxToBuyUser = maxToBuyUser;
    }
    
    public Date Product.getAvailableFrom() {
        return availableFrom;
    }
    
    public void Product.setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }
    
    public Date Product.getAvailableTo() {
        return availableTo;
    }
    
    public void Product.setAvailableTo(Date availableTo) {
        this.availableTo = availableTo;
    }
    
}
