package com.example.zl;

import org.I0Itec.zkclient.IZkDataListener;
import org.apache.commons.collections.CollectionUtils;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

public class ExcutorZkLock extends AbstractZkLock {
    private static final Logger log= LoggerFactory.getLogger(ExcutorZkLock.class);
    @Override
    void waitLock() {
        log.info("等待锁");
        if(zkClient.exists(WAIT_PATH)){
            try {
            System.out.println(Thread.currentThread().getName()
                    +"等待节点:"+WAIT_PATH+"解锁");
            CountDownLatch latch = new CountDownLatch(1);
            zkClient.subscribeDataChanges(WAIT_PATH, new IZkDataListener(){
                @Override
                public void handleDataChange(String dataPath, Object data) throws Exception {
                }
                @Override
                public void handleDataDeleted(String dataPath) throws Exception {
                    System.out.println(WAIT_PATH+"锁被删除了");
                    latch.countDown();
                }
            });
            latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    boolean tryLock() {
        log.info("尝试获取锁");
        zkClient = getZkClient();
       // lisnerZk();
        CURRENTLOCK = zkClient.createEphemeralSequential(PATH + "/", "test");
        List<String> children = zkClient.getChildren(PATH);
        SortedSet<String> sortSet = new TreeSet<>();
        for (String str : children) {
            sortSet.add(str);
        }
        String first = sortSet.first();
        if (first.equals(CURRENTLOCK.replace(PATH + "/", ""))) {
            return true;
        }
        SortedSet<String> headSet = sortSet.headSet(CURRENTLOCK.replace(PATH + "/", ""));
        if (CollectionUtils.isNotEmpty(headSet)) {
            WAIT_PATH = PATH+"/"+headSet.last();
        }
        return false;
    }

    private void lisnerZk(){
        zkClient.subscribeChildChanges(PATH,
                (String parentPath, List<String> currentChilds)->
                System.out.println(Thread.currentThread().getName()+"在"+ parentPath+"下创建了子节点"));
    }
}
