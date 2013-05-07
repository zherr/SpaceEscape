package zach.game.Main;

import zach.game.Commands.Command;
import zach.game.Helper.CommandMapper;


/**
 * Help Response
 */
public class Helper implements Response
{

   /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    public boolean execute(Command cmd) 
    {
        System.out.println("You lost your way, but you must finish your");
        System.out.println("mission to escape from the alien space station.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(CommandMapper.allCommands());
        return false;
    }

    /**
     * Constructor for objects of class Helper
     */
    public Helper()
    {
    }
}