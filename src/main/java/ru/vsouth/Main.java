package ru.vsouth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BackSystem backSystem = new BackSystem(new AtomicLong(0));

        List<OuterSystem> outerSystems = new ArrayList<>(List.of(
                new OuterSystem("Outer system 1", backSystem, 500, 7000),
                new OuterSystem("Outer system 2", backSystem, 200, 6000),
                new OuterSystem("Outer system 3", backSystem, 300, 9000)
        ));

        FrontSystem frontSystem = new FrontSystem(2);

        Thread requestHandler1 = new Thread(
                new RequestHandler("Обработчик №1", frontSystem, backSystem));
        Thread requestHandler2 = new Thread(
                new RequestHandler("Обработчик №2", frontSystem, backSystem));


        List<Client> clientList = new ArrayList<>(List.of(
                new Client("Клиент 1",
                        new Request("Клиент 1", 500, RequestType.CREDIT),
                        frontSystem),
                new Client("Клиент 2",
                        new Request("Клиент 2", 1000, RequestType.REPAYMENT),
                        frontSystem),
                new Client("Клиент 3",
                        new Request("Клиент 3",2000, RequestType.CREDIT),
                        frontSystem),
                new Client("Клиент 4",
                        new Request("Клиент 4", 1500, RequestType.REPAYMENT),
                        frontSystem)
        ));



        ExecutorService executorService = Executors.newFixedThreadPool(6);

        executorService.invokeAll(outerSystems);
        System.out.printf("Баланс банка: %s\n", backSystem.getBalance());


        executorService.submit(requestHandler1);
        executorService.submit(requestHandler2);


        for (Client client : clientList) {
            executorService.submit(client);
        }


        executorService.submit(() -> {
            try {
                Thread.sleep(3000);
                System.out.printf("Итоговый баланс банка: %s",backSystem.getBalance());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });



    }
}