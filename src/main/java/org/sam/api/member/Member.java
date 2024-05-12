package org.sam.api.member;

import java.time.LocalDateTime;

/**
 * Created by melchor
 * Date: 2020/08/11
 * Time: 8:32 PM
 */
public class Member {

    private Long id;
    private String email;
    private String name;
    private String password;
    private LocalDateTime regDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }
}
