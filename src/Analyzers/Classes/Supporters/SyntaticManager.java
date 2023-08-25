package Analyzers.Classes.Supporters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Analyzers.Interface.Information;

public class SyntaticManager implements Information{
    private Pattern pattern;
    private Matcher matcher;
    private String stateMessage;
    private int lastIndex;
    @Override
    public String information() {
        return stateMessage;
    }

    public String removeWhite(String s) {
        
        // Creating a pattern for whitespaces
        pattern = Pattern.compile("[\\s]");

        // Searching patt in s.
        matcher = pattern.matcher(s);

        // Replacing
        return matcher.replaceAll("");
    }
    public int indexNoWhite(String string, int index){
        Pattern noWhite = Pattern.compile("[^-\\s]");
        Matcher findId = noWhite.matcher(string);
        final boolean wasFind = findId.find(index);
        if(!wasFind){
            return -1;
        }
        return findId.start();
    }
    public Belongs analizer(String regular_expression, String inString, int index){
        pattern = Pattern.compile(regular_expression); //defino la expresion regular del automata
        matcher = pattern.matcher(inString); //introdusco la cadena en el automata
        final boolean wasFind = matcher.find(index); //reviso si se encuentra esa expresion esta en la cadena 
        int start, end; //lexema
        final int init = indexNoWhite(inString, index); 
        if (!wasFind || (init == -1)){
            stateMessage = "no se encontro: " + regular_expression + " en linea: " + init;  
            return Belongs.NO; 
        }
        start = matcher.start();
        end = matcher.end();
        // System.out.println("start, " + start);
        if(start != init){ //si se encuentra en el lugar correcto.
            stateMessage = "deberia expresarse con la expresion regular:'" + regular_expression + "' en la linea: " + init;  
            return Belongs.NO;
        }
        this.lastIndex = end;
        System.out.println(end);
        stateMessage = inString.substring(start,end);  
        return Belongs.YES;
    }
    public Belongs isThere(String regular_expression, String inString, int index){
        pattern = Pattern.compile(regular_expression); //defino la expresion regular del automata
        matcher = pattern.matcher(inString); //introdusco la cadena en el automata
        final boolean finded = matcher.find(index); //reviso si se encuentra esa expresion esta en la cadena 
        int start, end; //lexema
        final int init = indexNoWhite(inString, index);  
        if (!finded || (init == -1)){
            stateMessage = "There isn't";
            return Belongs.NO;
        }
        start = matcher.start();
        end = matcher.end();
        // System.out.println("start, " + start);
        if(start != init){ //si se encuentra en el lugar correcto.
            stateMessage = "There isn't in the correct place";
            return Belongs.NO;
        }
        this.lastIndex = end;
        stateMessage = inString.substring(start,end);
        return Belongs.YES;
    }
    public Belongs firstOcurrency(String regular_expression, String inString, int index){
        pattern = Pattern.compile(regular_expression); //defino la expresion regular del automata
        matcher = pattern.matcher(inString); //introdusco la cadena en el automata
        final boolean finded = matcher.find(index); //reviso si se encuentra esa expresion esta en la cadena 
        int start, end; //lexema 
        if (!finded){
            stateMessage = "There isn't any ocurrency";
            return Belongs.NO;
        }
        start = matcher.start();
        end = matcher.end();
        this.lastIndex = end;
        stateMessage = inString.substring(start,end);
        return Belongs.YES;
    }
    public int getLastIndex(){
        return lastIndex;
    }
}
