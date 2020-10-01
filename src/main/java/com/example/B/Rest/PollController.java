package com.example.B.Rest;

import com.example.B.Poll;
import com.example.B.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.hibernate.jpa.AvailableSettings.PERSISTENCE_UNIT_NAME;

@RestController
public class PollController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/poll")
    public Poll poll(@RequestParam(value = "poll_name", defaultValue = "Empty poll") String name) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Poll poll = new Poll(String.format(template, name));
        em.getTransaction().begin();
        em.persist(poll);
        em.getTransaction().commit();
        em.close();

        Query q = em.createQuery("select p from Poll p");
        List<Poll> pollList = q.getResultList();
        for (Poll p : pollList) {
            System.out.println(p);
        }
        System.out.println("Size: " + pollList.size());

        return new Poll(String.format(template, name));
    }

}