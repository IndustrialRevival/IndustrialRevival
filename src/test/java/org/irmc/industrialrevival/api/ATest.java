package org.irmc.industrialrevival.api;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ATest {

    @DisplayName("Test test afterAll")
    @AfterAll
    public static void afterAll() {
    }

    @DisplayName("Test test beforeAll")
    @BeforeAll
    public static void beforeAll() {
    }

    @DisplayName("Test test beforeEach")
    @BeforeEach
    public void beforeEach() {
    }

    @DisplayName("Test test Test")
    @Test
    void aTest() {
        Assertions.assertTrue(true);
    }
}
