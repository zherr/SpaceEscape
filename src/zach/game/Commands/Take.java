package zach.game.Commands;

import zach.game.Main.*;



public class Take implements Response
{
    private Game game;
    /**
     * Lets the user take an item in the room that they are
     * currently in.
     */
    public boolean execute(Command command){
        Player currentPlayer = game.getCurrentPlayer();//local variable to get the player in game
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to pick up...
            System.out.println("Take what?");
            return false;
        }
        String itemName = command.getSecondWord();
        Item newItem = currentPlayer.getCurrentRoom().getInventory().getItem(itemName);
        if(newItem == null){
            System.out.println("That item isn't here!");
        }
        else if(game.addcurrentPlayerItem(newItem)){
            //setCurrentInventory(newitem); 
            System.out.println("You picked up the item!");
            System.out.println(newItem);
            currentPlayer.alterThreat(newItem.getItemThreat());
            System.out.println(currentPlayer.info());
            System.out.println();
            
            //System.out.println(currentPlayer.getCurrentInventory()); //test
        }
        else{
            System.out.println("You can't carry any more items! Too heavy..");
        }
    
        return false;
    }
    
    /**
     * Constructor for objects of class Take
     */
    
    public Take(Game game){
        this.game = game;
    }
}