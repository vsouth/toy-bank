package ru.vsouth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BackSystem backSystem = new BackSystem(new AtomicLong(1000));
        FrontSystem frontSystem = new FrontSystem(2);

        Thread requestHandler1 = new Thread(
                new RequestHandler("Обработчик №1", frontSystem, backSystem));
        Thread requestHandler2 = new Thread(
                new RequestHandler("Обработчик №2", frontSystem, backSystem));


        List<Client> clientList = new ArrayList<>();
        var amounts = List.of(500, 1000, 2000, 1500);
        for (int i = 0; i < amounts.size(); i++) {
            RequestType requestType = i % 2 == 0 ? RequestType.CREDIT : RequestType.REPAYMENT;
            String clientName = "Клиент " + (i + 1);
            Request request = new Request(clientName, amounts.get(i), requestType);
            Client client = new Client(clientName, request, frontSystem);
            clientList.add(client);
        }



        ExecutorService clientService = Executors.newFixedThreadPool(6);
        clientService.submit(requestHandler1);
        clientService.submit(requestHandler2);



        for (Client client : clientList) {
            clientService.submit(client);
        }



        clientService.submit(() -> {
            try {
                Thread.sleep(3000);
                System.out.printf("Итоговый баланс банка: %s",backSystem.getBalance());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });



    }
}