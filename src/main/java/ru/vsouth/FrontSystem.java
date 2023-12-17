package ru.vsouth;

import java.util.ArrayDeque;

public class FrontSystem {
    private final ArrayDeque<Request> requestQueue;
    private final int queueCapacity;

    public FrontSystem(int queueCapacity) {
        this.queueCapacity = queueCapacity;
        this.requestQueue = new ArrayDeque<>(queueCapacity);
    }

    public void putRequest(Request request) {
        synchronized (requestQueue) {
            while (requestQueue.size() >= queueCapacity) {
                try {
                    requestQueue.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            requestQueue.add(request);
            System.out.printf("Фронтальная система приняла заявку - %s\n", request.getClientThreadName());
            requestQueue.notifyAll();
        }
    }

    public Request takeRequest() {
        synchronized (requestQueue) {
            while (requestQueue.size() == 0) {
                try {
                    requestQueue.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Request request = requestQueue.pop();
            System.out.printf("Фронтальная система передала заявку - %s\n", request.getClientThreadName());
            requestQueue.notifyAll();
            return request;
        }
    }
}
