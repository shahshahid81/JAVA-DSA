package com.shahid.concurrency;

import java.math.BigInteger;
import java.util.LinkedList;

public class ThreadCoordinationDemo {
  public static void main(String[] args) {

    DataReader dataReader = new DataReader();

    Thread producerThread = new Thread(new Producer(dataReader));
    producerThread.setName("Producer");

    Thread consumerThreadOne = new Thread(new Consumer(dataReader));
    consumerThreadOne.setName("Consumer One");

    Thread consumerThreadTwo = new Thread(new Consumer(dataReader));
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

  private static class DataReader {

    private final LinkedList<Integer> queue;
    private boolean dataAvailable = true;

    private DataReader() {
      queue = new LinkedList<>();
    }

    public boolean isDataAvailable() {
      return dataAvailable;
    }

    public void setDataAvailable(boolean dataAvailable) {
      this.dataAvailable = dataAvailable;
    }
  }

  private static class Producer implements Runnable {

    private final DataReader dataReader;

    Producer(DataReader dataReader) {
      this.dataReader = dataReader;
    }

    @Override
    public void run() {
      synchronized (dataReader) {
        int limit = 1100;
        for (int i = 1000; i <= limit; i = i + 10) {
          for (int j = 0; j < 10; j++) {
            System.out.println("Produced: " + (i + j));
            dataReader.queue.offer((i + j));
          }
          dataReader.notifyAll();

          if (i == limit) break;
          try {
            dataReader.wait();
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        }
        dataReader.setDataAvailable(false);
      }
    }
  }

  private static class Consumer implements Runnable {

    private final DataReader dataReader;


    Consumer(DataReader dataReader) {
      this.dataReader = dataReader;
    }

    static BigInteger factorial(BigInteger n) {
      if (n.equals(BigInteger.valueOf(0))) {
        return BigInteger.valueOf(1);
      }
      return n.multiply(factorial(n.subtract(BigInteger.valueOf(1))));
    }

    @Override
    public void run() {
      synchronized (dataReader) {
        while (!dataReader.queue.isEmpty()) {
          String threadName = Thread.currentThread().getName();
          Integer consumedValue = dataReader.queue.poll();
          BigInteger factorial = factorial(BigInteger.valueOf(consumedValue));
          System.out.println(threadName + " Consumed: " + consumedValue);
          System.out.println(threadName + " generated factorial of " + consumedValue + " is " + factorial);
          dataReader.notifyAll();
          if (dataReader.isDataAvailable()) {
            try {
              dataReader.wait();
            } catch (InterruptedException e) {
              throw new RuntimeException(e);
            }
          }
        }
      }
    }
  }
}
