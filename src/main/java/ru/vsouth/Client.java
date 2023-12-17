package ru.vsouth;

public class Client implements Runnable {
    final String name;
    final Request request;
    final FrontSystem frontSystem;

    public Client(String name, Request request, FrontSystem frontSystem) {
        this.name = name;
        this.request = request;
        this.frontSystem = frontSystem;
    }

    @Override
    public void run() {
        System.out.printf("%s: Заявка %s отправлена в банк\n", name, request);
        frontSystem.putRequest(request);
    }
}