package ru.vsouth;

import static ru.vsouth.RequestType.*;

public class BackSystem {
    private volatile long balance;

    public BackSystem(long balance) {
        this.balance = balance;
    }

    public void fulfillRequest(Request request) {
        RequestType requestType = request.getRequestType();
        switch (requestType) {
            case REPAYMENT:
                increaseBalance(request);
                break;
            case CREDIT:
                decreaseBalance(request);
                break;
        }

    }

    private synchronized void increaseBalance(Request request) {
        balance += request.getAmount();
        System.out.printf("Бэк система: Заявка %s: Баланс банка: %d\n",
                request, balance);
        notifyAll();
    }

    private synchronized void decreaseBalance(Request request) {
        while (balance - request.getAmount() < 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        balance -= request.getAmount();
        notifyAll();
        System.out.printf("Бэк система: Заявка %s: Баланс банка: %d\n",
                request, balance);

    }
}
