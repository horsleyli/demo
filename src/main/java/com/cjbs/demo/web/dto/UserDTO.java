package com.cjbs.demo.web.dto;

import com.cjbs.demo.domain.User;

/**
 * @author shj
 */
public class UserDTO {

    private String id;

    private String name;

    private String age;

    public UserDTO() {}

    public UserDTO(User user) {
        this(user.getId(), user.getName(), user.getAge());
    }

    public UserDTO(String id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User ToEntity() {
        User user = new User();
        user.setId(this.getId());
        user.setName(this.getName());
        user.setAge(this.getAge());

        return user;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
