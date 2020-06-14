package com.example.demo;

class Consumer implements Runnable {
    private Thread t;
    private final String threadName;
    private final Producer p;

    Consumer(String name, Producer p) {
        threadName = name;
        System.out.println("Creating " + threadName);
        this.p = p;
    }

    public void run() {

        System.out.println("Running " + threadName);

        try {
            // consumer thread waits while list
            // is empty
            while (p.list.size() == 0) {

            }
            //  wait();
            while (true) {
                // to retrive the ifrst job in the list
                int val = p.list.take();

                System.out.println("Consumer " + threadName + " consumed-"
                        + val);

                // Wake up producer thread
                // notify();

                // and sleep
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }

        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}