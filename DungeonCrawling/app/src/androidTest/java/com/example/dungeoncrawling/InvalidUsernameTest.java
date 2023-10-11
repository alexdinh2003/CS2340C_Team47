package com.example.dungeoncrawling;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.dungeoncrawling.model.UsernameValidator;

public class InvalidUsernameTest {

    @Test
    public void testValidUsername() {
        boolean isValid = UsernameValidator.isValidUsername("ValidName");
        assertTrue(isValid);
    }

    @Test
    public void testInvalidUsernameEmpty() {
        boolean isValid = UsernameValidator.isValidUsername("");
        assertFalse(isValid);
    }

    @Test
    public void testInvalidUsernameWhitespaceOnly() {
        boolean isValid = UsernameValidator.isValidUsername("     ");
        assertFalse(isValid);
    }
}
