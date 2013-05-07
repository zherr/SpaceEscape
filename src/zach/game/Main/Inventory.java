package zach.game.Main;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Set;

import zach.game.Helper.*;
/**
 * The inventory class keeps track of the location of game items.
 * 
 * 
 * @author (Jon Gosling) 
 * @version (v1.0 03/30/11)
 */
public class Inventory
{
    // instance variables
    
    // key=Item.itemLocation, value=the item
    private HashMap<String, ArrayList<Item>> allItems;  //for global inventory
    private ArrayList<Item> items;
    private FileUtil fileutil;
    /**
     * Constructor for objects of class Inventory
     * 
     * Creates initial game inventory with location and size parameters.
     */
    public Inventory() 
    {
        allItems = new HashMap<String, ArrayList<Item>>();
        items = new ArrayList<Item>();
    }
    
    /**
     * Loads a global list of all of the game items.
     */    
    public static Inventory loadGlobalInventory(String fileName)
    {
       Scanner in = ResourceUtil.openFileScanner(fileName);
       if (in == null) {
           System.out.println("File not found: " + fileName);
           return null;
       }
       
       Inventory inv = new Inventory();
       
       while (in.hasNext()) {
           Item i = new Item(in);
           inv.addGlobalItem(i);
       }
       in.close();
       
       return inv;
    }
    
    /**
     * Get the number of items in the Inventory.
     * @return The number of items in the inventory.
     */
    public int getCount(){
        return items.size();
    }
    
    /**
     * Return the list of items.
     * @return The ArrayList of items.
     */
    public ArrayList<Item> getItems()
    {
        return items;
    }
    
    /**
     * Return an item specified by name.
     * @param The name of the item.
     * @return The item specified.
     */
    public Item getItem(String itemName){
        for(Item item : items){
            if(itemName.toLowerCase().equals(item.getItemName().toLowerCase())){
                return item;
            }
        }
        return null;
    }
    
    /**
     * Adds an item to the global inventory.
     * 
     * @param The item to be added to the global inventory.
     */
    public void addGlobalItem(Item item)
    {
        // get item location, which we will use as the hashmap key
        String location = item.getItemLocation();
        // get the item list using the key
        ArrayList<Item> itemList = allItems.get(location);
        if (itemList == null)
        {
            // if this key hasn't been added to the hashmap yet,
            // allocate the list
            itemList = new ArrayList<Item>();
        }
        // add the item to the list of items
        itemList.add(item);
        // set the list of items in the hashmap
        allItems.put(location, itemList);
    }

    /**
     * Gets inventory based on room location.
     * 
     * @param location for which we want to retrieve the items from the 
     * global inventory.
     */
    public Inventory getInventory(String location)
    {
        Inventory inventory = new Inventory();
        for(String key : allItems.keySet())
        {
            if (key.equals(location))
            {
                ArrayList<Item> itemList = allItems.get(key);
                for (Item i : itemList)
                {
                    inventory.addAnItem(i);
                }
            }
        }
        return inventory;
    } 
    
    /**
     * Gets the weight of the entire inventory.
     * @return The weight of the entire inventory.
     */
    public int getInventoryWeight(){
        int invWeight = 0;
        for(Item item : items){
            invWeight += item.getWeight();
        }
        return invWeight;
    }
    
    /**
     * Checks if an item is contained within the inventory.
     * @param The name of the item.
     * @return True if it is in th einventory, false if it is not.
     */
    public boolean itemExist(Item aItem)
    {
        for(Item item : items){
            if(item.equals(aItem)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Adds an item to the list of items.
     * @param The item to add to the list.
     */
    public void addAnItem(Item aItem){
        items.add(aItem);
    }
    
    //
    
    /**
     * Add an item to the local inventory if it exists.
     * @return True if it was added, false it wasn't.
     */
    public boolean addItem(Item aItem){
        if (!(itemExist(aItem)))
        {
            items.add(aItem);
            return true;
        }
        return false;
    }
    
    /**
     * Remove an item from the local inventory if it exists.
     * @return True if it was removed, false it wasn't there.
     */
    public boolean removeItem(Item aItem)
    {
        if (itemExist(aItem))
        {
            items.remove(aItem);
            return true;
        }
        return false;
    }
    
    /**
     * Returns all of the items in the inventory.
     * 
     * @return A list of the items
     */
    public void showGlobalInventory()
    {
        String list = "";
        for(String key : allItems.keySet())
        {
            ArrayList<Item> itemList = allItems.get(key);
            for(Item item : itemList)
            {
                list = list + item + "\n";
            }
        }
        System.out.println(list);
    }
    
    /**
     * A toString method for the inventory class.
     * @return The string of the inventory's attributes.
     */
    public String toString(){
        String theInventory = "Items: ";
        for(Item item : items){
            theInventory = theInventory + item.getItemName() + "; ";
        }
        Scanner in = new Scanner(theInventory);
        theInventory = fileutil.lineWrap(in);
        return theInventory;
    }
    
}
