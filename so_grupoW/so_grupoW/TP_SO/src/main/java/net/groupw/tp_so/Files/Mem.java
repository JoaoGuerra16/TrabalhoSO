package net.groupw.tp_so.Files;



import net.groupw.tp_so.Enumerations.LogType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Mem {
    private List<Message> memory;

    public Mem(List<Message> initialData) {
        this.memory = new ArrayList<>(initialData);
    }

    /**
     * Writes a message to the memory.
     *
     * @param message The message to be written to memory.
     */
    public synchronized void writeToMemory(Message message) {
        memory.add(message);

        LogEntry.getInstance().addLog(new Log(LogType.INFO,Thread.currentThread().getName() + "MEM written message: " + message, LocalDateTime.now()));
    }

    /**
     * Reads and removes a message from the memory.
     *
     * @return The read message or null if the memory is empty.
     */
    public synchronized Message readFromMemory() {
        if (!memory.isEmpty()) {
            LogEntry.getInstance().addLog(new Log(LogType.INFO,Thread.currentThread().getName() + "MEM read message: " + memory.get(0), LocalDateTime.now()));
            return memory.remove(0);
        } else {
            return null;
        }
    }

    /**
     * Checks if a specific message is present in the memory.
     *
     * @param message The message to check for.
     * @return True if the message is present, false otherwise.
     */
    public synchronized boolean containsMessage(Message message) {
        return memory.contains(message);
    }

    /**
     * Gets the current size of the memory (number of messages).
     *
     * @return The size of the memory.
     */
    public synchronized int getMemorySize() {
        return memory.size();
    }

    /**
     * Clears all messages from the memory.
     */
    public synchronized void clearMemory() {
        memory.clear();
    }

    public boolean isEmpty() {
        return memory.isEmpty();
    }



}
