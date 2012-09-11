package org.ws4d.coap.client;

import java.util.concurrent.CountDownLatch;

public class Future<T> {

    private final CountDownLatch latch = new CountDownLatch(1);
    private volatile T value = null;

    public Future() {
        super();
    }

    public void set(T value) {
        this.value = value;
        latch.countDown();
    }

    public T get() {
        try {
            latch.await();
        } catch (InterruptedException ex) {}
        return this.value;
    }
}
