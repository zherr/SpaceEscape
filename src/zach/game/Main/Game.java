package zach.game.Main;

import java.util.*;
import javax.swing.*;//for jpeg map

import java.awt.*;//for jpeg map
import java.io.*;
import javax.imageio.ImageIO;//for jpeg map

import zach.game.Commands.*;
import zach.game.Helper.*;

import java.net.*;
/**
 *  This class is the main class of the "Space Escape" application. 
 *  "Space Escape" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *  
 *  This version is modified by Andrew Harrington to use the UI and FileUtil
 *  utility classes.  Room data comes from a text file rather than being
 *  hard-coded into the program.  This implementation also allows all rooms
 *  to be accessed by name using the HashMap rooms in the Game class.
 *  
 *  A main method is also provided in the Game class.
 *  All commands are executed by classes satifying the Response interface,
 *  so the main class does not have the code for the commands.
 *  The CommandWords class is replaced by a CommandMapper class that
 *  not onlhy know the command words, but also the mapping to Responses. 
 *  The Parser and CommandMapper classes are made all static.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class Game 
{
    
    private FileUtil fileUtil; // allows word wrapping
    private HashMap<String, Room> rooms; //allows all rooms to be found by name
    private Player currentPlayer;
    private ArrayList<Monster> monsters;
    private Stack<Room> prevRooms;
    private URL url;
    /** Make a Game playable from the command line. */
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
    
    /**
     * Return the current player.
     * @return The current player.
     */
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    
    /**
     * Return the monsters.
     * @return The monsters in the game.
     */
    public ArrayList<Monster> getMonsters(){
        return monsters;
    }
    
    /**
     * Return the Room associated with a name.
     * @param roomName The name of the desired Room.
     * @return the associated Room.
     */
    public Room getNamedRoom(String roomName) {
        return rooms.get(roomName);
    }
    
    /**
     * Set the current room.
     * @param newRoom The new current Room.
     */
    public void setCurrentRoom(Room newRoom) {
        currentPlayer.setCurrentRoom(newRoom);
    }
    
    /**
     * Add an item to the player's inventory and remove it from the room they are in.
     * @param The item to add.
     * @return True if successful take.
     */
    public boolean addcurrentPlayerItem(Item aItem){
        if(currentPlayer.takeItem(aItem)){
            currentPlayer.getCurrentRoom().removeRoomItem(aItem);
            return true;
        }
        return false;
    }
    
    /**
     * Remove an item from the player's inventory and at it to the room they are in.
     * @param The item to drop.
     * @return True if successful drop.
     */
    public boolean removecurrentPlayerItem(Item aItem){
        if(currentPlayer.dropItem(aItem)){
            currentPlayer.getCurrentRoom().addRoomItem(aItem);
            return true;
        }
        return false;
    }
    
    /**
     * Returns the player to the previous room.
     */
    public void goBack(){
        
        if(prevRooms.empty()){
            System.out.println("There is no room to go back to!");
        }
        else{
            System.out.println();
            System.out.println(prevRooms.peek().getLongDescription());
            setCurrentRoom(prevRooms.peek());
            prevRooms.pop();
        }
    }
    
    /**
     * Gets the previous rooms.
     * @return The previous rooms.
     */
    public Stack<Room> getPrevRooms(){
        return prevRooms;
    }

    /**
     * "Pops" a room out of the stack when the user goes back to a previous room.
     */
    public void popRooms(){
        prevRooms.pop();
    }
    
    /**
     * "Pushes" a new room onto the stack when the user enters a new room.
     * @param The room to add to the stack.
     */
    public void pushRooms(){
        prevRooms.push(currentPlayer.getCurrentRoom());
    }
    
    /**
     * "Peeks" at the previous room in the stack so the user can go back to it.
     * @return The most recent room in the stack.
     */
    public Room peekRooms(){
        return prevRooms.peek();
    }
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        rooms = Room.createRooms("roomData.txt");
        Inventory globalInventory = Inventory.loadGlobalInventory("inventoryData.txt"); //initializes global inventory
        monsters = Monster.loadGlobalMonsters("monsterData.txt");
        //globalInventory.showGlobalInventory();  // for debugging purposes
        for (String key : rooms.keySet())
        {
            Room room = rooms.get(key);
            room.loadInventory(globalInventory);   // load inventory into each room
            room.loadMonsters(monsters);
        }
        currentPlayer = new Player("Hanson", rooms.get("medical"), 200, 75);
        prevRooms = new Stack<Room>();
        CommandMapper.init(this); // allowresponses to refer to game state
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        ShowMap();
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.               
        while (! processCommand(Parser.getCommand()))
            ;  // convention with isolated semiclon for an empty loop
        System.out.println("Thank you for playing Space Escape!");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        String line = "Welcome to HANSAN Space Escape Suite where escaping could be " +
        "quite defficult! Space Escape Suite is a new, awesome adventure game. "+
        "A study of your being is to commence at a reasearch facility located in a space "+
        "station. Chaos unfolds "+
        "after an attack on the space station and you were abandoned in a medical "+
        "room in station. You wake up restrained to an examination table and "+
        "no one is in the room. Your mission is to find a way out and get back home. "+
        "Careful where you choose to go, you might not come out alive. " +
        "The user instructions and jpeg map are in the sub-folder 'doc'." +
        " Type 'help' if you need help.";
        Scanner in = new Scanner(line);
        System.out.println(fileUtil.lineWrap(in));
        System.out.println();
        System.out.println(currentPlayer.getCurrentRoom().getLongDescription());
    }
    
    /**
     * Displays a jpeg map of all of the rooms.
     */
    public static void ShowMap() {
        
        ImageIcon image = new ImageIcon("RoomLayout.jpg");
        //ImageIcon image = new ImageIcon("image/pic1.jpg");
        JLabel label = new JLabel("", image, JLabel.CENTER);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add( label, BorderLayout.CENTER );
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(label);
        f.pack();
        f.setLocation(200,200);
        f.setVisible(true);
    }
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        Response response = CommandMapper.getResponse(commandWord);
        return response.execute(command);
    }

}
