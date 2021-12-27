package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//2. seq 사용 ->
//@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq", // 매핑할 데이터베이스 시퀀스 이름
//        initialValue = 1, allocationSize = 50)

//3. table seq 사용 ->@TableGenerator(name = "MEMBER_SEQ_GENERATOR",table = "MY_SEQUENCES",pkColumnValue = "MEMBER_SEQ",allocationSize = 1)
@Entity
public class Member {
//  1. @GeneratedValue(strategy = GenerationType.IDENTITY)
//  2. @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
//  3. @GeneratedValue(strategy = GenerationType.TABLE,generator = "MEMBER_SEQ_GENERATOR")
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT")
//    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}