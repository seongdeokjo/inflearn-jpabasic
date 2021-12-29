package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
//           -------------------------------------------------------------------------------
            // native sql - jpa가 제공하는 sql을 직접 사용하는 기능, jpql로 해결할 수 없는 특정 데이터베스에 의존적인 기능
            //            - 예) 오라클 connect by, 특정 db만 사용하는 sql 힌트
//            em.createNativeQuery("select MEMBER_ID, city, street, zipcode, USERNAME from MEMBER")
//                    .getResultList();
//           -------------------------------------------------------------------------------
            // criteria 사용 준비   - 문자가 아닌 자바코드로 jpql을 작성할 수 있다. / jpql 빌더 역활 / jpa 공식 기능
            // 단점 - 너무 복잡하고 실용성이 없다. -> criteria 대신 queryDsl 사용 권장
//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Member> query = cb.createQuery(Member.class);
//            Root<Member> m = query.from(Member.class);
//            CriteriaQuery<Member> cq = query.select(m);
//            String username = "dada";
//            if(username != null){
//                cq = cq.where(cb.equal(m.get("username"), "kim"));
//            }
//            List<Member> resultList = em.createQuery(cq).getResultList();
//           --------------------------------------------------------------------------------
            // jpql 문법 - 테이블이 아닌 객체를 대상으로 검색하는 객체 지향 쿼리
            //          - sql을 추상화해서 특정 데이터베이스 sql에 의존x
            //          - jpql -> 객체 지향 sql
//            List<Member> result = em.createQuery(
//                    "select m from Member m where m.username like '%kim%'",
//                    Member.class
//            ).getResultList();
//            for (Member member : result) {
//                System.out.println("member = " + member);
//            }
//          ---------------------------------------------------------------------------------
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setHomeAddress(new Address("homeCity","street","10"));
//            member.getFavoriteFoods().add("치킨");
//            member.getFavoriteFoods().add("피자");
//            member.getFavoriteFoods().add("족발");
//            member.getAddressHistory().add(new AddressEntity("old1","street","20"));
//            member.getAddressHistory().add(new AddressEntity("old2", "street", "20"));
//            em.persist(member);
//            em.flush();
//            em.clear();
//            System.out.println("========start========");
//            Member findMember = em.find(Member.class, member.getId());
//          수정
            // homeCity -> newCity
//            findMember.getHomeAddress().setCity("newCity");
//            Address a = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));
            // 치킨 -> 한식
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");
//            findMember.getAddressHistory().remove(new Address("old1","street","20"));
//            findMember.getAddressHistory().add(new Address("newCity1","street","20"));
//          값 타입  조회
//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address = " + address.getCity());
//            }
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFood = " + favoriteFood);
//            }

//           ---------------------------------------------------------------------
//            Address address = new Address("city", "street", "10");
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setHomeAddress(address);
//            em.persist(member);
//            Address newAddress = new Address("NewCity", address.getStreet(), address.getZipcode());
//            member.setHomeAddress(newAddress);
//            Member member2 = new Member();
//            member2.setUsername("member1");
//            member2.setHomeAddress(copyAddress);
//            em.persist(member2);
//            member.getHomeAddress().setCity("newCity");
//          ------------------------------------------------------------------
//          -cascade- 소유자가 1개일 경우에만 사용
//            Child child1 = new Child();
//            Child child2 = new Child();
//            Parent parent = new Parent();
//            parent.addChild(child1);
//            parent.addChild(child2);
//            em.persist(parent);
//            em.flush();
//            em.clear();
//          고아 객체 - 참조하는 곳이 하나일 때 사용해야함, 특정 엔티티가 개인 소유할 때 사용
//          oorphanRemoval = true 사용
//            Parent findParent = em.find(Parent.class, parent.getId());
//            em.remove(findParent);
//            findParent.getChildList().remove(0);
//            em.persist(child1);
//            em.persist(child2);
//          -----------------------------------------------------------------
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//            Member member = new Member();
//            member.setUsername("member1");
//            em.persist(member);
//            member.setTeam(team);
//            em.flush();
//            em.clear();
//           --------------------------------------------------------------------------------
//           지연 로딩 - lazy
//            Member m = em.find(Member.class, member.getId());
//            System.out.println("m = " + m.getTeam().getClass());
//            System.out.println("============");
//            m.getTeam().getName();  // 초기화
//            System.out.println("============");
//          --------------------------------------------------------------------------------
//          즉시 로딩 - eager 
//          ** 가급적 지연 로딩만 사용하자
//            Member m = em.find(Member.class, member.getId());
//            System.out.println("m = " + m.getTeam().getClass());
//            System.out.println("============");
//            System.out.println("teamName = "+m.getTeam().getName());    // 실제 team 엔티티
//            System.out.println("============");

//          ----------------------------------------------------------------------------------
//           프록시
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
//            Member refMember = em.getReference(Member.class, member.getId());
//            System.out.println("refMember = " + refMember.getClass());  // proxy
//            System.out.println("isLoaded = "+ emf.getPersistenceUnitUtil().isLoaded(refMember));    // 초기화 전 false
////            refMember.getUsername();    // 강제 초기화
//            Hibernate.initialize(refMember);    // 강제 초기화
//            System.out.println("isLoaded = "+ emf.getPersistenceUnitUtil().isLoaded(refMember));    // 초기화 후 true
//            ---------------------------------------------------------------------------------
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }


}