/*
    You are given a class NumberPrinter with three methods: printZero, printEven, and printOdd.
    These methods are designed to print the numbers 0, even numbers, and odd numbers, respectively.
    Task:
    Create a ThreadController class that coordinates three threads:
    5. ZeroThread: Calls printZero to print 0s.
    6. EvenThread: Calls printEven to print even numbers.
    7. OddThread: Calls printOdd to print odd numbers.
    These threads should work together to print the sequence "0102030405..." up to a specified number n.
The output should be interleaved, ensuring that the numbers are printed in the correct order.
 */

/*
   Approach
   Use Three Threads: One for printing 0, one for even numbers, and one for odd numbers.
   Synchronization: Use a shared lock to control execution order.
   Wait-Notify Mechanism:
   printZero() waits for its turn (when count % 2 == 0).
   printOdd() waits for count % 4 == 1.
   printEven() waits for count % 4 == 3.
   After printing, each thread increments count and notifies all waiting threads.
   Ensuring Proper Order: Threads execute alternately, ensuring the output format 0 X 0 Y 0 Z ....
   Time Complexity:
   O(n) – Each number (zero, odd, even) is printed exactly once in sequence.

   Space Complexity:
   O(1) – Only a few variables and synchronization objects are used, independent of n. 🚀
*/

// A class responsible for printing numbers in a synchronized manner.

class NumberPrinter {
    // Method to print zero
    public void printZero() {
        System.out.print("0");
    }

    // Method to print even numbers
    public void printEven(int num) {
        System.out.print(num);
    }

    // Method to print odd numbers
    public void printOdd(int num) {
        System.out.print(num);
    }
}

class ThreadController {
    private final int n;
    private int count = 0; // Start from 0 for correct sequence
    private final NumberPrinter printer;
    private final Object lock = new Object();

    public ThreadController(int n, NumberPrinter printer) {
        this.n = n;
        this.printer = printer;
    }

    public void printZero() {
        synchronized (lock) {
            for (int i = 0; i < n; i++) {
                while (count % 2 != 0) { // Zero prints when count is even
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                printer.printZero();
                count++;
                lock.notifyAll();
            }
        }
    }

    public void printEven() {
        synchronized (lock) {
            for (int i = 2; i <= n; i += 2) {
                while (count % 4 != 3) { // Ensures even numbers print correctly
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                printer.printEven(i);
                count++;
                lock.notifyAll();
            }
        }
    }

    public void printOdd() {
        synchronized (lock) {
            for (int i = 1; i <= n; i += 2) {
                while (count % 4 != 1) { // Ensures odd numbers print correctly
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                printer.printOdd(i);
                count++;
                lock.notifyAll();
            }
        }
    }
}

public class Q6a {
    public static void main(String[] args) {
        int n = 5;
        NumberPrinter printer = new NumberPrinter();
        ThreadController controller = new ThreadController(n, printer);

        Thread zeroThread = new Thread(controller::printZero);
        Thread evenThread = new Thread(controller::printEven);
        Thread oddThread = new Thread(controller::printOdd);

        zeroThread.start();
        evenThread.start();
        oddThread.start();
    }
}
// output: 0102030405
