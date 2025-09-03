package banking;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Lock;

public class ConcurrentBank {
    private final Map<UUID, BankAccount> accounts = new HashMap<>();

    public BankAccount createAccount(long initialBalance) {
        BankAccount newAccount = new BankAccount(initialBalance);
        accounts.put(newAccount.getId(), newAccount);
        return newAccount;
    }

    public void transfer(UUID fromId, UUID toId, long amount) {
        BankAccount from = accounts.get(fromId);
        BankAccount to = accounts.get(toId);

        Lock firstLock = fromId.compareTo(toId) < 0 ? from.getLock() : to.getLock();
        Lock secondLock = fromId.compareTo(toId) < 0 ? to.getLock() : from.getLock();

        firstLock.lock();
        secondLock.lock();

        try {
            if (from.getBalance() < amount) {
                System.out.println("Insufficient funds in the account");
                return;
            }
            from.withdraw(amount);
            to.deposit(amount);
            System.out.println("Transfer successfully completed");
        } finally {
            secondLock.unlock();
            firstLock.unlock();
        }
    }

    public long getTotalBalance() {
        return accounts.values().stream()
                .mapToLong(BankAccount::getBalance)
                .sum();
    }
}
