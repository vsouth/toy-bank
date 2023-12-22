package ru.vsouth;

public class RequestHandler implements Runnable{
    private final String name;
    private final FrontSystem frontSystem;
    private final BackSystem backSystem;

    public RequestHandler(String name, FrontSystem frontSystem, BackSystem backSystem) {
        this.name = name;
        this.frontSystem = frontSystem;
        this.backSystem = backSystem;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted())  {
            Request currentRequest;
            try {
                currentRequest = frontSystem.takeRequest();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("%s: Получена заявка на обработку по клиенту - %s\n", name, currentRequest.getClientThreadName());
            backSystem.fulfillRequest(currentRequest);

        }
    }
}
