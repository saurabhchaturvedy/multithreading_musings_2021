package main.java.thread_coordination;

import java.math.BigInteger;

public class Main2 {

    public static void main(String[] args) {
        LongComputationTask longComputationTask = new LongComputationTask(new BigInteger("2"), new BigInteger("10000000"));

        Thread thread = new Thread(longComputationTask);
        thread.setDaemon(true);
        thread.start();
        thread.interrupt();
    }

    private static class LongComputationTask implements Runnable {

        private final BigInteger base;
        private final BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + this.pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger pow) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(pow) != 0; i = i.add(BigInteger.ONE)) {
//                if (Thread.currentThread().isInterrupted()) {
//                    System.out.println("Prematurely interuppted execution");
//                    return BigInteger.ZERO;
//                }
                result = result.multiply(base);
            }

            return result;
        }
    }
}
