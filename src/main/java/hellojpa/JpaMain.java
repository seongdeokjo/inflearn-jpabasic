package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
//            Member findMember = em.find(Member.class, 1L);
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//            for (Member member : result) {
//                System.out.println("member.getName() = " + member.getName());
//            }
            // 비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloAB");

            // 영속
            System.out.println("==== before ====");
            em.persist(member);
            em.detach(member);
            System.out.println("==== after ====");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close();
    }
}