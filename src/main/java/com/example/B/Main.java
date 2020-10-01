package com.example.B;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "poll";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        // read the existing entries and write to console
        Query q = em.createQuery("select p from User p");
        List<User> personList = q.getResultList();
            for (User person : personList) {
            System.out.println(person);
         }
         System.out.println("Size: " + personList.size());

        com.example.B.User u = new com.example.B.User();
        u.setAdmin(false);
        u.setUserName("Jarle");
        u.setPassword("mos");

        com.example.B.Poll poll = new com.example.B.Poll();
        poll.setName("poll");

        com.example.B.Vote v = new com.example.B.Vote();
        v.setUser(u);
        v.setYes(false);

        poll.addVote(v);

        em.getTransaction().begin();
        em.persist(poll);
        em.persist(u);
        em.persist(v);
        em.getTransaction().commit();

        em.close();
    }
}
