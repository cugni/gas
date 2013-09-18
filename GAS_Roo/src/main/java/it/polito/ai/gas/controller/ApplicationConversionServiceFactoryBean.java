package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.Producer;
import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.Proposal;
import it.polito.ai.gas.business.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;
       
/**
 * A central place to register application Converters and Formatters.
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

        @Override
        protected void installFormatters(FormatterRegistry registry) {
                super.installFormatters(registry);
                // Register application converters and formatters
        }

    public Converter<User, String> getUserToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<it.polito.ai.gas.business.User, java.lang.String>() {
            public String convert(User user) {
                return new StringBuilder().append(user.getUsername()).toString();
            }
        };
    }

    public Converter<Proposal, String> getProposalToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<it.polito.ai.gas.business.Proposal, java.lang.String>() {
            public String convert(Proposal proposal) {
                return new StringBuilder().append(proposal.getProduct()).append(" - ").append(proposal.getDelegate()).toString();
            }
        };
    }

    public Converter<Producer, String> getProducerToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<it.polito.ai.gas.business.Producer, java.lang.String>() {
            public String convert(Producer producer) {
                return new StringBuilder().append(producer.getUsername()).toString();
            }
        };
    }


    public Converter<Product, String> getProductToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<it.polito.ai.gas.business.Product, java.lang.String>() {
            public String convert(Product product) {
                return new StringBuilder().append(product.getName()).append(' ').append(product.getQuantity()).append(" - ").append(product.getProducer()).toString();
            }
        };
    }
}
