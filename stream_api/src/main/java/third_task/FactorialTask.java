package third_task;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {
    private final int n;

    public FactorialTask(int n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        if (n == 0 || n == 1) {
            return 1L;
        } else {
            FactorialTask ft = new FactorialTask(n - 1);
            ft.fork();
            return n * ft.join();
        }
    }
}
