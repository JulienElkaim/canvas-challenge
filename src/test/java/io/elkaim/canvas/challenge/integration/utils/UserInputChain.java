package io.elkaim.canvas.challenge.integration.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserInputChain {
    private final List<String> inputs;

    private UserInputChain() {
        this.inputs = new ArrayList<>();
    }

    public static UserInputChain of(String... inputs) {
        UserInputChain userInputChain = new UserInputChain();
        Arrays.stream(inputs).forEach(userInputChain::then);
        return userInputChain;
    }

    public UserInputChain then(String input) {
        this.inputs.add(input);
        return this;
    }

    @Override
    public String toString() {
        return "\n".concat(String.join("\n", this.inputs));
    }

    public InputStream build() {
        return new ByteArrayInputStream(this.toString().getBytes());
    }
}
