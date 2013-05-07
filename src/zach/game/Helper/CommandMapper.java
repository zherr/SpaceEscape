package zach.game.Helper;

import java.util.HashMap;

import zach.game.Commands.*;
import zach.game.Main.*;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.
 * 
 * This version holds mapping from command words to Responses
 *
 * Bad example -- there is no need for this class to have an instance.
 * It has been easily redone with all static methods!
 */

public class CommandMapper
{
    // a constant array that holds all valid command words
    private static HashMap<String, Response> responses; //responses to commands
    
    /**
     * Initialize the command response mapping
     * @param game The game being played.
     */
    public static void init(Game game) {
        responses = new HashMap<String, Response>();
        responses.put("quit", new Quitter());
        responses.put("go", new Goer(game)); //need game to change state
        responses.put("back", new Goer(game));
        responses.put("help", new Helper());
        responses.put("look", new Looker(game));
        responses.put("take", new Take(game));
        responses.put("drop", new Drop(game));
        responses.put("fix", new Fix(game));
        responses.put("eat", new Eat(game));
        //responses.put("fight",new Fight(game));
        // add new commands and Responders here!
    }

    /**
     * Check whether a given String is a valid command word. 
     * @param aString The possible command word.
     * @return true if it is, false if it isn't.
     */
    public static boolean isCommand(String aString)
    {
        return responses.get(aString) != null;
    }

    /**
     * Return the command associated with a command workd.
     * @param cmdWord The command word.
     * @return the Response forthe command.
     */
    public static Response getResponse(String cmdWord)
    {
        return responses.get(cmdWord);
    }

    /**
     * Return a string containing all valid commands.
     */
    public static String allCommands() 
    {
        String all = "";
        for(String cmd : responses.keySet()) 
            all += cmd + "  ";
        return all;
    }
}
