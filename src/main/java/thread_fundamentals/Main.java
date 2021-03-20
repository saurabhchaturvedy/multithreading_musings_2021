package main.java.thread_fundamentals;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("We are now in the thread : " + Thread.currentThread().getName());
                System.out.println("thread priority is : " + Thread.currentThread().getPriority());
              //  throw new RuntimeException("Intentional exception");
            }
        });
        thread.setName("New worker thread");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A Critical error happened in the thread "+thread.getName()+"exception message is : "+e.getMessage());
            }
        });
        System.out.println("We are in the thread : " + Thread.currentThread().getName());
        thread.start();
        System.out.println("We are in the thread after start : " + Thread.currentThread().getName());
        Thread.sleep(10000);
    }
}
