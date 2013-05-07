package zach.game.Main;

import java.util.Scanner;

import zach.game.Helper.FileUtil;
/**
 * An item to be interacted with in the game.
 * 
 * @author (Jon Gosling) 
 * @version (v1.0 03/31/11)
 */
public class Item
{
    // instance variables
    private String itemName;
    private String itemDetail;
    private int itemWeight;
    private String itemLocation;
    private int itemThreat;
    private FileUtil fileutil;
    /**
     * Constructor for objects of class Item
     * 
     * Creates an item.
     * 
     * @param itemName the item's name
     * @param itemDetail the item's detailed description
     * @param itemWeight the item's weight
     * @param itemLocation the items current location
     */
    public Item(String itemName, String itemDetail, int itemWeight, String itemLocation, int itemThreat)
    {
        this.itemName = itemName;
        this.itemDetail = itemDetail;
        this.itemWeight = itemWeight;
        this.itemLocation = itemLocation;
        this.itemThreat = itemThreat;
    }
    
    /**
     * Constructs an item using a scanner.
     * @param The source of the scanner.
     */
    
    public Item(Scanner source){
        while(source.hasNextLine()){
           itemName = source.nextLine().trim();
           if(!(itemName.equals(""))){ 
                itemDetail = source.nextLine().trim();
                itemWeight = source.nextInt();
                source.nextLine();
                itemLocation = source.nextLine().trim();
                itemThreat = source.nextInt();
                //source.next();
                break;
           }
       }
    }
    
    /**
     *Checks if this item is equal in name to another item.
     *@param The item to compare to.
     *@return True if they are equal, false if they are not.
     */
    
    public boolean equals(Item aItem)
    {
        return (itemName.equals(aItem.itemName) && itemDetail.equals(aItem.itemDetail)
                && itemWeight == aItem.itemWeight && itemLocation.equals(aItem.itemLocation)
                && itemThreat == aItem.itemThreat);
    }    

    /**
     * This method gets the name of the item.
     * 
     * @return the name of the item.
     */
    public String getItemName()
    {
        return itemName;
    }
    
    /**
     * This method gets the weight of the item.
     * 
     * @return the weight of the item.
     */
    public int getWeight()
    {
        return itemWeight;
    }
    
    /**
     * This method gets the current location of the item.
     * 
     * @return the location of the item.
     */
    public String getItemLocation()
    {
        return itemLocation;
    }
    
    /**
     * This method gets the description of the item.
     *
     *@return The description of the item.
     */
    public String getDetail()
    {
        String detail = itemDetail;
        Scanner in = new Scanner(detail);
        detail = fileutil.lineWrap(in);
        return itemDetail;
    }
    
    /**
     * Gets the item's threat level.
     * @return The item's threat number.
     */
    public int getItemThreat(){
        return itemThreat;
    }
    
    /**
     * Checks to see if the item is edible.
     * @return True if the item is edible, false if it is not.
     */
    public boolean isEdible(){
        if(itemDetail.indexOf("edible") > -1){
            return true;
        }
        return false;
    }
    
    /**
     * A toString method that shows some of the item's fields.
     * @return A string of the item's fields. 
     */
    
    public String toString()
    {
        return "Description: " + itemDetail;
    }
    //     /**
    //      * Test.
    //      */
    //     public static void main(String[] args) {
    //         Item item = new Item("book", "an old dusty ", 45, "medical");
    //         System.out.print(item);
    //     }
}
