package net.groupw.tp_so.Files;


public class Station {
    private int ref;
    private String name;
    private String location;
    private Kernel kernel;

    public Station(String name, String location, Kernel kernel) {
        this.name = name;
        this.location = location;
        this.kernel = kernel;
    }

    public void stationSendMessage( Message message){
        kernel.addNewTask(message);
    }



}
