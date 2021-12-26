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
            // 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);
            
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            // 연관관계의 주인에 값 설정
//            member.setTeam(team);   // **

            // 역방향(주인이 아닌 방향)만 연관관계 설정
//            team.getMembers().add(member); //**
            team.addMember(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());  // 1차 캐시
            List<Member> members = findTeam.getMembers();

            System.out.println("====================");
            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }
            System.out.println("====================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close();
    }
}