package org.example;

/**
 * Створити два додаткових потоки різними способами (успадкувати Thread або Runnable або за допомогою лямбди)
 * В першому потоці вивести тільки парні числа в діапазоні від 0 до 100_000
 * В другому потоці вивести тільки непарні числа в тому ж діапазоні
 * Питання: чи виводяться всі числа по черзі п
 */
public class EvenOddRunnable implements Runnable {
    private final int start;
    private final int end;

    public EvenOddRunnable(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (var i = start; i <= end; i += 2) {
            System.err.println(Thread.currentThread().getName() + ": " + i);
        }
    }

    public static void main(String[] args) {
        int start = 0;
        int end = 100_000;

        EvenOddRunnable evenRunnable = new EvenOddRunnable(start, end);
        Thread evenThread = new Thread(evenRunnable);
        evenThread.setName("EvenThread");

        Thread oddThread = new Thread(() -> {
            for (var i = start + 1; i <= end; i += 2) {
                System.err.println(Thread.currentThread().getName() + ": " + i);
            }
        });
        oddThread.setName("OddThread");

        evenThread.start();
        oddThread.start();
    }
}
