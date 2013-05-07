package zach.game.Main;

import java.util.Set;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList; ///////////////////////

import zach.game.Helper.FileUtil;
import zach.game.Helper.ResourceUtil;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling and David J. Barnes
 * modified by Andrew Harrington to use FileUtil
 * @version 2009.03.18
 */

public class Room 
{
    private String description;
    private String location;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private Inventory inventory; // the room's items
    private ArrayList<Monster> monsters;
    private FileUtil fileutil;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String location) //String item,
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.location = location;
        inventory = new Inventory();
        monsters = new ArrayList<Monster>();
    }

    /**
     * Create rooms and their interconnections by taking room names, exit data
     * and descriptions from a text file, and return a map of room names
     * to rooms.  File format for each room:  <br>
     * First line:  room name (one word) <br>
     * Second line: pairs of exit direction and neighbor room name  <br>
     * Remaining paragraph: room description, blank line terminated 
     * @param filename Name of file with all the room data
     * @return A map of room names to rooms
     */
    public static HashMap<String, Room> createRooms(String fileName)
    {
       Scanner in = ResourceUtil.openFileScanner(fileName);
       if (in == null) {
           System.out.println("File not found: " + fileName);
           return null;
       }
       
       // Map to return
       HashMap<String, Room> rooms = new HashMap<String, Room>();
       
       // temporary Map to delay recording exits until all rooms exist
       HashMap<String, String> exitStrings = new HashMap<String, String>();
       
       while (in.hasNext()) {
           String name = in.nextLine().trim();
           //String item = in.nextLine().trim();
           String exitPairs = in.nextLine();
           String description = FileUtil.readParagraph(in);
           rooms.put(name, new Room(description, name));//item,
           exitStrings.put(name, exitPairs);
       }
       in.close(); 
       
       // need rooms before you can map exits
       // go back and use exitPairs to map exits:
       for (String name : rooms.keySet()) {
           Room room = rooms.get(name);
           Scanner lineIn = new Scanner(exitStrings.get(name));
           // extract direction and neighbor pairs
           while (lineIn.hasNext()) {
               String direction = lineIn.next();
               String neighbor = lineIn.next();
               room.setExit(direction, rooms.get(neighbor));
           }
       }
       
       return rooms;            
    }
    
    /**
     * Gets the room inventory.
     * @return The users inventory.
     */
    public Inventory getInventory()
    {
        return inventory;
    }   
    
    /**
     * Gets the location of the room.
     * @return The room location.
     */
    public String getLocation()
    {
        return location;
    }
    
    /**
     * Load room's monsters from the global monters based on the room's location.
     * @param The global monster list of items
     */
    public void loadMonsters(ArrayList<Monster> globalMonsters)
    {
        for(Monster monster : globalMonsters){
            if(monster.getMonsterLocation().equals(location)){
                monsters.add(monster);
            }
        }
    }

    /**
     * Load room's inventory from the global inventory based on the room's location.
     * @param globalInventory The global inventory of items.
     */
    public void loadInventory(Inventory globalInventory)
    {
        inventory = globalInventory.getInventory(location);
    }
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * Gets the room description.
     * @return The short description of the room
     */
    public String getShortDescription()
    {
        return description;
    }
    
    //     /**
    //      * @return The item in that room.
    //      * (the one defined in the constructor).
    //      */
    //     public String getItem()
    //     {
    //         if(item.equals("nothing"))
    //             return null;
    //         return item;
    //     } 
    
    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + "\n" + getExitString() + "\n";
    }
    
    /**
     * Return a string describing the room.exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /** 
     * Show the monsters in the room.
     * @return The monsters in the room.
     */
    public String showMonsters(){
        String monsterStr = "";
        for(Monster monster : monsters){
            monsterStr += monster;
        }
        return monsterStr;
    }
    
    /**
     * Checks if the room has a monster or not.
     * @return True if it does contain a monster, false if not.
     */
    public boolean hasMonster(){
        if(monsters.size() > 0){
            return true;
        }
        return false;
    }
    
    /**
     * Gets the list of monsters in a room.
     * @return The array list of monsters.
     */
    public ArrayList<Monster> getMonsters(){
        return monsters;
    }
    
    /**
     * Add an item to this room.
     * @param The item to add.
     * @return True if the item was added, false if it was not.
     */
    public boolean addRoomItem(Item aItem)
    {
        return inventory.addItem(aItem);
    }
    
    /**
     * Remove an item from this room.
     * @param The item to remove.
     * @return True if the item was removed, false if it was not.
     */
    public boolean removeRoomItem(Item aItem)
    {
        return inventory.removeItem(aItem);
    }
}

