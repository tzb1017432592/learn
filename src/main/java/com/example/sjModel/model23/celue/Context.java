package com.example.sjModel.model23.celue;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void price(){
        strategy.price();
    }
}
