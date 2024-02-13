package net.groupw.tp_so.Files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveLogs {

    private String filePath;

    public SaveLogs(String filepath) {
        this.filePath = filepath;
    }

    public void saveLogsToFile( ArrayList<Log> logs) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Log log : logs) {
                writer.write(log.toString());
                writer.newLine();
            }
            System.out.println("Logs salvos com sucesso no arquivo: " + filePath);
        } catch (IOException e) {
            System.err.println("Erro ao salvar logs no arquivo: " + filePath);
            e.printStackTrace();
        }
    }
}
