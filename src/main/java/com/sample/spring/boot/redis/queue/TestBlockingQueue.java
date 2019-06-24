package com.sample.spring.boot.redis.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestBlockingQueue {

    private final static BlockingQueue blockingQueue = new ArrayBlockingQueue(5);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Producer(blockingQueue)).start();
        new Thread(new Customer(blockingQueue)).start();
    }
}

class Producer implements Runnable {

    private BlockingQueue blockingQueue = null;

    public Producer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            blockingQueue.put("test1");
            blockingQueue.put("test2");
            blockingQueue.put("test3");
            blockingQueue.put("test4");
            blockingQueue.put("test5");
//            boolean bool = blockingQueue.offer("test6");
            System.out.println("入队的消息为：test1");
//            System.out.println("入队的消息为：" + bool);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Customer implements Runnable {

    private BlockingQueue blockingQueue = null;

    public Customer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
//            String str = (String) blockingQueue.take();
            String str = (String) blockingQueue.poll();
            System.out.println("出队消息为：" + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
