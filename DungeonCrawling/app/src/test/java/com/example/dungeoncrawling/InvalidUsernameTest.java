//package com.example.dungeoncrawling;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import com.example.dungeoncrawling.viewmodels.PlayerCreation;
//
//public class InvalidUsernameTest {
//    private PlayerCreation playerCreation; // Assuming you have a PlayerCreation class
//
//    @Before
//    public void setUp() {
//        // Initialize PlayerCreation instance or any other setup
//        playerCreation = new PlayerCreation();
//    }
//
//    @Test
//    public void testValidUsername() {
//        // Provide a valid username
//        boolean isValid = playerCreation.isValidUsername("ValidName");
//        assertTrue(isValid);
//    }
//
//    @Test
//    public void testInvalidUsernameEmpty() {
//        // Provide an empty username
//        boolean isValid = playerCreation.isValidUsername("");
//        assertFalse(isValid);
//    }
//
//    @Test
//    public void testInvalidUsernameWhitespaceOnly() {
//        // Provide a username with whitespace characters only
//        boolean isValid = playerCreation.isValidUsername("     ");
//        assertFalse(isValid);
//    }
//
//    @Test
//    public void testInvalidUsernameWithSpecialCharacters() {
//        // Provide a username with special characters
//        boolean isValid = playerCreation.isValidUsername("Invalid@Name");
//        assertFalse(isValid);
//    }
//}
