package net.groupw.tp_so.Files;

public class WorkerThread extends Thread{
    private final Cpu cpu;

    public WorkerThread(Cpu cpu) {
        this.cpu = cpu;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Runnable task = cpu.getTask();
                task.run();
            } catch (InterruptedException e) {
                // Handle the interruption or any other exception
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
