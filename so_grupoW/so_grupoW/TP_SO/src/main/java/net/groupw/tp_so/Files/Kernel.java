package net.groupw.tp_so.Files;

import net.groupw.tp_so.Enumerations.LogType;
import net.groupw.tp_so.Gui.LoginForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Kernel {

    private Middleware middleware;
    private Cpu cpu;


    public void iniciarSistema() {
        ArrayList<Log> logs = new ArrayList<>();
        Mem memory = new Mem(new ArrayList<>());
        Cpu cpu = new Cpu(10, memory, 5);

        this.cpu = cpu;

        Middleware middleware = new Middleware(cpu, memory, 0);

        this.middleware = middleware;

        cpu.setMiddleware(middleware);

        Satellite satellite = new Satellite(logs);

        satellite.setMiddleware(middleware);
        Station station = new Station("Estacao", "Portugal", this);
        System.out.println("A iniciar o sistema...");
        LogEntry.getInstance().addLog(new Log(LogType.INFO, "Sistema Iniciado...", LocalDateTime.now()));
        SwingUtilities.invokeLater(() -> new LoginForm(station));
    }

    public void addNewTask(Message message) {
        Runnable taskLogic = new Task(message, middleware);
        cpu.executeTask(taskLogic);
    }

    public void desligarSistema() {
        System.out.println("A desligar o sistema...");
        LogEntry.getInstance().addLog(new Log(LogType.INFO, "Sistema Desligado...", LocalDateTime.now()));
        System.exit(0);
    }

}
