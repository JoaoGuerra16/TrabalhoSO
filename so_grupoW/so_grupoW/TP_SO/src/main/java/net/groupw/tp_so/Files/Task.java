package net.groupw.tp_so.Files;

public class Task implements Runnable {
    private Message message;
    private Middleware middleware;

    public Task(Message message, Middleware middleware) {
        this.message = message;
        this.middleware = middleware;
    }

    @Override
    public void run() {
        Runnable task = () -> {
            middleware.sendMessage(message);
        };
        middleware.getCpu().executeTask(task);
    }
}
