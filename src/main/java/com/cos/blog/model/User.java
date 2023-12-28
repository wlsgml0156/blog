package com.cos.blog.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

// ORM => Java(다른 언어) Object -> JPA 가 table로 매핑해주는 기술
@Entity
// @DynamicInsert // insert시 값이 null값을 제외 시켜준다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더패턴
@Table(name = "META_USER")
public class User {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; // 시퀀스, auto_increment

    @Column(nullable = false, length = 100, unique = true)
    private String username; // 아이디

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

//    @ColumnDefault("'user'") // ""큰 따옴표안에 ' 작은 따옴표를 같이 작성해줘서 문자라는 것을 알려줘야한다.
//    private String role;
    // 나중에는 Enum을 쓰는게 좋다. -> Enum을 쓰므로써 도메인을 만들 수 있음.
    // 권한을 admin, user, manger 만 넣고 싶은데 만약 String으로 해놨을 경우 오타가나서
    // adminn이런식으로 들어갈 수 있기 때문

    // DB는 RoleType라는게 없다.
    // 따라서 @Enumerated 를 붙여주어 String이라는 것을 알려줘야한다.
    @Enumerated(EnumType.STRING)
    private RoleType role;


    private String oauth; // 소셜 로그인인지 일반 로그인인지 확인하기 위함.

    @CreationTimestamp // 시간이 자동으로 입력
    private Timestamp createDate;

}
