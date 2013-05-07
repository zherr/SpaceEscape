package zach.game.Main;


/**
 * This class enables the game to have a player so that they may pick
 * up items, have an inventory, and possibly have an aliment status.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Inventory currentItems;
    private int playerWeight;
    private int maxWeight;
    private Room currentRoom;
    private String name;
    private int lives = 5;
    private int threatLevel;
    /**
     * Constructor for objects of class Player
     */
    public Player(String name, Room room, int maxWeight, int threatLevel)
    {
        // initialise instance variables
        this.name = name;
        currentItems = new Inventory();
        this.maxWeight = maxWeight;
        currentRoom = room;
        playerWeight = 0;
        this.threatLevel = threatLevel;
    }
    
    /** Get the current room. 
     * @return The current room the player is in.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    /**
     * Get the player's weight.
     * @return The player's weight.
     */
    public int getPlayerWeight(){
        return playerWeight;
    }
    
    /**
     * Add or subtract to the player's maximum weight limit.
     *@param The weight to add or subtract
     */
    public void changeMaxWeight(int number){
        maxWeight += number;
    }
    
    /**
     * Get the player's maximun weight.
     * @return he player's maximum weight it can carry.
     */
    public int getMaxWeight()
    {
        return maxWeight;
    }
    
    /**
     * Get the player's current room it is in.
     * @return current position
     */ 
    public Room getRoom()
    {
        return currentRoom;
    }
    
    /**
     * Get the number of lives the player has.
     * @return The number of lives.
     */
    public int getLives(){
        return lives;
    }
    
    /**
     * Get the player's threat level.
     * @return The player's threat level.
     */
    public int getThreatLevel(){
        return threatLevel;
    }
    
    /**
     * Get the player's current items.
     * @return The inventory of the player.
     */
    public Inventory getCurrentInventory(){
        return currentItems;
    }
    /**
     * Set the current room.
     * @param newRoom The new current Room.
     */
    public void setCurrentRoom(Room newRoom) {
        currentRoom = newRoom;
    }
    
    /**
     * Get the player's name.
     * @return the players name.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Get the number of items in the player's inventory.
     * @return the number of items in inventory
     */
     public int inventorySize()
     {
         return currentItems.getCount();
 
     }
    
    //     /**
    //      * Checks if an item is in the current inventory.
    //      * @param The name of the item.
    //      * @return True if the item is in the inventory, false if it is not.
    //      */
    //     public boolean checkInv(Item aItem)
    //     {
    //         return currentItems.itemExist(aItem);
    //     }
    /**
     * Alters the player's number of lives.
     * @param The number to add to the player's lives.
     */
    public void alterLives(int num){
        lives += num;
    }
    
    /**
     * Alters the player's threat level.
     * @param The number to alter the player's threat level.
     */
    public void alterThreat(int increment){
        threatLevel += increment;
    }
    
     /**
     * Add an item to the player's current inventory if it is not already
     * there.
     * @param The item to add.
     * @return True if it was added, flase if it was not.
     */
    public boolean takeItem(Item aItem)
    {
        if((playerWeight + aItem.getWeight()) < maxWeight){
            if(currentItems.addItem(aItem)){
                playerWeight += aItem.getWeight();
                return true;
            }
       }
        return false;
    }
    
    /**
     * Remove an item from the player's inventory.
     * @param The item to remove.
     * @return True if it was removed, false if it was not.
     */
    public boolean dropItem(Item aItem)
    {
        if(currentItems.removeItem(aItem)){
            playerWeight -= aItem.getWeight();
            return true;
        }
        return false;
    }
    
    /**
     * Prints important information about the player's attributes.
     * @return The string with various info abou the player.
     */
    public String info(){
        String info = "";
        info += "Player weight = " + playerWeight+"/" + maxWeight + 
        "\nYour Threat Level: " + getThreatLevel();
        return info;
    }
        //     /**
        //      * Creating a new player and setting his/her weight limit, current
        //      * room, and the current items to nothing, or null.
        //      * @param The maximum weight the player can carry.
        //      * @param The room the player starts in.
        //      * @return The player that was created.
        //      */
        //     public static Player createPlayer(int max, Room room)
        //     {
        //         Player player = new Player();
        //         player.currentItems = null;
        //         player.maxWeight = max;
        //         player.currentRoom = room;
        //         return player;
        //     }
}
