package io.elkaim.canvas.challenge;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
@RunWith(JUnitPlatform.class)
public class CrashTests {

    @Test
    @Disabled("Is ignored")
    public void should_behave(){

        Assumptions.assumeFalse(false, "Kazaw");
    }
}
