package com.bigjava18.httpclienttest.bean;

/**
 * @Author zgp
 * @Since 2021 -06 -22 14 :14
 * @Description
 */
public class UserRequest {

    private Long id;
    private String username;
    private String password;

    @Override
    public String toString() {
        return "UserRequest{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
