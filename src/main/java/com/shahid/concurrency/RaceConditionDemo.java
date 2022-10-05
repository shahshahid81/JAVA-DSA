package com.shahid.concurrency;

public class RaceConditionDemo {
  private static class BankAccount implements Runnable {
    private int balance;

    public void setBalance(int balance) {
      this.balance = balance;
    }

    // Remove synchronized to remove lock for race condition
    public synchronized void withdraw(int amount) {
      System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount);
      if (balance >= amount) {
        balance -= amount;
        System.out.printf("%s has withdrawn %d\nRemaining balance: %d%n", Thread.currentThread().getName(), amount, balance);
      } else {
        System.out.println(Thread.currentThread().getName() + " can't withdraw " + amount);
      }
    }

    @Override
    public void run() {
      withdraw(80);
    }
  }

  public static void main(String[] args) {
    BankAccount bankAccount = new BankAccount();
    bankAccount.setBalance(100);

    Thread john = new Thread(bankAccount);
    Thread harry = new Thread(bankAccount);

    john.setName("John");
    harry.setName("Harry");

    john.start();
    harry.start();
  }
}
