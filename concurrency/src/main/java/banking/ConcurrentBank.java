package banking;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class ConcurrentBank {
    private final Map<BankAccount, Integer> accounts = new HashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public BankAccount createAccount(long initialBalance) {
        BankAccount newAccount = new BankAccount(initialBalance);
        int newId = idCounter.getAndIncrement();
        accounts.put(newAccount, newId);
        return newAccount;
    }

    public void transfer(BankAccount fromAccount, BankAccount toAccount, long amount) {
        int fromId = accounts.get(fromAccount);
        int toId = accounts.get(toAccount);

        Lock firstLock = fromId < toId ? fromAccount.getLock() : toAccount.getLock();
        Lock secondLock = fromId < toId ? toAccount.getLock() : fromAccount.getLock();

        firstLock.lock();
        secondLock.lock();

        try {
            if (fromAccount.getBalance() < amount) {
                System.out.println("Insufficient funds in the account");
                return;
            }
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transfer successfully completed");
        } finally {
            secondLock.unlock();
            firstLock.unlock();
        }
    }

    public long getTotalBalance() {
        return accounts.keySet().stream()
                .mapToLong(BankAccount::getBalance)
                .sum();
    }
}
