package com.sample.spring.boot.redis.messagePack;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    private Integer id;

    private String name;

    private String city;

    private List<Student> lovers;

    public Student(Integer id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
}
