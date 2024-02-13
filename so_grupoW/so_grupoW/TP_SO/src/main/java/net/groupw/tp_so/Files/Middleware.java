package net.groupw.tp_so.Files;

import net.groupw.tp_so.Enumerations.LogType;
import net.groupw.tp_so.Files.Log;
import net.groupw.tp_so.Files.LogEntry;
import net.groupw.tp_so.Files.Message;
import net.groupw.tp_so.Files.Satellite;

import java.time.LocalDateTime;
import java.util.concurrent.Semaphore;

public class Middleware implements Runnable {
    private Cpu cpu;
    private Mem mem;
    private Semaphore startSemaphore = new Semaphore(1);

    public Middleware(Cpu cpu, Mem mem, int index) {
        this.cpu = cpu;
        this.mem = mem;
    }

    public void startMiddlewareThread() {
        startSemaphore.release();
    }

    public synchronized void sendMessage(Message message) {
        mem.writeToMemory(message);
        // Notify waiting threads that a message is available
        notify();
    }

    public synchronized Message receiveMessage() throws InterruptedException {
        while (mem.isEmpty()) {
            // Wait until a message is available
            wait();
        }
        return mem.readFromMemory();
    }

    public synchronized void sendToSatellite(Message message) {
        // Assuming Satellite class has a static method for simplicity
        Satellite.receiveMessageFromMiddleware(message);
    }

    public synchronized Message receiveFromSatellite() {
        // Assuming Satellite class has a static method for simplicity
        return Satellite.sendMessageToMiddleware();
    }

    public Cpu getCpu() {
        return cpu;
    }

    @Override
    public void run() {

        try {
            startSemaphore.acquire(); // Acquire a permit, or block until one is available
        } catch (InterruptedException e) {
            // Handle the interruption or any other exception
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(2000);
                synchronized (this) {
                    while (mem.isEmpty()) {
                        // Wait until a message is available
                        wait();
                    }
                }

                // Process the message
                Message messageRead = receiveMessage();
                LogEntry.getInstance().addLog(new Log(LogType.INFO, Thread.currentThread().getName() + " Middleware read a message: " + messageRead, LocalDateTime.now()));

                Thread.sleep(1000);
                // Send the message to the satellite
                sendToSatellite(messageRead);

                Thread.sleep(1000);
                Message responseFromSatellite = receiveFromSatellite();

                LogEntry.getInstance().addLog(new Log(LogType.INFO, Thread.currentThread().getName() + " Middleware received a response from Satellite: " + responseFromSatellite, LocalDateTime.now()));
                startSemaphore.release();
            } catch (InterruptedException e) {
                // Handle the interruption or any other exception
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
