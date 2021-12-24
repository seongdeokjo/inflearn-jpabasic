package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collections;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            // 영속
            Member member = em.find(Member.class, 150L);
            member.setName("AAAA");

            em.clear();

            Member member2 = em.find(Member.class, 150L);

            System.out.println("==========================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close();
    }
}