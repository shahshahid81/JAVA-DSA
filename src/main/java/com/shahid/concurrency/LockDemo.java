package com.shahid.concurrency;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
  public static void main(String[] args) {

    LinkedList<Integer> queue = new LinkedList<>();
    ReentrantLock reentrantLock = new ReentrantLock();
    Condition dataProduced = reentrantLock.newCondition();

    Thread producerThread = new Thread(new Producer(queue, reentrantLock, dataProduced));
    producerThread.setName("Producer");

    Thread consumerThreadOne = new Thread(new Consumer(queue, reentrantLock, dataProduced));
    consumerThreadOne.setName("Consumer One");

    Thread consumerThreadTwo = new Thread(new Consumer(queue, reentrantLock, dataProduced));
    consumerThreadTwo.setName("Consumer Two");

    producerThread.start();
    consumerThreadOne.start();
    consumerThreadTwo.start();


    try {
      producerThread.join();
      consumerThreadOne.join();
      consumerThreadTwo.join();
      System.out.println("Completed");
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private static class Producer implements Runnable {

    LinkedList<Integer> queue;
    ReentrantLock reentrantLock;
    Condition dataProduced;

    public Producer(LinkedList<Integer> queue, ReentrantLock reentrantLock, Condition dataProduced) {
      this.queue = queue;
      this.reentrantLock = reentrantLock;
      this.dataProduced = dataProduced;
    }

    @Override
    public void run() {
      reentrantLock.lock();
      try {
        int limit = 1100;
        for (int i = 1000; i <= limit; i = i + 10) {
          for (int j = 0; j < 10; j++) {
            System.out.println("Produced: " + (i + j));
            queue.offer((i + j));
          }
          dataProduced.signalAll();
          if(i == limit) break;
          dataProduced.await();
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } finally {
        System.out.println("Finally Producer");
        reentrantLock.unlock();
      }
    }
  }

  private static class Consumer implements Runnable {
    LinkedList<Integer> queue;
    ReentrantLock reentrantLock;
    Condition dataProduced;

    public Consumer(LinkedList<Integer> queue, ReentrantLock reentrantLock, Condition dataProduced) {
      this.queue = queue;
      this.reentrantLock = reentrantLock;
      this.dataProduced = dataProduced;
    }

    static BigInteger factorial(BigInteger n) {
      if (n.equals(BigInteger.valueOf(0))) {
        return BigInteger.valueOf(1);
      }
      return n.multiply(factorial(n.subtract(BigInteger.valueOf(1))));
    }

    @Override
    public void run() {
      String threadName = Thread.currentThread().getName();
      reentrantLock.lock();
      try {
        while (!queue.isEmpty()) {
          Integer consumedValue = queue.poll();
          BigInteger factorial = factorial(BigInteger.valueOf(consumedValue));
          System.out.println(threadName + " Consumed: " + consumedValue);
          System.out.println(threadName + " generated factorial of " + consumedValue + " is " + factorial);
          dataProduced.signalAll();
          if(!queue.isEmpty()) dataProduced.await();
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } finally {
        System.out.println("Finally " + threadName);
        reentrantLock.unlock();
      }
    }
  }
}
