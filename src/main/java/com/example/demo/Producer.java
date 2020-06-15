package com.example.demo;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
public class Producer {
    static int capacity = 20;
    static BlockingQueue<Integer> list = new ArrayBlockingQueue<>(capacity, true);
    public  void produce() throws InterruptedException
    {
        int value = 0;
        while (true) {
            synchronized (this)
            {
                // producer thread waits while list
                // is full
                while (list.size() == capacity)
                System.out.println("Producer produced: "+ value);
                // to insert to the list
                list.put(value++);
                // notifies the consumer thread that
                // now it can start consuming
                notify();
                Thread.sleep(200);
            }
        }
    }

}
