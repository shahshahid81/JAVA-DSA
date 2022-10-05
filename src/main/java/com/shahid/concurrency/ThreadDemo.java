package com.shahid.concurrency;

public class ThreadDemo {
  private static class Work implements Runnable {

    @Override
    public void run() {
      System.out.println("Running " + Thread.currentThread().getName());
      if (Thread.currentThread().getPriority() == Thread.MIN_PRIORITY) Thread.yield();
      printOne();
      if (Thread.currentThread().getPriority() == Thread.MIN_PRIORITY) Thread.yield();
      printTwo();
      if (Thread.currentThread().getPriority() == Thread.MIN_PRIORITY) Thread.yield();
      printThree();
    }

    private void printOne() {
      System.out.println(Thread.currentThread().getName() + ": " + 1);
      printOnePointOne();
      printOnePointTwo();
    }

    private void printOnePointOne() {
      System.out.println(Thread.currentThread().getName() + ": " + 1.1);
    }

    private void printOnePointTwo() {
      System.out.println(Thread.currentThread().getName() + ": " + 1.2);
    }

    private void printTwo() {
      System.out.println(Thread.currentThread().getName() + ": " + 2);
    }

    private void printThree() {
      System.out.println(Thread.currentThread().getName() + ": " + 3);
      printThreePointOne();
    }

    private void printThreePointOne() {
      System.out.println(Thread.currentThread().getName() + ": " + 3.1);
    }
  }

  public static void main(String[] args) {
    Work work = new Work();
    Thread thread1 = new Thread(work);
    Thread thread2 = new Thread(work);
    Thread thread3 = new Thread(work);
    Thread thread4 = new Thread(work);
    Thread thread5 = new Thread(work);


    System.out.println(Thread.currentThread());

    thread1.setName("Thread One");
    thread1.setPriority(Thread.MAX_PRIORITY);

    thread2.setName("Thread Two");
    thread2.setPriority(Thread.MIN_PRIORITY);

    thread3.setName("Thread Three");
    thread3.setPriority(Thread.MIN_PRIORITY);

    thread4.setName("Thread Four");
    thread4.setPriority(Thread.NORM_PRIORITY);

    thread5.setName("Thread Five");
    thread5.setPriority(Thread.MIN_PRIORITY);

    System.out.println("Starting Thread");
    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();
    thread5.start();

    try {
      thread1.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Thread Started");
  }
}
