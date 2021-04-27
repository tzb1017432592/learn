package com.example.lambda.mylambda.myinterface.function;

import java.util.function.Predicate;

public interface ThreeArgsConsturct<Q,W,E,R>{
    R get(Q q,W w,E e);
}
