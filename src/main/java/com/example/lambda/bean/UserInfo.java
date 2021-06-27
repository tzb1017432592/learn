package com.example.lambda.bean;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class UserInfo {
    private long id;
    private String name;
    private int age;
    private Date birthday;
    private String sex;
}
