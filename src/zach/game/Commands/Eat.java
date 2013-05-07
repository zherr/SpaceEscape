package zach.game.Commands;

import zach.game.Main.*;



public class Eat implements Response
{
    private Game game;
    
    /**
     * Let's the user attempt to eat an item that they have in their
     * inventory.
     */
    public boolean execute(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to pick up...
            System.out.println("Eat what?");
            return false;
        }
        String goodItems = "ChocolateCake,";//, badItems = "goo,";
        String itemName = command.getSecondWord();
        Player currentPlayer = game.getCurrentPlayer();
        Item item = currentPlayer.getCurrentInventory().getItem(itemName);
        if(item != null){
            if(item.isEdible()){
                if(goodItems.indexOf(item.getItemName()) >=0){
                    currentPlayer.changeMaxWeight(item.getWeight());
                    currentPlayer.getCurrentInventory().removeItem(item);
                    System.out.println("Your maximum weight capacity went up!: " +
                                    currentPlayer.getMaxWeight());
                    return false;
                }
                else{
                    currentPlayer.changeMaxWeight(-item.getWeight());
                    currentPlayer.getCurrentInventory().removeItem(item);
                    System.out.println("Your maximum weight capacity went down!: " +
                                    currentPlayer.getMaxWeight());
                    return false;
                }
            }
            else{
                System.out.println("That's not edible!!");
                return false;
            }
        }
        
        System.out.println("You don't have that item!");
        return false;
    }
    /**
     * Constructor for objects of class Eat
     */
    public Eat(Game game){
        this.game = game;
    }
}