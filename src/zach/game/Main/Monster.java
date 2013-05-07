package zach.game.Main;

import java.util.Scanner;
import java.util.ArrayList;

import zach.game.Helper.ResourceUtil;
/**
 * An interactive Monster to engage player in the game.
 * 
 * @author (Harrisson Adeoye) 
 * @version (1 04/14/11)
 */
public class Monster
{
    // instance variables
    private String MonsterName;
    private String MonsterDescription;
    private Item MonsterWeapon;
    private String MonsterLocation;
    private int threat;

    /**
     * Constructor for objects of class Monster
     * 
     * Creates an Monster.
     * 
     * @param MonsterName the Monster's name.
     * @param MonsterDescription the Monster's detailed description.
     * @param MonsterWeapon the Monster's weapon .
     * @param MonsterLocation the Monsters current location.
     * @param The monster's threat level.
     */
    public Monster(String MonsterName, String MonsterDescription, Item MonsterWeapon, String MonsterLocation, int threat)
    {
        this.MonsterName = MonsterName;
        this.MonsterDescription = MonsterDescription;
        this.MonsterWeapon = MonsterWeapon;
        this.MonsterLocation = MonsterLocation;
        this.threat = threat;
    }
    
    /**
     * Constructs an Monster using a scanner.
     * @param The source of the scanner.
     */
    
    public Monster(Scanner source){
        while(source.hasNextLine()){
           MonsterName = source.nextLine().trim();
           if(!(MonsterName.equals(""))){ 
                MonsterDescription = source.nextLine();
                
                //source.nextLine();
                MonsterLocation = source.nextLine();
                threat = source.nextInt();
                MonsterWeapon = new Item(source);
                //source.nextLine();
                break;
           }
       }
    }
    
    /**
     * Loads a global list of monsters.
     * @param The name of the file the monsters are in.
     */
    public static ArrayList<Monster> loadGlobalMonsters(String fileName)
    {
       Scanner in = ResourceUtil.openFileScanner(fileName);
       if (in == null) {
           System.out.println("File not found: " + fileName);
           return null;
       }
       
       ArrayList<Monster> monsters = new ArrayList<Monster>();
       
       while (in.hasNext()) {
           Monster i = new Monster(in);
           monsters.add(i);
       }
       in.close();
       
       return monsters;
    }
    
    
    /**
     *Checks if this Monster is equal in name to another Monster.
     *@param The Monster to compare to.
     *@return True if they are equal, false if they are not.
     */
    
    public boolean equals(Monster aMonster)
    {
        return (MonsterName.equals(aMonster.MonsterName) && MonsterDescription.equals(aMonster.MonsterDescription)
                && MonsterWeapon.equals(aMonster.MonsterWeapon) && MonsterLocation.equals(aMonster.MonsterLocation));
    }    

    /**
     * This method gets the name of the Monster.
     * 
     * @return the name of the Monster.
     */
    public String getMonsterName()
    {
        return MonsterName;
    }
    
    /**
     * This method gets the description of the Monster.
     *
     *@return The description of the Monster.
     */
    public String getDetail()
    {
        return MonsterDescription;
    }
    
    /**
     * This method gets the weight of the Monster.
     * 
     * @return the weight of the Monster.
     */
    public Item getWeapon()
    {
        return MonsterWeapon;
    }
    
    /**
     * This method gets the current location of the Monster.
     * 
     * @return the location of the Monster.
     */
    public String getMonsterLocation()
    {
        return MonsterLocation;
    }
    
    /** 
     * Gets the monster's threat level.
     * @return The threat number.
     */
    public int getThreat(){
        return threat;
    }
            
    /**
     * A toString method that shows all of the Monster's fields.
     * @return A string of the Monster's fields. 
     */
    
    public String toString()
    {
        return "Be careful, there is an alien that hasn't spotted you yet.\n"
        + MonsterName + "\n" + MonsterDescription 
         + "\nThreat level: " + threat;
    }

}
