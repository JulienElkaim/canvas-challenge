package io.elkaim.canvas.challenge;

import io.elkaim.canvas.challenge.app.ApplicationContext;
import io.elkaim.canvas.challenge.app.CanvasApplication;


public class MainApplication {

    public static void main(String[] args) {
        new CanvasApplication(new ApplicationContext()).run();
    }
}
