package hellojpa;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 1.조인 전략 사용
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 2.싱글 테이블 사용
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 3. 구현 클래스마다 테이블 사용, 잘 사용 x
@DiscriminatorColumn
//public abstract class Item {        // 3. 사용시
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
