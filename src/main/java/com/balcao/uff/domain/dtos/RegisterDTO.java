package com.balcao.uff.domain.dtos;

import com.balcao.uff.domain.enums.UserType;

public record RegisterDTO(String name, String email, String password, String phone, UserType role) {
}