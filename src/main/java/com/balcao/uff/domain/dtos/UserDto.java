package com.balcao.uff.domain.dtos;

import com.balcao.uff.domain.User;
import com.balcao.uff.domain.enums.UserType;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private UserType userType;
    private boolean active;

    public UserDto() {}

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.active = user.isActive();
        this.userType = user.getUserType();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public UserType getUserType() {
        return userType;
    }
}
