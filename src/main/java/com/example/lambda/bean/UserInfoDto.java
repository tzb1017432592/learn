package com.example.lambda.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserInfoDto {
    private String name;
    private int age;
    private Date birthday;
}
