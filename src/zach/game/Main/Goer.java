package zach.game.Main;

import java.util.*;

import zach.game.Commands.Command;
import zach.game.Helper.UI;
/**
 * Response to try to go to a new room.
 */
public class Goer implements Response
{
   private Game game;
    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * @return false(does not end game)
     */
    public boolean execute(Command command) 
    {
        Player currentPlayer = game.getCurrentPlayer();
        
        if(command.getCommandWord().equals("back")){
            game.goBack();
            return false;
        }
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return false;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentPlayer.getCurrentRoom().getExit(direction);
        

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else if(nextRoom.hasMonster()){
            game.pushRooms();
            game.setCurrentRoom(nextRoom);
            currentPlayer.alterThreat(-nextRoom.getMonsters().get(0).getThreat());
            System.out.println(currentPlayer.getCurrentRoom().showMonsters());
            System.out.println("Chance of win: " + currentPlayer.getThreatLevel());
            if(UI.agree("Do you want to fight? ")){
                System.out.println();
                return fight();//call fight FUNCTION
            }
            else{
                currentPlayer.alterThreat(nextRoom.getMonsters().get(0).getThreat());
                game.goBack();
                return false;
            }
        }
        else {
            game.pushRooms();
            game.setCurrentRoom(nextRoom);
            System.out.println(currentPlayer.getCurrentRoom().getLongDescription());
            return false;
        }
        return false;
    }
    
    /**
     * Fights the monster that is in the room with the player.
     * @return True if the player is out of lives. False in any other case.
     */
    public boolean fight(){
        Random rand = new Random();
        ArrayList<Monster> monsters = game.getCurrentPlayer().getCurrentRoom().getMonsters();
        Monster monster1 = monsters.get(0); //Assuming there is one monster
        Player currentPlayer = game.getCurrentPlayer();
        Room currentRoom = currentPlayer.getCurrentRoom();
        
        int chance = rand.nextInt(101); 
        if (currentPlayer.getThreatLevel() >= chance){
            currentPlayer.alterThreat(monster1.getThreat());
            currentRoom.addRoomItem(monster1.getWeapon());
            monsters.remove(0);
            System.out.println("You defeated the alien.");
            System.out.println();
            System.out.println(currentPlayer.getCurrentRoom().getLongDescription());
            return false;
        }
        else if (currentPlayer.getLives() > 1){
            currentPlayer.alterLives(-1);
            currentPlayer.alterThreat(monster1.getThreat());
            currentPlayer.setCurrentRoom(game.getNamedRoom("medical"));
            game.getPrevRooms().clear();
            System.out.println("You lost the battle. You lost one life.");
            System.out.println();
            System.out.println(currentPlayer.getCurrentRoom().getLongDescription());
            return false;
        }
        else {
            System.out.println("You lost the battle and have no lives left. Game over.");
            return true;
        }
    }

    /**
     * Constructor for objects of class Goer
     */
    
    public Goer(Game game)
    {
        this.game = game; 
    }
}

