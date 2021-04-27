package com.example.sjModel.model23.celue;

public class Test {
    public static void main(String[] args) {
        Context context;
        System.out.println("现在是早上");
        context=new Context(new ConcreteStrategyMorning());
        context.price();
        System.out.println("\n");

        System.out.println("现在是中午");
        context=new Context(new ConcreteStrategyNoon());
        context.price();
        System.out.println("\n");

        System.out.println("现在是晚上");
        context=new Context(new ConcreteStrategyNight());
        context.price();
        System.out.println("\n");
    }
}
