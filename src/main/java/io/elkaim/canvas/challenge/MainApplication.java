package io.elkaim.canvas.challenge;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.canvas.CanvasServiceImpl;
import io.elkaim.canvas.challenge.command.CommandService;
import io.elkaim.canvas.challenge.command.CommandServiceImpl;
import io.elkaim.canvas.challenge.command.exceptions.QuitApplicationSignalException;
import io.elkaim.canvas.challenge.command.executors.*;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandFactory;
import io.elkaim.canvas.challenge.painter.CanvasPainter;
import io.elkaim.canvas.challenge.painter.CanvasPainterImpl;
import io.elkaim.canvas.challenge.painter.MessagePainter;
import io.elkaim.canvas.challenge.painter.MessagePainterImpl;
import io.elkaim.canvas.challenge.utils.InputRequestor;

import java.util.List;
import java.util.Objects;


public class MainApplication {

    public static void main(String[] args){
        CanvasPainter canvasPainter = new CanvasPainterImpl();
        CanvasService canvasService = new CanvasServiceImpl();
        MessagePainter messagePainter = new MessagePainterImpl();
        CommandService commandService = new CommandServiceImpl(List.of(
                new BulkFillExecutor(messagePainter, canvasService),
                new RectangleCreationExecutor(messagePainter, canvasService),
                new CanvasCreationExecutor(messagePainter, canvasService),
                new LineCreationExecutor(messagePainter, canvasService),
                new QuitApplicationExecutor(messagePainter)

        ));

        InputRequestor inputRequestor = new InputRequestor(System.in, System.out);

        String userCommand;
        while(!Objects.isNull( userCommand = inputRequestor.promptUserForInput("Enter your command here"))){

            if(userCommand.trim().isEmpty()){
                continue;
            }

            try{
                Command command = CommandFactory.build(userCommand);
                commandService.execute(command);
                canvasPainter.draw(canvasService.getCanvas());

            }catch (QuitApplicationSignalException quitApp) {
                String resp = inputRequestor.promptUserForInput("All your drawings will be destroyed! Are you sure? (y)");
                if (resp.equalsIgnoreCase("y")) {
                    System.out.println("The canvas is destroyed.");
                    break;
                }
            } catch (ErrorMessageException e){
                messagePainter.draw(e.getMessage());
            }catch (Exception e){
                messagePainter.draw(e);
            }
        }
        System.out.println("You quit our Canvas Application. See you soon !");
    }
}
