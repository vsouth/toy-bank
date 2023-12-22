package ru.vsouth;

import java.util.concurrent.ArrayBlockingQueue;

public class FrontSystem {
    private final ArrayBlockingQueue<Request> requestQueue;

    public FrontSystem(int queueCapacity) {
        this.requestQueue = new ArrayBlockingQueue<>(queueCapacity);
    }

    public void putRequest(Request request) {
        requestQueue.add(request);
        System.out.printf("Фронтальная система приняла заявку - %s\n", request.getClientThreadName());
    }

    public Request takeRequest() throws InterruptedException {
        Request request = requestQueue.take();
        System.out.printf("Фронтальная система передала заявку - %s\n", request.getClientThreadName());
        return request;
    }
}
