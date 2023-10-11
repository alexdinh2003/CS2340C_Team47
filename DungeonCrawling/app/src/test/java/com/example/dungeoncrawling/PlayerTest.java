package com.example.dungeoncrawling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import android.graphics.Rect;

import com.example.dungeoncrawling.model.Leaderboard;
import com.example.dungeoncrawling.model.Player;
import com.example.dungeoncrawling.model.ScoreEntry;

import org.junit.Test;

public class PlayerTest {

    @Test
    public void shouldGetSetName() {
        // Arrange
        Player player = new Player("Alex Dinh");

        // Act
        String name = player.getName();

        // Assert
        assertEquals("Alex Dinh", name);

        // Arrange
        Player player2 = new Player();

        // Act
        player2.setName("Alex Dinh");

        // Assert
        assertEquals("Alex Dinh", player.getName());
    }


    @Test
    public void shouldGetSetScore() {
        // Arrange
        Player player = new Player();
        player.setScore(100);

        // Act
        int score = player.getScore();

        // Assert
        assertEquals(100, score);

        // Arrange
        Player player2 = new Player();

        // Act
        player2.setScore(200);

        // Assert
        assertEquals(200, player2.getScore());
    }

    @Test
    public void shouldGeSetHealth() {
        // Arrange
        Player player = new Player();
        player.setHealth(100);

        // Act
        int health = player.getHealth();

        // Assert
        assertEquals(100, health);

        // Arrange
        Player player2 = new Player();

        // Act
        player2.setHealth(200);

        // Assert
        assertEquals(200, player2.getHealth());
    }

    @Test
    public void shouldGetSetSpriteId() {
        // Arrange
        Player player = new Player();
        player.setSpriteId(1);

        // Act
        int spriteId = player.getSpriteId();

        // Assert
        assertEquals(1, spriteId);

        // Arrange
        Player player2 = new Player();

        // Act
        player2.setSpriteId(2);

        // Assert
        assertEquals(2, player2.getSpriteId());
    }

    @Test
    public void shouldGetInstanceWithNameOrWithoutName() {
        // Arrange
        String name = "Alex Dinh";

        // Act
        Player player = Player.getInstance(name);

        // Assert
        assertEquals(name, player.getName());

        // Arrange

        // Act
        Player player2 = Player.getInstance();

        // Assert
        assertNotEquals("n/a", player2.getName());
    }


    @Test
    public void shouldGetInstanceWithId() {
        // Arrange
        int id = 1;

        // Act
        Player player = Player.getInstance("Alex Dinh", id, 5, 100, new Rect(0, 0, 64, 64));

        // Assert
        assertEquals(id, player.getSpriteId());
    }

    @Test
    public void shouldGetInstanceWithHealth() {
        // Arrange
        int health = 200;

        // Act
        Player player = Player.getInstance("Alex Dinh", 0, health, 100, new Rect(0, 0, 64, 64));

        // Assert
        assertNotEquals(health, player.getHealth());
    }

    @Test
    public void shouldGetInstanceWithPoints() {
        // Arrange
        int points = 200;

        // Act
        Player player = Player.getInstance("Alex Dinh", 0, 5, points, new Rect(0, 0, 64, 64));

        // Assert
        assertNotEquals(points, player.getScore());
    }

    @Test
    public void shouldHaveDifferentStartingLivesBasedOnDifficulty() {
        // Arrange
        int easyDifficultyStartingLives = 5;
        int mediumDifficultyStartingLives = 3;
        int hardDifficultyStartingLives = 1;

        // Act
        Player easyDifficultyPlayer = Player.getInstance("Alex Dinh", 0, easyDifficultyStartingLives, 100, new Rect(0, 0, 64, 64));
        Player mediumDifficultyPlayer = Player.getInstance("Alex Dinh", 0, mediumDifficultyStartingLives, 100, new Rect(0, 0, 64, 64));
        Player hardDifficultyPlayer = Player.getInstance("Alex Dinh", 0, hardDifficultyStartingLives, 100, new Rect(0, 0, 64, 64));

        // Assert
        assertEquals(easyDifficultyStartingLives, easyDifficultyPlayer.getHealth());
        assertNotEquals(mediumDifficultyStartingLives, mediumDifficultyPlayer.getHealth());
        assertNotEquals(hardDifficultyStartingLives, hardDifficultyPlayer.getHealth());
    }

    @Test
    public void shouldNotDecreaseScoreBelowZero() {
        // Arrange
        Leaderboard leaderboard = Leaderboard.getInstance();

        // Act
        leaderboard.addScore(new ScoreEntry("Alex Dinh", 0));
        leaderboard.addScore(new ScoreEntry("Alex Dinh", -1));

        // Assert
        assertNotEquals(0, leaderboard.getRecentAttempt().getScore());
    }
    
}