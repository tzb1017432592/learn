package com.example.lambda.mylambda.myinterface.bean;

import com.example.lambda.mylambda.myinterface.function.DoTest;
import com.example.lambda.mylambda.myinterface.function.SetName;
import com.example.lambda.mylambda.myinterface.function.ThreeArgsConsturct;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Getter
@Setter
@ToString
public class Cat {
    private String name;
    private int age;
    private int weight;

    public Cat(int age,String name, int weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }
    public static <R> R build(Integer age, String name,Integer weight, ThreeArgsConsturct<Integer,String,Integer,R> t){
        return t.get(age,name,weight);
    }
    public static Cat build2(Integer a,String n,Integer w,Predicate<Cat> t
            ,Function<String,String> f){
        Cat build = Cat.build(a, n, w, Cat::new);
        if (t.test(build)){
            build.setName(f.apply(build.getName()));
            return build;
        }
        return null;
    }


    public Cat(int age, String name) {
        this.name = name;
        this.age = age;
    }

    public Cat(Cat cat) {
    }

    public static <R> R build(Integer age,String name,BiFunction<Integer,String,R> t){
        return t.apply(age,name);
    }



    public static <T> T build(Supplier<T> t){
        return t.get();
    }



    public void otherName(String name,SetName<String> n){
        this.name=n.setName(name);
    }
    public void otherName2(Function<String,String> n){
        this.name=n.apply(this.name);
    }
    /* public void otherName(String name, Function<String,String> n){
            this.name=n.apply(name);
        }*/
    public Cat() {
    }

    public Cat(String name) {
        this.name = name;
    }

    public void die() {
        System.out.println("我死了。。。。。。。");
    }

    public void live() {
        System.out.println("我出生了。。。。。。");
    }
}
