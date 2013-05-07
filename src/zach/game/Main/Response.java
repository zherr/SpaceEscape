package zach.game.Main;

import zach.game.Commands.Command;


/**
 * Object that responds to a command
 */
public interface Response
{
    /**
     * Execute a command.
     * 
     * @param  cmd  The full command getting executed
     * @return true if the game is over; false otherwise 
     */
    boolean execute(Command cmd);
}
