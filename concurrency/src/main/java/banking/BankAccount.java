package banking;

import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private long balance; // Храним в копейках/центах
    private final ReentrantLock lock = new ReentrantLock();

    public void deposit(long amount) {
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    public boolean withdraw(long amount) {
        lock.lock();
        try {
            if (balance < amount) {
                System.out.println("Insufficient funds in the account");
                return false;
            }
            balance -= amount;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public synchronized long getBalance() {
        return balance;
    }
}
