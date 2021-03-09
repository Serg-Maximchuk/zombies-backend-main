package com.zorg.zombies.application;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GameSettingsTest {

    @Autowired
    private GameSettings gameSettings;

    @Test
    void getHumanWalkDelayMs() {
        assertNotEquals(0, gameSettings.getHumanWalkDelayMs());
    }
}
