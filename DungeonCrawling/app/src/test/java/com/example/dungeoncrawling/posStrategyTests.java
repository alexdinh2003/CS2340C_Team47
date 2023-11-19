package com.example.dungeoncrawling;

import org.junit.Test;

import com.example.dungeoncrawling.model.playermove.DirectionStrategy;
import com.example.dungeoncrawling.model.Player;
import com.example.dungeoncrawling.model.playermove.Down;
import com.example.dungeoncrawling.model.playermove.Left;
import com.example.dungeoncrawling.model.playermove.Right;
import com.example.dungeoncrawling.model.playermove.Up;
import com.example.dungeoncrawling.model.map.MapLayout;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
public class posStrategyTests {

    @Test
    public void setPosTest() {
        Player p = Player.getInstance();
        p.setPosition(1, 2);
        assertEquals(p.getRow(), 1);
        assertEquals(p.getCol(), 2);
    }

    @Test
    public void setPosArrTest() {
        Player p = Player.getInstance();
        int[] newArray = {3, 4};
        p.setInitalPosition(newArray);
        assertEquals(p.getRow(), 3);
        assertEquals(p.getCol(), 4);
    }

    @Test
    public void getColTest() {
        Player p = Player.getInstance();
        int[] newArray = {33, 44};
        p.setInitalPosition(newArray);
        int col = p.getCol();
        assertEquals(col, 44);
    }

    @Test
    public void getRowTest() {
        Player p = Player.getInstance();
        int[] newArray = {33, 44};
        p.setInitalPosition(newArray);
        int row = p.getRow();
        assertEquals(row, 33);
    }

    @Test
    public void implementsStrategyInterface() {

        Right right = new Right();
        List<Class<?>> rInter = Arrays.asList(right.getClass().getInterfaces());
        assertTrue(rInter.contains(DirectionStrategy.class));

        Up up = new Up();
        List<Class<?>> uInter = Arrays.asList(up.getClass().getInterfaces());
        assertTrue(uInter.contains(DirectionStrategy.class));

        Down down = new Down();
        List<Class<?>> dInter = Arrays.asList(down.getClass().getInterfaces());
        assertTrue(dInter.contains(DirectionStrategy.class));

        Left left = new Left();
        List<Class<?>> lInter = Arrays.asList(left.getClass().getInterfaces());
        assertTrue(lInter.contains(DirectionStrategy.class));
    }

    @Test
    public void startPosMatchesRoom() {
        MapLayout map0 = new MapLayout(0);
        MapLayout map1 = new MapLayout(1);
        MapLayout map2 = new MapLayout(2);

        int[] pos0 = new int[]{23, 15};
        int[] pos1 = new int[]{24, 1};
        int[] pos2 = new int[]{1, 1};

        assertEquals(map0.getStartPos()[0], pos0[0]);
        assertEquals(map0.getStartPos()[1], pos0[1]);

        assertEquals(map1.getStartPos()[0], pos1[0]);
        assertEquals(map1.getStartPos()[1], pos1[1]);

        assertEquals(map2.getStartPos()[0], pos2[0]);
        assertEquals(map2.getStartPos()[1], pos2[1]);
    }


}
