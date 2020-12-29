package com.example.reflectLearn.bean;

@MyTest(value = "我是类上的注解", name = "ReflectBean类")
public class ReflectBean {
    @MyTest(value = "我是成员变量上的注解", name = "name变量")
    private String name;
    private String nickname;

    public ReflectBean() {
    }

    public ReflectBean(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
    }

    @MyTest(value = "我是方法上的注解", name = "interfaceTest方法")
    public void interfaceTest() {
        System.out.println("我使用了自定义的注解");
    }

    private void say(String name) {
        System.out.println(name);
    }

    @Override
    public String toString() {
        return "ReflectBean{" +
                "name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String Myname(String name, String nickname) {
        return name + nickname;
    }
}
