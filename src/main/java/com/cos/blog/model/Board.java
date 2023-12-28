package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더패턴
@Table(name = "META_BOARD")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨.

//    @ColumnDefault("0")
    private int count;

    // DB에는 결국 integer로 생성이 된 것을 확인.
    // 이유가 혹시 연관관계의 주인의 pk의 타입을 따라가는건가??
    @ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One
    @JoinColumn(name="userId")
    private  User user; // DB는 오브젝트를 저장할 수 없다 . FK, 자바는 오브젝트를 저장할 수 있다.

    @OrderBy("id desc")
    @JsonIgnoreProperties({"board"})
    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE) // mappedBy 연관관계의 주인이 아니다.
    private List<Reply> replys;

    @CreationTimestamp
    private Timestamp createDate;

}
