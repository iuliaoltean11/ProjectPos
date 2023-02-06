package com.pos.projectpos.common;

public class UserDto {
    Long id;
    String email;
    String password;
    String username;
    double money;

    public UserDto(Long id, String email, String password, String username, double money) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.money=money;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}