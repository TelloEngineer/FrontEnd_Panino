package Analyzers.Classes.Supporters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Analyzers.Classes.Dates.Token;
import Analyzers.Interface.Information;


public class SyntaticManager implements Information{
    private Pattern pattern;
    private Matcher matcher;
    private String stateMessage;
    private int lastIndex;

    public SyntaticManager() {
    }

    @Override
    public String information() { //devuel el estado del analisis
        return stateMessage;
    }

    public Token firstToken(Token[] words, String inString){ //recibe Tokens y cadena a analizar
        int i = 0;
        for (i = 0; i < words.length; i++) { // recorre cada Token
            if(isThere(words[i].getLexema(),inString) == Belongs.YES){ //si encuentra uno
              return words[i];  //lo devuelve
            }
        }
        return new Token(); // si no, devuelve token en blanco
    }

     public int preanalisis(Token[] words, String inString){ //recibe Tokens y cadena a analizar
        int i = 0;
        for (i = 0; i < words.length; i++) { // recorre cada Token
            if(isThere(words[i].getLexema(),inString) == Belongs.YES){ //si encuentra uno
              return words[i].getId();   //devuelve id del mismo
            }
        }
        return '0'; //regresa cero, si no lo encuentra.
    }

    public int preanalisis(Token[] words, String inString, int index){ //recibe Tokens, index y cadena a analizar
        int i = 0;
        for (i = 0; i < words.length; i++) { // recorre cada Token
            if(isThere(words[i].getLexema(),inString, index) == Belongs.YES){ //si encuentra uno
              return words[i].getId();  //devuelve id del mismo
            }
        }
        return '0'; //regresa 0, si no encuntra nada
    }

    //quita los espacios en blanco
    public String removeWhite(String s) {
        
        // Creating a pattern for whitespaces
        pattern = Pattern.compile("[\\s]");

        // Searching patt in s.
        matcher = pattern.matcher(s);

        // Replacing
        return matcher.replaceAll("");
    }
    //devuelve la primera direccion sin espacio en blanco
    public int indexNoWhite(String string){
        Pattern noWhite = Pattern.compile("[^\\s]"); //expresion hasta donde no hay espacio en blanco
        Matcher findId = noWhite.matcher(string); //agarra la cadena a analizar
        final boolean wasFind = findId.find(); //ve si lo encontro
        if(!wasFind){ //si no lo encuentra, debuelve -1
            return -1;
        }
        return findId.start(); //si lo encuentra, devuelve la posicion donde se encontro.
    }

    public Belongs analizer(String regular_expression, String inString){
        pattern = Pattern.compile(regular_expression); //defino la expresion regular del automata
        matcher = pattern.matcher(inString); //introdusco la cadena en el automata
        int start, end; //lexema
        final boolean wasFind = matcher.find(); //reviso si se encuentra esa expresion esta en la cadena 
        final int init = indexNoWhite(inString); 
        if (!wasFind || (init == -1)){
            stateMessage = "no se encontro: " + regular_expression + " en linea: " + init;  
            return Belongs.NO; 
        }
        start = matcher.start();
        end = matcher.end();
        // System.out.println("start, " + start);
        if(start != init){ //si se encuentra en el lugar incorrecto.
            stateMessage = "deberia expresarse con la expresion regular:'" + regular_expression + "' en la linea: " + init ;  
            return Belongs.NO;
        }
        this.lastIndex = end;
        //System.out.println(end);
        stateMessage = inString.substring(start,end);  
        return Belongs.YES;
    }
    /* public Belongs analizer(String regular_expression, String inString){
            pattern = Pattern.compile(regular_expression); //defino la expresion regular del automata
            matcher = pattern.matcher(inString);
            // reviso si encontro la concurrencia : boolean
            // busco primer lugar sin espacios blancos : int
            // reviso lo anterior.  : if
            // guardo el inicio y fina :

    }*/

    public Belongs analizer_cursor(String regular_expression, String inString, int cursor){
        pattern = Pattern.compile(regular_expression); //defino la expresion regular del automata
        matcher = pattern.matcher(inString); //introdusco la cadena en el automata
        final boolean wasFind = matcher.find(); //reviso si se encuentra esa expresion esta en la cadena 
        int start, end; //lexema
        final int init = indexNoWhite(inString); 
        if (!wasFind || (init == -1)){
            stateMessage = "no se encontro: " + regular_expression + " en linea: " + (init+cursor);  
            return Belongs.NO; 
        }
        start = matcher.start();
        end = matcher.end();
        // System.out.println("start, " + start);
        if(start != init){ //si se encuentra en el lugar incorrecto.
            stateMessage = "deberia expresarse con la expresion regular:'" + regular_expression + "' en la linea: " + (init+cursor) ;  
            return Belongs.NO;
        }
        this.lastIndex = end;
        //System.out.println(end);
        stateMessage = inString.substring(start,end);  
        return Belongs.YES;
    }

    public Belongs isThere(String regular_expression, String inString){
        pattern = Pattern.compile(regular_expression); //defino la expresion regular del automata
        matcher = pattern.matcher(inString); //introdusco la cadena en el automata
        final boolean finded = matcher.find(); //reviso si se encuentra esa expresion esta en la cadena 
        int start, end; //lexema
        final int init = indexNoWhite(inString);  
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

    public Belongs firstOcurrency(String regular_expression, String inString){
        pattern = Pattern.compile(regular_expression); //defino la expresion regular del automata
        matcher = pattern.matcher(inString); //introdusco la cadena en el automata
        final boolean finded = matcher.find(); //reviso si se encuentra esa expresion esta en la cadena 
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

    public int indexNoWhite(String string, int index){
        Pattern noWhite = Pattern.compile("[^\\s]");
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
    /// obtiene el ultimo index obtenido
    public int getLastIndex(){
        return lastIndex;
    }
}

