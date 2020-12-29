package com.example.zl;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractZkLock implements Lock{
    private static final Logger log=LoggerFactory.getLogger(AbstractZkLock.class);
    public static  final String ZKSERVERPATH = "192.168.200.130:2181,192.168.200.131:2181,192.168.200.132:2181";
    public static final Integer TIMEOUT = 50000;
    protected static volatile  ZkClient zkClient=null;
    protected static final String PATH = "/lock";
    protected String WAIT_PATH;
    protected String CURRENTLOCK;

    //双重检测机制
    public static ZkClient getZkClient(){
        if (zkClient==null){
            synchronized (AbstractZkLock.class){
                if (zkClient==null){
                    zkClient=new ZkClient(ZKSERVERPATH,TIMEOUT);

                    return zkClient;
                }
            }
        }
        return zkClient;
    }
    @Override
    public void getLock() {
        if (tryLock()){
            log.info("开始");
            System.out.println(Thread.currentThread().getName()
                    +"->"+CURRENTLOCK+"获得锁成功");
            return;
        }else {
            waitLock();
            System.out.println(Thread.currentThread().getName()
                    +"->"+CURRENTLOCK+"进入等待锁阶段");

        }
    }

    abstract void waitLock();

    abstract boolean tryLock();

    @Override
    public void unLock() {
        zkClient.delete(CURRENTLOCK);
    }
}
