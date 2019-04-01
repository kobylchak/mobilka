package ua.kiev.prog.dao;

public enum UserRole {

    ADMIN, USER;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}