package io.elkaim.canvas.challenge.app;

import io.elkaim.canvas.challenge.app.exceptions.ErrorMessageException;
import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.command.CommandService;
import io.elkaim.canvas.challenge.command.exceptions.QuitApplicationSignalException;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandFactory;
import io.elkaim.canvas.challenge.io.in.InputService;
import io.elkaim.canvas.challenge.io.out.OutputService;

import java.io.PrintStream;
import java.util.Objects;

/**
 * Application of Canvas.
 * Allow the user to create a canvas and draw on it.
 */
public class CanvasApplication implements Runnable {

    private final PrintStream out;
    private final InputService inputService;
    private final OutputService outputService;

    private final CommandService commandService;
    private final CanvasService canvasService;

    private final ApplicationContext ctx;

    public CanvasApplication(ApplicationContext context) {
        this.ctx = context;

        this.commandService = this.ctx.getBean(CommandService.class);
        this.canvasService = this.ctx.getBean(CanvasService.class);

        this.out = this.ctx.getBean(PrintStream.class);
        this.outputService = this.ctx.getBean(OutputService.class);
        this.inputService = this.ctx.getBean(InputService.class);
    }

    /**
     * Run the application.
     * Perform the main loop to request user's inputs, handle behavior for errors.
     */
    public void run() {

        String userCommand;
        while (!Objects.isNull(userCommand = this.inputService.requestInputLine("Enter your command here"))) {

            if (userCommand.trim().isEmpty()) {
                continue;
            }

            try {
                Command command = CommandFactory.build(userCommand);
                this.commandService.execute(command);

                if (command.getType().isRedrawRequired()) {
                    this.outputService.print(this.canvasService.getCanvas());
                }

            } catch (QuitApplicationSignalException quitApp) {
                StringBuilder request = new StringBuilder();
                if (this.canvasService.canvasNotYetCreated()) {
                    request.append("You did not try our application yet. you may create a canvas with the 'C' command, or use HELP to get hints.");
                } else {
                    request.append("All your drawings will be destroyed!");
                }
                request.append("\n");
                String choice = this.inputService.requestInputLine(request.append("Are you sure you want to quit? (y)").toString());
                if (choice.equalsIgnoreCase("y")) {
                    if (!this.canvasService.canvasNotYetCreated()) {
                        this.out.println("Ok you will quit. The following canvas is the final result:");
                        this.outputService.print(this.canvasService.getCanvas());
                    }
                    break;
                }

            } catch (ErrorMessageException e) {
                this.outputService.print(e.getMessage());
            } catch (Exception e) {
                this.outputService.print(e);
            }
        }
        this.out.println("You quit our Canvas Application. See you soon !");
    }
}
