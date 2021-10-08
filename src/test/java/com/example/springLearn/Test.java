package com.example.springLearn;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author tianzhoubing
 * @date 2021/5/24 19:05
 * @description
 **/
public class Test {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    ListNode head = null;
    ListNode tail = null;
    public void mk() {
        for (int i = 1; i < 13; i++) {
            if (head == null) {
                head = new ListNode(i);
                tail = head;
            } else {
                ListNode node = new ListNode(i);
                tail.next = node;
                tail = node;
            }
        }
        tail.next = head;
    }

    private void loop() {
        ListNode temp = head;
        while (true) {
            for (int i = 1; i < 5; i++) {
                temp = temp.next;
                if (i==4){
                    if (temp.next!=null){
                        temp.next=temp.next.next;
                    }
                }
                if (temp.next == null) {
                    break;
                }
            }
        }
    }

    static long s=0;
    static Semaphore semaphore = new Semaphore(100000);
    static Runnable runnable = ()->{
        try {
            semaphore.acquire();
            s++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }

    };
   static LongAdder longAdder = new LongAdder();
   static Long l = 0l;
    static Runnable runnable2 = ()->{
            longAdder.increment();
            l++;
    };

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(50);
        for (int i = 0; i <100000 ; i++) {
            service.submit(runnable2);
        }

        System.out.println(longAdder.longValue());
        service.shutdown();
        while (!service.isTerminated()){
        }
        System.out.println(longAdder.longValue());
        System.out.println(l);
    }

}
