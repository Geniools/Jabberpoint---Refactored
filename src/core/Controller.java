package core;

import java.util.ArrayList;

public class Controller {
    private ApplicationWindow applicationWindow;
    private ArrayList<ApplicationController> controllers;

    public Controller(ApplicationWindow application) {
        this.controllers = new ArrayList<>();
        this.applicationWindow = application;
    }

    public void addController(ApplicationController controller) {
        this.controllers.add(controller);
    }

    public void run() {
        applicationWindow.initialize();

        for (ApplicationController controller : this.controllers) {
            controller.connectToApplication(this.applicationWindow);
        }
    }
}
