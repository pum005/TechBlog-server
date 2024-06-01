package com.toy.techblog.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserDTO {

    private Long userId;
    private String accountType;
    private String email;
    private String nickname;
    private String profileImage;

    public UserDTO() {
    }

    @Builder
    public UserDTO(Long userId, String accountType, String email, String nickname, String profileImage) {
        this.userId = userId;
        this.accountType = accountType;
        this.email = email;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }
}
