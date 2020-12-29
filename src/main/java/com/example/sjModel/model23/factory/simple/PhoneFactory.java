package com.example.sjModel.model23.factory.simple;

public class PhoneFactory {
    /* public static  Phone createPhone(String name){
         if (name.equalsIgnoreCase("huawei")){
             return new HuaWei();
         }else if (name.equalsIgnoreCase("pingguo")){
             return new IPhone();
         }
         return null;
     }*/
    public static <T> T createPhone(Class<T> t) {
        T obj = null;
        try {
            obj = t.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
