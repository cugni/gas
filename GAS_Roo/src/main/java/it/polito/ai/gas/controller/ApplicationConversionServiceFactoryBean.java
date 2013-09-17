package it.polito.ai.gas.controller;

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
                return new StringBuilder().append(user.getUsername()).append(' ').toString();
            }
        };
    }

    public Converter<Proposal, String> getProposalToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<it.polito.ai.gas.business.Proposal, java.lang.String>() {
            public String convert(Proposal proposal) {
                return new StringBuilder().append(proposal.getProduct()).append(' ').append(proposal.getDelegate()).toString();
            }
        };
    }
}
