package zach.game.Commands;

import java.util.ArrayList;
import java.util.Scanner;

import zach.game.Helper.FileUtil;
import zach.game.Main.*;
public class Fix implements Response
{
    private Game game;
    private FileUtil fileutil;
    /**
     * Goes through the player's items and checks it against a list of items 
     * needed to win when the user types "Fix" in the correct room. 
     * @return True if the user has all of the winning items.
     */
    public boolean execute(Command command){
        ArrayList<String> winItems = new ArrayList<String>();
        winItems.add("medicaltubing"); winItems.add("oxygentank");
        winItems.add("rope"); winItems.add("SuperMicroWave");
        winItems.add("radar"); winItems.add("PortableEngine");
        winItems.add("semi-conductors"); winItems.add("SecurityKey");
        int winNum = 0;
        Room currentRoom = game.getCurrentPlayer().getCurrentRoom();
        ArrayList<Item> currentItems = game.getCurrentPlayer().getCurrentInventory().getItems();
        if(currentRoom == game.getNamedRoom("escape")){
            for(Item i : currentItems){
                for(String j : winItems){
                    if(i.getItemName().toLowerCase().equals(j.toLowerCase())){
                        winNum++;
                    }
                }
            }
            if(winNum == winItems.size()){
                Scanner in = new Scanner("You quickly gather all of your items "+
                        "and fix the escape pod. You start the launch sequence " +
                        "and escape off the mothership, hurdling towards Earth.");
                System.out.println(fileutil.lineWrap(in));      
                return true;
            }
            else{
                Scanner in = new Scanner("It appears that "+winNum+"/"+winItems.size()+
                        " of your items are apparently needed to fix the escape pod. "+
                        "If you have any, perhaps you should leave them in this room...");
                System.out.println(fileutil.lineWrap(in));
                return false;
            }
        }
        else{
            System.out.println("There is nothing to fix!");
            return false;
        }
        
    }
    
    /**
     * Constructor for objects of class Fix
     */
    
    public Fix(Game game){
        this.game = game;
    }
}