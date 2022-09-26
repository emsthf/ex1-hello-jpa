package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")  // mappedBy는 이 컬럼은 어떤 것이랑 연결되어 있다는 것을 나타내 준다. 여기서는 Member의 team 컬럼이랑 연결되었다는 뜻
    private List<Member> members = new ArrayList<>();  // 관례로 List는 초기화 해주는게 좋다. 그렇게 해야 add할 때 nullpointer 에러가 안뜨니까.

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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
