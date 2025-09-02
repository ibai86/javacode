package synchro;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComplexTaskExecutor {
    private final int numberOfThreads;
    private final CyclicBarrier cyclicBarrier;

    public ComplexTaskExecutor(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
        this.cyclicBarrier = new CyclicBarrier(numberOfThreads);
    }

    public void executeTasks(int numberOfTasks) {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfTasks);
        

    }
}