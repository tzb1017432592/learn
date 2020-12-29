package com.example.bingfa.finalTets;


import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.*;

public class Final {
    private static final int SAFEINT=2333;
    private static final String SAFESTR="2333";
    private static final Map<String,String> MAP= new HashMap();
    private static  Map<String,String> UNMAP= new HashMap();
    private static final Map GUNMAP= ImmutableMap.of("k1","v1","k2","v2");
    private static final List GUNLIST= ImmutableList.of("111","222");
    private static final Set GUNSET= ImmutableSet.of("111","222");
    static {
        UNMAP.put("111","222");
        UNMAP= Collections.unmodifiableMap(UNMAP);
    }
    @Test
    public void test(){
      /*  UNMAP.forEach((k,v)->{
            System.out.println(k+":"+v);
        });*/
        GUNMAP.forEach((k,v)->{
            System.out.println(k+":"+v);
        });
        GUNLIST.forEach(l->{
            System.out.println(l);
        });
        GUNSET.forEach(k->{
            System.out.println(k);
        });
    }


}
