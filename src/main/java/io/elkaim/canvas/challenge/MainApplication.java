package io.elkaim.canvas.challenge;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.canvas.CanvasServiceImpl;
import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.command.CommandExecutor;
import io.elkaim.canvas.challenge.command.CommandService;
import io.elkaim.canvas.challenge.command.CommandServiceImpl;
import io.elkaim.canvas.challenge.command.exceptions.QuitApplicationSignalException;
import io.elkaim.canvas.challenge.command.executors.*;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandFactory;
import io.elkaim.canvas.challenge.painter.CanvasPainter;
import io.elkaim.canvas.challenge.painter.CanvasPainterImpl;
import io.elkaim.canvas.challenge.painter.ErrorPainter;
import io.elkaim.canvas.challenge.painter.ErrorPainterImpl;

import java.beans.JavaBean;
import java.util.List;
import java.util.Scanner;


public class MainApplication {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        CanvasPainter canvasPainter = new CanvasPainterImpl();
        CanvasService canvasService = new CanvasServiceImpl();
        ErrorPainter errorPainter = new ErrorPainterImpl();
        CommandService commandService = new CommandServiceImpl(List.of(
                new BulkFillExecutor(canvasService),
                new RectangleCreationExecutor(canvasService),
                new CanvasCreationExecutor(canvasService),
                new LineCreationExecutor(canvasService),
                new QuitApplicationExecutor()

        ));

        while(userStillPresent(sc)){
            String userCommand = sc.nextLine();
            // 1 translate user Input as a Command
            try{
                Command command = CommandFactory.build(userCommand);
                commandService.exec(command);
                canvasPainter.draw(canvasService.getCanvas());

            }catch (QuitApplicationSignalException quitApp){
                System.out.print("You decided to quit. All your drawings will be destroyed ! Are you sure? (y): ");
                String resp = sc.nextLine();
                if (resp.equalsIgnoreCase("y")){
                    System.out.println("You pr");
                    break;
                }
            }catch (Exception e){
                errorPainter.draw(e);
            }

            // 2 Send this command to the good Executor
            // 3 Executor parse and understand the cmd to modify the canvas
            // 4 the canvas is drawn by a CanvasDrawer
            //Here,
        }
        System.out.println("You quit our Canvas Application, see you soon !");
    }

    private static boolean userStillPresent(Scanner sc) {
        System.out.print("Enter your command here: ");
        return sc.hasNextLine();
    }
}
