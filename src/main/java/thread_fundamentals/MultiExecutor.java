package main.java.thread_fundamentals;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

    private List<Runnable> tasks;

    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    void executeAll() {
        List<Thread> threads = new ArrayList<>();

        for (Runnable thread : threads) {
            Thread t = new Thread(thread);
            threads.add(t);
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}
