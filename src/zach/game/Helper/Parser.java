package zach.game.Helper;

import java.util.Scanner;

import zach.game.Commands.Command;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * Switched to static by ANH
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2005.03.08    AH:  Use UI to read the keyboard. 
 */
public class Parser 
{

    /**
     * Parse the next user command.
     * @return The user's command.
     */
    public static Command getCommand() 
    {
        String inputLine = "";   // will hold the full input line
        String word1;
        String word2;

        String line = UI.readLine("> ");  // AH change
        Scanner scan = new Scanner(line); // String source for the Scanner
        
        if(scan.hasNext())
            word1 = scan.next();      // get first word
        else
            word1 = null;
        if(scan.hasNext())
            word2 = scan.next();      // get second word
        else
            word2 = null;

        // note: we just ignore the rest of the input line.

       // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if(CommandMapper.isCommand(word1)) {
            System.out.println();
            return new Command(word1, word2);
        }
        else {
            System.out.println();
            return new Command(null, word2); 
        }

    }

}
