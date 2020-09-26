package project.study.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study.common.domain.Address;
import project.study.common.domain.BaseAuditingTime;
import project.study.user.dto.CreateUserRequestDto;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "USER_SEQ_GENERATOR"
        , sequenceName = "USER_SEQ"
        , initialValue = 1, allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User extends BaseAuditingTime { //BaseAuditingUser로 교체할것
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GENERATOR")
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String userLoginId;
    private String pass1;
    private String pass2;
    private String deleteYn;//

    @Enumerated(EnumType.STRING)
    private UserType userType;

//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    private List<Order> orders = new ArrayList<>();

//    @OneToMany(mappedBy = "boards")
//    private List<Board> boards = new ArrayList<>();

    @Embedded
    private Address address;

    public static User createUser(CreateUserRequestDto createUserRequestDto) {
        User user = new User();
        user.name = createUserRequestDto.getName();
        user.userLoginId = createUserRequestDto.getUserLoginId();
        user.pass1 = createUserRequestDto.getPass1();
        user.pass2 = createUserRequestDto.getPass2();
        user.deleteYn = "N";
        user.address = new Address(createUserRequestDto.getCity()
                , createUserRequestDto.getStreet()
                , createUserRequestDto.getZipcode());
        user.userType = UserType.NORMAL;
        return user;
    }

}
