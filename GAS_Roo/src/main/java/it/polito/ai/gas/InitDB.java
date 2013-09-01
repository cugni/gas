package it.polito.ai.gas;


import it.polito.ai.gas.business.*;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.Calendar;


public class InitDB {
    /* NON FUNZIONA UN CULO */

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("DB Initialization...") ;

        User admin = new User();
        admin.setRole(UserType.ROLE_ADMIN);
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setBirthDate(Calendar.getInstance().getTime());
        admin.setName("Admin");
        admin.setSurname("Admin");
        entityManager.persist(admin);


        System.out.println("Created user: "+admin);
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
