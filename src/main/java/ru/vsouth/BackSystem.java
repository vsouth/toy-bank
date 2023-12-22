package ru.vsouth;

import java.util.concurrent.atomic.AtomicLong;

public class BackSystem {

    private volatile AtomicLong balance;

    public BackSystem(AtomicLong balance) {
        this.balance = balance;
    }

    public AtomicLong getBalance() {
        return balance;
    }

    public boolean fulfillRequest(Request request) {
        RequestType requestType = request.getRequestType();
        boolean result = false;
        switch (requestType) {
            case REPAYMENT:
                return increaseBalance(request);
            case CREDIT:
                return decreaseBalance(request);
        }
        return result;
    }

    private boolean increaseBalance(Request request) {
        boolean flag = false;
        long newBalance = 0;
        while (!flag) {
            var expectedBalance = balance.getAcquire();
            newBalance = expectedBalance + request.getAmount();
            flag = balance.compareAndSet(expectedBalance, newBalance);
        }
        System.out.printf("Бэк система: Заявка %s: Баланс банка: %s\n",
                request, getBalance());
        return true;
    }

    private boolean decreaseBalance(Request request) {
        boolean flag = false;
        long newBalance = 0;
        while (!flag) {
            var expectedBalance = balance.getAcquire();
            newBalance = expectedBalance - request.getAmount();
            if (newBalance < 0) {
                continue;
            }

            flag = balance.compareAndSet(expectedBalance, newBalance);
        }
        System.out.printf("Бэк система: Заявка %s: Баланс банка: %d\n",
                request, newBalance);
        return true;
    }
}
