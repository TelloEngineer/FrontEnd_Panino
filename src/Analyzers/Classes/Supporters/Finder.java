package Analyzers.Classes.Supporters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Analyzers.Interface.Information.Belongs;



public class Finder {
    public enum Matched {
        NO_ONE,
        FIRST,
        EVERYONE
    }
    private String subString;
    private int start;
    private int end;
    private Pattern pattern;
    private Matcher matcher;
    private String inString;
    
    public Finder(String inString) {
        this.inString = inString;
    }
    public String getInString() {
        return inString;
    }
    public void setInString(String inString) {
        this.inString = inString;
    }
    public Finder() {
        this.subString = " ";
        this.start = 0;
        this.end = 0;
        this.inString = " ";
    }
    public String getSubString() {
        return subString;
    }
    public int getStart() {
        return start;
    }
    public int getEnd() {
        return end;
    }

    /**
     * va a encontrar la expresion regular especificada. va a empezar a buscar,
     * desde startIn
     * @param regular_expression
     * @param startIn
     * @return Belongs
     */
    public Belongs retrieve(String regular_expression, int startIn){
        pattern = Pattern.compile(regular_expression); //defino la expresion regular del automata
        matcher = pattern.matcher(this.inString); //introdusco la cadena en el automata
        final boolean finded = matcher.find(startIn); //reviso si se encuentra esa expresion esta en la cadena 
        if (!finded){
            return Belongs.NO;
        }
        this.start = matcher.start();
        this.end = matcher.end();
        this.subString = inString.substring(this.start,this.end);
        return Belongs.YES;
    }
    /**
     * va a encontrar la expresion regular especificada.
     * @param regular_expression
     * @return Belongs
     * 
     */
    public Belongs retrieve(String regular_expression){
        return retrieve(regular_expression, 0);
    }
    
    public Matched retrieveBetween(String First_concurrency, String Second_concurrency,int start_in){
        Belongs found = retrieve(First_concurrency,start_in);
        int start, end;
        switch(found)
        {
            case YES:
                break;
            case NO:
                this.start = -1;
                this.end = -1;
                this.subString = " ";
            return Matched.NO_ONE;
        }
        start = this.start;
        found = retrieve(Second_concurrency, this.end);
        switch(found)
        {
            case YES:
                break;
            case NO:
                this.start = start;
                this.end = -1;
                this.subString = " ";
            return Matched.FIRST;
        }
        end = this.end;
        this.start = start;
        this.end = end;
        this.subString = inString.substring(this.start,this.end);
        return Matched.EVERYONE;
    }
    public Matched retrieveBetween(String First_concurrency, String Second_concurrency){
        return retrieveBetween(First_concurrency, Second_concurrency,0);
    }
}
