package banking;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private long balance; // Храним в копейках/центах
    private final ReentrantLock lock = new ReentrantLock();

    public BankAccount(long balance) {
        this.balance = balance;
    }

    public void deposit(long amount) {
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(long amount) {
        lock.lock();
        try {
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }

    public synchronized long getBalance() {
        return balance;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount account = (BankAccount) o;
        return balance == account.balance && Objects.equals(lock, account.lock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, lock);
    }
}
