package com.example.springLearn.newlearn.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Objects;

@Data
@Component
public class Html {
    private Integer id;
    private String name;

    public Html(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Html() {
        System.out.println("html调用了构造方法");
    }

    //@PostConstruct在容器的组件初始化后使用
    @PostConstruct
    public void post() {
        System.out.println("Html调用了@PostConstruct的方法");
    }

    //@PreDestroy在容器的组件被摧毁前被调用
    @PreDestroy
    public void destroy() {
        System.out.println("Html调用了@PreDestroy的方法");
    }

  /*  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Html html = (Html) o;
        return Objects.equals(id, html.id) &&
                Objects.equals(name, html.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }*/
}
