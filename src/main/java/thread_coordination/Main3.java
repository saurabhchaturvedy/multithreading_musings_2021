package main.java.thread_coordination;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main3 {

    public static void main(String[] args) throws InterruptedException {
        List<Long> numbers = Arrays.asList(10000000L, 343L, 4343L, 23L, 550L, 228L);

        List<FactorialThread> threads = new ArrayList<>();

        for (Long number : numbers) {
            threads.add(new FactorialThread(number));
        }

        for (Thread thread : threads) {
            thread.setDaemon(true);   // for not terminating the main thread , keep running factorial threads in background
            thread.start();
        }

        for (Thread thread: threads)
        {
            thread.join(3000);   // specify milliseconds in case of one of the computations take unreasonable time , so atleast we can print others which got completed
        }

        for (int i = 0; i < numbers.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinished()) {
                System.out.println("Factorial of " + threads.get(i).inputNumber + " is " + factorialThread.getResult());
            } else {
                System.out.println("Calculation of factorial for " + threads.get(i).inputNumber + " is still in progress");
            }
        }
    }

    private static class FactorialThread extends Thread {
        private long inputNumber;
        BigInteger result = BigInteger.ZERO;
        boolean isFinished = false;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        private BigInteger factorial(long inputNumber) {
            BigInteger tempResult = BigInteger.ONE;

            for (long i = inputNumber; i > 0; i--) {
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }
            return tempResult;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public BigInteger getResult() {
            return result;
        }
    }
}
