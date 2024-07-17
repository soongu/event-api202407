package com.study.event.api.event.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(exclude = "eventList")
@EqualsAndHashCode(of ="id")
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_event_user")
public class EventUser {

    @Id
    @GenericGenerator(strategy = "uuid2", name = "uuid-generator")
    @GeneratedValue(generator = "uuid-generator")
    @Column(name = "ev_user_id")
    private String id; // 회원계정이 아니고 랜덤문자PK

    @Column(name = "ev_user_email", nullable = false, unique = true)
    private String email; // 회원 계정

    // NN을 하지 않는 이유: SNS로그인한 회원, 인증번호만 받고 회원가입 완료하지 않은 사람처리
    @Column(length = 500)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Role role = Role.COMMON; // 권한

    private LocalDateTime createAt; // 회원가입 시간

    @OneToMany(mappedBy = "eventUser", orphanRemoval = true, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Event> eventList = new ArrayList<>();


    // 이메일 인증을 완료했는지 여부
    // 엔터티에 boolean타입을 사용하면 실제 DB에는 0, 1로 저장됨에 주의
    @Setter
    @Column(nullable = false)
    private boolean emailVerified;

    public void confirm(String password) {
        this.password = password;
        this.createAt = LocalDateTime.now();
    }

    public void promoteToPremium() {
        this.role = Role.PREMIUM;
    }
}
