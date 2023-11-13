//package com.example.cs2340a_team43;
//import org.junit.Test;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import com.example.cs2340a_team43.Models.Map;
//import com.example.cs2340a_team43.Interfaces.CollisionObserver;
//import com.example.cs2340a_team43.Models.Player;
//import com.example.cs2340a_team43.Models.RunMovement;
//import com.example.cs2340a_team43.ViewModels.MapViewModel;
//import com.example.cs2340a_team43.ViewModels.PlayerViewModel;
//
//
//public class Sprint3UnitTests {
//    //Lauren Test 1
//    @Test
//    public void leftMovement() {
//        Player player = Player.getInstance();
//        player.setInitialXY(1, 1);
//        player.moveLeft();
//        int newX = player.getX();
//        assertEquals(0, newX);
//    }
//
//    //Joseph Test #1
//    @Test
//    public void rightMovement(){
//        Player player = Player.getInstance();
//        player.setInitialXY(1, 1);
//        int newX;
//        for (int i = 2; i < 102; i++) {
//            player.moveRight();
//            newX = player.getX();
//            assertEquals(i, newX);
//        }
//    }
//    //Joseph Test #2
//    @Test
//    public void upMovement(){
//        Player player = Player.getInstance();
//        player.setInitialXY(1, 101);
//        int newY;
//        for (int i = 0; i < 100; i++) {
//            player.moveUp();
//            newY = player.getY();
//            assertEquals(100 - i, newY);
//        }
//    }
//    //Vishnu Test #1
//    @Test
//    public void downMovement(){
//        Player player = Player.getInstance();
//        player.setInitialXY(1, 1);
//        int newY;
//        for (int i = 0; i < 100; i++) {
//            player.moveDown();
//            newY = player.getY();
//            assertEquals(i+2, newY);
//        }
//    }
//    @Test
//    public void testInitialXY() {
//        Player player = Player.getInstance();
//        player.setInitialXY(0,0);
//        int x = player.getX();
//        int y = player.getY();
//        assertEquals(0, x);
//        assertEquals(0, y);
//    }
//    @Test
//    public void testPlayerDoesNotMoveIfWall() {
//        MapViewModel mvm = new MapViewModel();
//        PlayerViewModel pvm = PlayerViewModel.getInstance();
//        pvm.setMap(mvm);
//        pvm.setInitialPlayerXY(1,1);
//        mvm.getRoomLayout()[1][0] = Map.MapObject.WALL;
//        assertEquals(1, pvm.getPlayerX());
//        assertEquals(1, pvm.getPlayerY());
//        pvm.movePlayerLeft(); // player coordinates should NOT change
//        assertEquals(1, pvm.getPlayerX());
//        assertEquals(1, pvm.getPlayerY());
//    }
//    @Test
//    public void testPlayerDoesMoveIfEmpty() {
//        MapViewModel mvm = new MapViewModel();
//        PlayerViewModel pvm = PlayerViewModel.getInstance();
//        pvm.setMap(mvm);
//        pvm.setInitialPlayerXY(1,1);
//        mvm.getRoomLayout()[1][0] = Map.MapObject.EMPTY;
//        assertEquals(1, pvm.getPlayerX());
//        assertEquals(1, pvm.getPlayerY());
//        pvm.movePlayerLeft(); // player x coordinate should change
//        assertEquals(0, pvm.getPlayerX());
//        assertEquals(1, pvm.getPlayerY());
//    }
//    //Cason test 2
//    @Test
//    public void testStrategyDesignPattern() {
//        Player player = Player.getInstance();
//        Player fastPlayer = new Player(new RunMovement());
//        player.setInitialXY(1, 1);
//        fastPlayer.setInitialXY(2,1);
//        player.moveLeft();
//        fastPlayer.moveLeft();
//        int X1 = player.getX();
//        int X2 = fastPlayer.getX();
//        assertEquals(0, X1);
//        assertEquals(0, X2);
//    }
//
//    //Vishnu Test #2
//    @Test
//    public void testObserverNotified() {
//        MapViewModel mvm = new MapViewModel();
//        PlayerViewModel pvm = PlayerViewModel.getInstance();
//        pvm.setMap(mvm);
//        pvm.setInitialPlayerXY(1,1);
//        CollisionObserver collisionObserver = new CollisionObserver() {
//            @Override
//            public void update() {
//                System.out.println("UPDATE");
//            }
//        };
//        pvm.addObserver(collisionObserver);
//        assertTrue(!pvm.isNotified()); //Test observer is not notified before action
//        pvm.movePlayerUp();
//        assertTrue(pvm.isNotified());
//
//    }
//
//}
//
//
