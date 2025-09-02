package blocking_q;

import java.util.ArrayDeque;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> queue;
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayDeque<>(capacity);
    }

    public synchronized void enqueue(T obj) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(obj);
        notify();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T obj = queue.remove();
        notify();
        return obj;
    }

    public synchronized int size() {
        return queue.size();
    }

    public static void main(String[] args) {
        BlockingQueue<Integer> q = new BlockingQueue<>(3);

        Thread producer = new Thread(() -> {
           try {
               for (int i = 1; i < 11; i++) {
                   q.enqueue(i);
                   System.out.println("Added " + i + " element");
                   Thread.sleep(100);
               }
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
           }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i < 11; i++) {
                    Integer element = q.dequeue();
                    System.out.println("Consumed " + element + " element");
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
