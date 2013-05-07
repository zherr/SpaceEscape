package zach.game.Helper;

import java.util.Scanner;

/**
 * File utilities for reading from a file with a Scanner.
 */
public class FileUtil
{    
    /**
     * Read a long line and return it wrapped into lines.
     * @param in
     *   The source of the next line of text.
     * @return  The line with newlines breaking it up. 
     */    
    public static String lineWrap(Scanner in)
    {  
        return wordWrap(in.nextLine(), 79);
    }
    
    /**
     * Add line breaks to s so it wraps within a specified width. 
     * @param s
     *   The string to break into lines - assumes no newlines.
     * @param columns
     *   The maximum number of columns to occupy after wrapping.
     * @return  The whole paragraph, with a newline after each line,
     *   except no newline after the final line. 
     */    
    public static String wordWrap(String s, int columns) 
    {
        String wrapped = "";
        s = s.trim();
        while (s.length() > columns) {
            int i = s.lastIndexOf(" ", columns);
            if (i == -1) i = columns;
            wrapped += s.substring(0, i).trim();
            s = s.substring(i).trim();
            if (s.length() > 0)
                wrapped += "\n";
        }
        if (s.length() > 0) 
            wrapped += s;
        return wrapped + "\n";        
    }
    
    /**
     * Read a paragraph consisting of one or more nonempty lines terminated
     * by an empty line, and return a single String, preserving the newlines.
     * The Scanner stops reading after the first empty line.
     * @param in
     *   The source of the paragraph text.
     * @return  The whole paragraph, with the newlines after each line,
     *   ending with the newline after the last nonempty line.
     */
    public static String readParagraph(Scanner in)
    {
        String para = "";
        String line = in.nextLine();
        while (line != null && line.trim().length() > 0) {
            para += line + ".";
            line = in.nextLine();
        }
        return para;
    }
}
