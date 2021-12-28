package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Member member = new Member();
            member.setUsername("hello");
            em.persist(member);
            em.flush();
            em.clear();
//          ----------------------------------------------------------------------------------
//            Member findMember = em.find(Member.class, member.getId());
            // 프록시 객체 조회
//            Member findMember = em.getReference(Member.class, member.getId());
//            System.out.println("findMember.getClass() = " + findMember.getClass());
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getUsername() = " + findMember.getUsername());
//          ----------------------------------------------------------------------------------
//            Member refMember = em.getReference(Member.class, member.getId());
//            System.out.println("refMember = " + refMember.getClass());  // proxy
//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("findMember = " + findMember.getClass());    // member
//            System.out.println("refMember == findMember: "+ (refMember == findMember));
//            --------------------------------------------------------------------------------
//            Member refMember = em.getReference(Member.class, member.getId());
//            System.out.println("refMember = " + refMember.getClass());  // proxy
//            em.detach(refMember);
//            em.close();
//            em.clear();
//            refMember.getUsername();
//            --------------------------------------------------------------------------------
            Member refMember = em.getReference(Member.class, member.getId());
            System.out.println("refMember = " + refMember.getClass());  // proxy
            System.out.println("isLoaded = "+ emf.getPersistenceUnitUtil().isLoaded(refMember));    // 초기화 전 false
//            refMember.getUsername();    // 강제 초기화
            Hibernate.initialize(refMember);    // 강제 초기화
            System.out.println("isLoaded = "+ emf.getPersistenceUnitUtil().isLoaded(refMember));    // 초기화 후 true



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }
        emf.close();
    }


}