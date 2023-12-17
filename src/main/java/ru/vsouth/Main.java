package ru.vsouth;

public class Main {
    public static void main(String[] args) {
        BackSystem backSystem = new BackSystem(1000);
        FrontSystem frontSystem = new FrontSystem(2);

        Thread requestHandler1 = new Thread(
                new RequestHandler("Обработчик №1", frontSystem, backSystem));
        Thread requestHandler2 = new Thread(
                new RequestHandler("Обработчик №2", frontSystem, backSystem));

        Thread client1 = new Thread(
                new Client("Клиент 1",
                new Request("Клиент 1", 500, RequestType.CREDIT),
                frontSystem));

        Thread client2 = new Thread(
                new Client("Клиент 2",
                new Request("Клиент 2", 1000, RequestType.REPAYMENT),
                frontSystem));

        Thread client3 = new Thread(
                new Client("Клиент 3",
                new Request("Клиент 3", 2000, RequestType.CREDIT),
                frontSystem));

        Thread client4 = new Thread(
                new Client("Клиент 4",
                new Request("Клиент 4", 1500, RequestType.REPAYMENT),
                frontSystem));

        client1.start();
        client2.start();
        client3.start();
        client4.start();

        requestHandler1.start();
        requestHandler2.start();

    }
}