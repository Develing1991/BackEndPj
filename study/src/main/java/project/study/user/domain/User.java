package project.study.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import project.study.board.domain.Board;
import project.study.common.domain.Address;
import project.study.common.domain.BaseAuditingTime;
import project.study.order.domain.Order;
import project.study.user.dto.CreateUserRequestDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(name = "USER_SEQ_GENERATOR"
        , sequenceName = "USER_SEQ"
        , initialValue = 1, allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
@Getter @Setter
public class User extends BaseAuditingTime { //BaseAuditing User로 교체할것
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GENERATOR")
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String userLoginId;
    private String pass1;
    //private String pass2;
    private String deleteYn;//

    @Enumerated(EnumType.STRING)
    private UserType userType;

    //@JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    //@JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();


    private boolean userLogin;

    @Embedded
    private Address address;

    public static User createUser(CreateUserRequestDto createUserRequestDto) {
        User user = new User();
        user.name = createUserRequestDto.getName();
        user.userLoginId = createUserRequestDto.getUserLoginId();
        user.pass1 = createUserRequestDto.getPass1();
        //user.pass2 = createUserRequestDto.getPass2();
        user.deleteYn = "N";
        user.address = new Address(createUserRequestDto.getCity()
                , createUserRequestDto.getStreet()
                , createUserRequestDto.getZipcode());
        user.userType = UserType.NORMAL;
        return user;
    }

}
