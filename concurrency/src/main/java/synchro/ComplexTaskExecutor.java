package synchro;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ComplexTaskExecutor {
    private final int numberOfThreads;
    private final List<Double> results = new CopyOnWriteArrayList<>();
    private CyclicBarrier barrier;
    private ExecutorService executor;

    public ComplexTaskExecutor(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    public synchronized void executeTasks(int numberOfTasks) {
        results.clear();

        barrier = new CyclicBarrier(numberOfTasks, () -> {
            double totalSum = results.stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();
            System.out.println("All complex tasks finished with result = " + totalSum);
        });

        executor = Executors.newFixedThreadPool(numberOfTasks);

        for (int i = 1; i <= numberOfTasks; i++) {
            final int taskId = i;
            executor.submit(() -> {
                ComplexTask task = new ComplexTask(taskId, results, barrier);
                task.execute();
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.err.println("Tasks completed with error " + e.getMessage());
        }
    }
}