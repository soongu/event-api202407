package com.study.event.api.event.dto.request;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDto {

    private String email;
    private String password;

    // 자동로그인 여부 ...
}
