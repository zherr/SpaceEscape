package zach.game.Commands;

import zach.game.Helper.UI;
import zach.game.Main.*;

/** Quit Response */
public class Quitter implements Response
{
    /**
     * Constructor for objects of class Quitter
     */
    public Quitter()
    {
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    public boolean execute(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return UI.agree("Do you really want to quit? ");  // signal that we want to quit
        }
    }

}
