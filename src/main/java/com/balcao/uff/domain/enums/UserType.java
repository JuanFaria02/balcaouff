package com.balcao.uff.domain.enums;

public enum UserType {
    PROFESSOR("professor"),
    ALUNO("aluno"),
    ADMIN("admin");

    private String role;

    UserType(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
