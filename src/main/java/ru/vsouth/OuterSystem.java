package ru.vsouth;

import java.util.concurrent.Callable;

public class OuterSystem implements Callable<Void> {
    private final String name;
    private final BackSystem backSystem;
    private final int amount;
    private final int timeout;

    public OuterSystem(String name, BackSystem backSystem, int amount, int timeout) {
        this.name = name;
        this.backSystem = backSystem;
        this.amount = amount;
        this.timeout = timeout;
    }


    @Override
    public Void call() {
        try {
            System.out.printf("Прогрев %s\n", this);
            Thread.sleep(timeout);
            backSystem.fulfillRequest(new Request(name, amount, RequestType.REPAYMENT));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public String toString() {
        return "OuterSystem{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", timeout=" + timeout + "ms" +
                '}';
    }
}
