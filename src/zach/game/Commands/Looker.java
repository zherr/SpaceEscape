package zach.game.Commands;

import java.util.ArrayList;

import zach.game.Main.*;

/**
 * Write a description of class Looker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Looker implements Response
{
    // instance variables - replace the example below with your own
    private Game game;
    /**
     * A look command to let the user see what items (or monsters) are
     * in a room.
     */
    public boolean execute(Command command) 
    {
        Player currentPlayer = game.getCurrentPlayer();
        ArrayList<Item> items = currentPlayer.getCurrentRoom().getInventory().getItems();
        
       if(command.hasSecondWord()){
            if("inventory".indexOf(command.getSecondWord()) > -1){
                System.out.println(currentPlayer.getCurrentInventory());
                return false;
            }
            else{
                System.out.println("Look at what?\n");
                return false;
            }
       }
        
        //if(!command.hasSecondWord()) {
       else{
            if (items.size() == 0) {
                System.out.println("There aren't any items in your current location\n");
            }
            else {
                for (Item i : items)
                {
                    System.out.println("You see: " + i.getItemName());
                }
                System.out.println();
            }
            return false;
        }
       // return false;
    }

    /**
     * Constructor for objects of class Looker
     */
    public Looker(Game game)
    {
        this.game = game;
    }
}
