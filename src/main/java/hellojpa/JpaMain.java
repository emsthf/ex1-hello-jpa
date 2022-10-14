package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member1 = new Member();
            member1.setUsername("user1");
            member1.setCreatedBy("kim");
            member1.setCreatedDate(LocalDateTime.now());
            em.persist(member1);

            em.flush();  // 영속성 컨텍스트에 있는 쿼리 DB에 즉시 날림
            em.clear();  // 영속성 컨텍스트 제거

            Member m1 = em.find(Member.class, member1.getId());  // m1이 영속성 컨텍스트에 올라가있는 상태
            System.out.println("m1 = " + m1.getClass());

            Member reference = em.getReference(Member.class, member1.getId());
            System.out.println("reference = " + reference.getClass());

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
