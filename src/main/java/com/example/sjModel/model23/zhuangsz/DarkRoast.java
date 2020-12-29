package com.example.sjModel.model23.zhuangsz;

import java.math.BigDecimal;

public class DarkRoast extends Beverage {

    /**
     * 说明他是DarkRoast饮料
     */
    public DarkRoast() {
        description = "DarkRoast";
    }

    /**
     * 实现cost方法，用来返回DarkRoast（深焙咖啡）的价格
     *
     * @return
     */
    @Override
    public BigDecimal cost() {
        return new BigDecimal("3.00");
    }
}
