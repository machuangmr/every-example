package com.machd.responschain;

/**
 * 请添加注释
 *
 * @author machd
 * @version 3.0
 * @date 2022/4/27
 */
public class Member {
    private String username;

    private String password;

    private String role;

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
