package synchro;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ComplexTask {
    private final int taskId;
    private final List<Double> partialResults;
    private final CyclicBarrier barrier;

    public ComplexTask(int taskId, List<Double> partialResults, CyclicBarrier barrier) {
        this.taskId = taskId;
        this.partialResults = partialResults;
        this.barrier = barrier;
    }

    public void execute() {
        try {
            System.out.println("Task #" + taskId + " started");
            double result = Math.random() * 2000;
            Thread.sleep((long) (result));
            System.out.println("Task #" + taskId + " completed with result = " + result);

            partialResults.add(result);

            barrier.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            System.err.println("Barrier broken " + e.getMessage());
        }
    }
}
