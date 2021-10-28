package io.elkaim.canvas.challenge.integration;

import io.elkaim.canvas.challenge.command.executors.HelpCommandExecutor;
import io.elkaim.canvas.challenge.integration.utils.UserInputChain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class QuitApplicationITCase extends ApplicationLevelIntegration {


    @Test
    public void when_anytime_possible_to_quit() {
        InputStream scenario = UserInputChain.of("Q")
                .then("y")
                .then("HELP")
                .build();

        this.runFullAppScenario(scenario, this.outputStream);
        Assertions.assertFalse(this.outputStream.toString().contains(HelpCommandExecutor.HELP_INTRODUCTION));

        InputStream scenario2 = UserInputChain.of("C 40 4")
                .then("Q")
                .then("y")
                .then("HELP")
                .build();

        this.runFullAppScenario(scenario2, this.outputStream);
        Assertions.assertFalse(this.outputStream.toString().contains(HelpCommandExecutor.HELP_INTRODUCTION));

        InputStream scenario3 = UserInputChain.of("C 40 4")
                .then("P 3 3 r")
                .then("Q")
                .then("y")
                .then("HELP")
                .build();
        this.runFullAppScenario(scenario3, this.outputStream);
        Assertions.assertFalse(this.outputStream.toString().contains(HelpCommandExecutor.HELP_INTRODUCTION));

    }

    @Test
    public void user_can_change_its_decision_to_quit() {
        InputStream scenario = UserInputChain.of("Q")
                .then("n")
                .then("HELP")
                .build();

        this.runFullAppScenario(scenario, this.outputStream);
        Assertions.assertTrue(this.outputStream.toString().contains(HelpCommandExecutor.HELP_INTRODUCTION));


    }

}
