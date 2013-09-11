// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.DeliveryWithdrawal;
import it.polito.ai.gas.business.Event;
import it.polito.ai.gas.business.Producer;
import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.Proposal;
import it.polito.ai.gas.business.PurchaseRequest;
import it.polito.ai.gas.business.PurchaseRequestPart;
import it.polito.ai.gas.business.User;
import it.polito.ai.gas.controller.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    public Converter<DeliveryWithdrawal, String> ApplicationConversionServiceFactoryBean.getDeliveryWithdrawalToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<it.polito.ai.gas.business.DeliveryWithdrawal, java.lang.String>() {
            public String convert(DeliveryWithdrawal deliveryWithdrawal) {
                return new StringBuilder().append(deliveryWithdrawal.getDeliveryDate()).append(' ').append(deliveryWithdrawal.getWithdrawalDate()).toString();
            }
        };
    }
    
    public Converter<Integer, DeliveryWithdrawal> ApplicationConversionServiceFactoryBean.getIdToDeliveryWithdrawalConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, it.polito.ai.gas.business.DeliveryWithdrawal>() {
            public it.polito.ai.gas.business.DeliveryWithdrawal convert(java.lang.Integer id) {
                return DeliveryWithdrawal.findDeliveryWithdrawal(id);
            }
        };
    }
    
    public Converter<String, DeliveryWithdrawal> ApplicationConversionServiceFactoryBean.getStringToDeliveryWithdrawalConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, it.polito.ai.gas.business.DeliveryWithdrawal>() {
            public it.polito.ai.gas.business.DeliveryWithdrawal convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), DeliveryWithdrawal.class);
            }
        };
    }
    
    public Converter<Event, String> ApplicationConversionServiceFactoryBean.getEventToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<it.polito.ai.gas.business.Event, java.lang.String>() {
            public String convert(Event event) {
                return new StringBuilder().append(event.getDate()).toString();
            }
        };
    }
    
    public Converter<Integer, Event> ApplicationConversionServiceFactoryBean.getIdToEventConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, it.polito.ai.gas.business.Event>() {
            public it.polito.ai.gas.business.Event convert(java.lang.Integer id) {
                return Event.findEvent(id);
            }
        };
    }
    
    public Converter<String, Event> ApplicationConversionServiceFactoryBean.getStringToEventConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, it.polito.ai.gas.business.Event>() {
            public it.polito.ai.gas.business.Event convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Event.class);
            }
        };
    }
    
    public Converter<Producer, String> ApplicationConversionServiceFactoryBean.getProducerToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<it.polito.ai.gas.business.Producer, java.lang.String>() {
            public String convert(Producer producer) {
                return new StringBuilder().append(producer.getUsername()).append(' ').append(producer.getPassword()).append(' ').append(producer.getName()).append(' ').append(producer.getSurname()).toString();
            }
        };
    }
    
    public Converter<Integer, Producer> ApplicationConversionServiceFactoryBean.getIdToProducerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, it.polito.ai.gas.business.Producer>() {
            public it.polito.ai.gas.business.Producer convert(java.lang.Integer id) {
                return Producer.findProducer(id);
            }
        };
    }
    
    public Converter<String, Producer> ApplicationConversionServiceFactoryBean.getStringToProducerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, it.polito.ai.gas.business.Producer>() {
            public it.polito.ai.gas.business.Producer convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Producer.class);
            }
        };
    }
    
    public Converter<Product, String> ApplicationConversionServiceFactoryBean.getProductToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<it.polito.ai.gas.business.Product, java.lang.String>() {
            public String convert(Product product) {
                return new StringBuilder().append(product.getName()).append(' ').append(product.getCost()).append(' ').append(product.getUnits()).append(' ').append(product.getQuantity()).toString();
            }
        };
    }
    
    public Converter<Integer, Product> ApplicationConversionServiceFactoryBean.getIdToProductConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, it.polito.ai.gas.business.Product>() {
            public it.polito.ai.gas.business.Product convert(java.lang.Integer id) {
                return Product.findProduct(id);
            }
        };
    }
    
    public Converter<String, Product> ApplicationConversionServiceFactoryBean.getStringToProductConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, it.polito.ai.gas.business.Product>() {
            public it.polito.ai.gas.business.Product convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Product.class);
            }
        };
    }
    
    public Converter<Proposal, String> ApplicationConversionServiceFactoryBean.getProposalToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<it.polito.ai.gas.business.Proposal, java.lang.String>() {
            public String convert(Proposal proposal) {
                return new StringBuilder().append(proposal.getStartDate()).append(' ').append(proposal.getEndDate()).toString();
            }
        };
    }
    
    public Converter<Integer, Proposal> ApplicationConversionServiceFactoryBean.getIdToProposalConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, it.polito.ai.gas.business.Proposal>() {
            public it.polito.ai.gas.business.Proposal convert(java.lang.Integer id) {
                return Proposal.findProposal(id);
            }
        };
    }
    
    public Converter<String, Proposal> ApplicationConversionServiceFactoryBean.getStringToProposalConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, it.polito.ai.gas.business.Proposal>() {
            public it.polito.ai.gas.business.Proposal convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Proposal.class);
            }
        };
    }
    
    public Converter<PurchaseRequest, String> ApplicationConversionServiceFactoryBean.getPurchaseRequestToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<it.polito.ai.gas.business.PurchaseRequest, java.lang.String>() {
            public String convert(PurchaseRequest purchaseRequest) {
                return new StringBuilder().append(purchaseRequest.getQuantity()).toString();
            }
        };
    }
    
    public Converter<Integer, PurchaseRequest> ApplicationConversionServiceFactoryBean.getIdToPurchaseRequestConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, it.polito.ai.gas.business.PurchaseRequest>() {
            public it.polito.ai.gas.business.PurchaseRequest convert(java.lang.Integer id) {
                return PurchaseRequest.findPurchaseRequest(id);
            }
        };
    }
    
    public Converter<String, PurchaseRequest> ApplicationConversionServiceFactoryBean.getStringToPurchaseRequestConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, it.polito.ai.gas.business.PurchaseRequest>() {
            public it.polito.ai.gas.business.PurchaseRequest convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), PurchaseRequest.class);
            }
        };
    }
    
    public Converter<PurchaseRequestPart, String> ApplicationConversionServiceFactoryBean.getPurchaseRequestPartToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<it.polito.ai.gas.business.PurchaseRequestPart, java.lang.String>() {
            public String convert(PurchaseRequestPart purchaseRequestPart) {
                return new StringBuilder().append(purchaseRequestPart.getQuantity()).toString();
            }
        };
    }
    
    public Converter<Integer, PurchaseRequestPart> ApplicationConversionServiceFactoryBean.getIdToPurchaseRequestPartConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, it.polito.ai.gas.business.PurchaseRequestPart>() {
            public it.polito.ai.gas.business.PurchaseRequestPart convert(java.lang.Integer id) {
                return PurchaseRequestPart.findPurchaseRequestPart(id);
            }
        };
    }
    
    public Converter<String, PurchaseRequestPart> ApplicationConversionServiceFactoryBean.getStringToPurchaseRequestPartConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, it.polito.ai.gas.business.PurchaseRequestPart>() {
            public it.polito.ai.gas.business.PurchaseRequestPart convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), PurchaseRequestPart.class);
            }
        };
    }
    
    public Converter<User, String> ApplicationConversionServiceFactoryBean.getUserToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<it.polito.ai.gas.business.User, java.lang.String>() {
            public String convert(User user) {
                return new StringBuilder().append(user.getUsername()).append(' ').append(user.getPassword()).append(' ').append(user.getName()).append(' ').append(user.getSurname()).toString();
            }
        };
    }
    
    public Converter<Integer, User> ApplicationConversionServiceFactoryBean.getIdToUserConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, it.polito.ai.gas.business.User>() {
            public it.polito.ai.gas.business.User convert(java.lang.Integer id) {
                return User.findUser(id);
            }
        };
    }
    
    public Converter<String, User> ApplicationConversionServiceFactoryBean.getStringToUserConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, it.polito.ai.gas.business.User>() {
            public it.polito.ai.gas.business.User convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), User.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getDeliveryWithdrawalToStringConverter());
        registry.addConverter(getIdToDeliveryWithdrawalConverter());
        registry.addConverter(getStringToDeliveryWithdrawalConverter());
        registry.addConverter(getEventToStringConverter());
        registry.addConverter(getIdToEventConverter());
        registry.addConverter(getStringToEventConverter());
        registry.addConverter(getProducerToStringConverter());
        registry.addConverter(getIdToProducerConverter());
        registry.addConverter(getStringToProducerConverter());
        registry.addConverter(getProductToStringConverter());
        registry.addConverter(getIdToProductConverter());
        registry.addConverter(getStringToProductConverter());
        registry.addConverter(getProposalToStringConverter());
        registry.addConverter(getIdToProposalConverter());
        registry.addConverter(getStringToProposalConverter());
        registry.addConverter(getPurchaseRequestToStringConverter());
        registry.addConverter(getIdToPurchaseRequestConverter());
        registry.addConverter(getStringToPurchaseRequestConverter());
        registry.addConverter(getPurchaseRequestPartToStringConverter());
        registry.addConverter(getIdToPurchaseRequestPartConverter());
        registry.addConverter(getStringToPurchaseRequestPartConverter());
        registry.addConverter(getUserToStringConverter());
        registry.addConverter(getIdToUserConverter());
        registry.addConverter(getStringToUserConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}
