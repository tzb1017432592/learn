package com.example.lambda.mylambda.myinterface;

import com.example.lambda.mylambda.myinterface.bean.Cat;
import com.example.lambda.mylambda.myinterface.function.DoSameField;
import com.example.lambda.mylambda.myinterface.function.DoSamething;
import com.example.lambda.mylambda.myinterface.function.DoTest;
import com.example.lambda.mylambda.myinterface.function.Itest;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class DoSameThingImpl {
    private static final String TEST = "TEST";

    public String todoSameThing(DoSamething a,String string) {
        return a.returnString(string);
    }

    public static <T>List<T> TestFunc(List<T> ls, DoTest<T> t){
        List<T> result=new ArrayList<>();
        ls.forEach(l->{
            if (t.test(l)){
                result.add(l);
            }
        });
        return result;
    }

    public static <T, R> List<R> TestFuncRT(List<T> list,
                                     DoSameField<T,R> f) {
        List<R> result = new ArrayList<>();
        for(T s: list){
            result.add(f.test(s));
        }
        return result;
    }

    public static <T> List<T> JDKFuncPredicate(List<T> ls,
                                                   Predicate<T> t) {
        List<T> result=new ArrayList<>();
        ls.forEach(l->{
            if (t.test(l)){
                result.add(l);
            }
        });
        return result;
    }
    public static <T,R> void JDKFuncFunction(List<T> ls,
                                               Consumer<T> t) {
        ls.forEach(l->t.accept(l));
    }

    public static <T,R> List<R> JDKFuncFunction(List<T> ls,
                                                Function<T,R> f) {
        List<R> result = new ArrayList<>();
        for(T s: ls){
            result.add(f.apply(s));
        }
        return result;
    }

//

    public static void main(String[] args) {
        DoSameThingImpl doSameThing = new DoSameThingImpl();
        String s = doSameThing.todoSameThing(str -> str.toLowerCase(),TEST);
        ArrayList<String> lists = Lists.newArrayList("苹果", "香蕉");
        List<String> result = TestFunc(lists, str -> {
            if (StringUtils.equals(str, "苹果")) {
                return true;
            }
            return false;
        });

        List<String> result2 = TestFuncRT(lists, str -> {
            if (StringUtils.equals(str, "苹果")) {
                return str;
            }
            return "null";
        });
        ArrayList<Object> list = Lists.newArrayList();
        Itest<String> itest=str->list.add(s);

        System.out.println(TestFuncRT(Lists.newArrayList("苹果", "香蕉"),String::new));

        DoSameField<String, Cat> f=Cat::new;
        Cat cat = f.test("通过构造函数的猫");

        System.out.println(cat.getName());
        System.out.println(s);

        System.out.println(result.toString());

        System.out.println(result2.toString());
    }
}
