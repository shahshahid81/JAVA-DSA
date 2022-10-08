package com.shahid.concurrency;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutorServiceDemo {
  public static void main(String[] args) {
    LinkedList<Integer> queue = new LinkedList<>();
    ReentrantLock reentrantLock = new ReentrantLock();
    Condition production = reentrantLock.newCondition();
    Condition consumption = reentrantLock.newCondition();

    int threadCount = 10;

    ExecutorService producerExecutorService = Executors.newSingleThreadExecutor();
    ExecutorService consumerExecutorService = Executors.newFixedThreadPool(threadCount);

    producerExecutorService.execute(new Producer(queue, reentrantLock, production, consumption));
    for (int i = 0; i < threadCount; i++) {
      consumerExecutorService.submit(new Consumer(queue, reentrantLock, production, consumption));
    }


    producerExecutorService.shutdown();
    consumerExecutorService.shutdown();
  }

  private static class Producer implements Runnable {

    LinkedList<Integer> queue;
    ReentrantLock reentrantLock;
    Condition production;
    Condition consumption;

    public Producer(LinkedList<Integer> queue, ReentrantLock reentrantLock, Condition production, Condition consumption) {
      this.queue = queue;
      this.reentrantLock = reentrantLock;
      this.production = production;
      this.consumption = consumption;
    }

    @Override
    public void run() {
      reentrantLock.lock();
      try {
        int limit = 11000;
        for (int i = 1000; i <= limit; i = i + 10) {
          for (int j = 0; j < 10; j++) {
            System.out.println("Produced: " + (i + j));
            queue.offer(i + j);
          }
          production.signalAll();
          consumption.await();
        }
        while (!queue.isEmpty()) {
          production.signalAll();
          consumption.await();
        }
        production.signalAll();
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
    Condition production;
    Condition consumption;

    public Consumer(LinkedList<Integer> queue, ReentrantLock reentrantLock, Condition production, Condition consumption) {
      this.queue = queue;
      this.reentrantLock = reentrantLock;
      this.production = production;
      this.consumption = consumption;
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
          consumption.signalAll();
          if (!queue.isEmpty()) {
            production.await();
          }
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
