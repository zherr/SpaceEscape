package zach.game.Commands;

import zach.game.Main.*;

public class Drop implements Response
{
    private Game game;
    /**
     * Lets the user drop an item in whatever room they are
     * currently in.
     */
    public boolean execute(Command command){
        Player currentPlayer = game.getCurrentPlayer();
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to pick up...
            System.out.println("Drop what?");
            return false;
        }
        String itemName = command.getSecondWord();
        Item newItem = currentPlayer.getCurrentInventory().getItem(itemName);
        if(newItem == null){
            System.out.println("You can't drop an item you don't have!");
        }
        else{
            currentPlayer.alterThreat(-newItem.getItemThreat());
            game.removecurrentPlayerItem(newItem);//setCurrentInventory(newitem);
            System.out.println("You dropped the item!");
            System.out.println(currentPlayer.info());
            System.out.println();
            //System.out.println(currentPlayer.getCurrentInventory()); //test
        }
    
        return false;
    }
    
    /**
     * Constructor for objects of class Drop
     */
    
    public Drop(Game game){
        this.game = game;
    }

}