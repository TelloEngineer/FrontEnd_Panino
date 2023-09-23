package Analyzers.Classes.Grammars;

import java.util.List;

import Analyzers.Classes.Dates.Token;
import Analyzers.Classes.Supporters.LexycalComponents;
import Analyzers.Classes.Supporters.SyntaticManager;
import Analyzers.Interface.GetLexycal;
import Analyzers.Interface.Grammar;

public class GrammarV1_0 implements Grammar, GetLexycal{
    private String inString;
    private LexycalComponents component;
    private SyntaticManager lexical;
    private Token[] wordsToAnalize;
    private String stateGrammar;
    private int index, cursor;
    public GrammarV1_0(){
        component = new LexycalComponents();
        lexical = new SyntaticManager();

        //definimos nuestros token, declaramos nuestro universo de símbolos, en base a estos haremos el análisis 
        wordsToAnalize = new Token[]{
                new Token("operador logico OR", "||", "[|]{2}", 15),
                new Token("operador logico AND", "&&", "[&]{2}", 16),
                new Token("operador logico igualdad", "==", "==", 17),
                new Token("operador logico igual mayor","<=","<=",18),
                new Token("operador logico igual menor",">=",">=",19),
                new Token("operador relacional menor que","<", "[<]", '<'),
                new Token("operador relacional mayor que", ">", "[>]", '>'),
                new Token("punto y coma",";", "[;]", ';'),
                new Token("coma",",","[,]",','),
                new Token("parentesis abierto","(", "[(]", '('),
                new Token("parentesis cerrado",")", "[)]", ')'),
                new Token("llave abierta", "{","[{]",'{'),
                new Token("llave cerrada","}","[}]",'}'),
                new Token("asignacion","=","[=]",'='),
                new Token("Comentario","/*","[/][*]",'#'),
                new Token("operador aritmetico multiplicacion","*", "[*]", '*'),
                new Token("operador aritmetico division","/", "[/]", '/'),
                new Token("operador aritmetico suma","+", "[+]", '+'),
                new Token("operador aritmetico resta","-", "[-]", '-'),
                new Token("numero decimal","", "[0-9]+.[0-9]+", 'f'),
                new Token("numero entero","", "[0-9]+", 'e'),
                new Token("palabra reservada if", "if", "if", 9),
                new Token("palabra reservada while","while", "while", 10),
                new Token("palabra reservada return","return","return",11),
                new Token("palabra reservada else","else","else", 12),
                new Token("palabra reservada int","int","int",1),
                new Token("palabra reservada float","float", "float", 2),
                new Token("palabra reservada void","void","void",0),
                new Token("identificador","", "[a-zA-Z][a-zA-Z0-9]*", 'i')
        };
    }

    private boolean found(){
        //System.out.println("coincidi");
        //inString manda el conjunto de cadenas ingresadas por el usuario
        Token tokenFound = lexical.firstToken(wordsToAnalize, inString);    //buscamos los token dentro de inString
        
        //al regresar el token, lo quitamos de inString
        if(!(1 < inString.length())){   //si ya no hay caracteres en inString, se termina el análisis
           stateGrammar = "fin de la cadena, cursor: " + cursor;
           return true; 
        }
        if(tokenFound.equals(new Token())){
            stateGrammar = "no se encontro mas tokens validos, cursor: " + cursor;
            return true;
        }
        int indexAdded = lexical.getLastIndex();
        switch(tokenFound.getId()){             //si encontró el token entonces lo regresa, sino -> actualiza el listado de tokens
            case 'i': case 'e': case 'f':       //si es identificador, número decimal o entero, entonces imprimimos su representación
                tokenFound.setRepresentation(lexical.information());
            break;
            case '#':                           //si es comentario entonces sólo lo quitamos de nuestras cadenas a analizar
                lexical.firstOcurrency("[*][/]", inString, indexAdded);
                indexAdded = lexical.getLastIndex();
                index = index + indexAdded;
                inString = inString.substring(indexAdded);  //lo quitamos de inString
                cursor = index + 1;
            return false;
        }
        //System.out.println(tokenFound.getLexema() + lexical.information());
        //sino encontró token -> actualiza el listado de tokens y lo quita del inString
        component.setComponent(tokenFound);
        index = index + indexAdded;
        inString = inString.substring(indexAdded);
        cursor = index + 1;
        return false;
        
   }

    @Override
    public Belongs Start(String set_to_analyze) { /// es la primera funcion que se usa ///, llama a todas
        inString = set_to_analyze;
        boolean finished = false;
        while(!finished){ /// si aun no acaba el analisis
            finished = found(); //encuentra uno por uno
        }
        return Belongs.YES; 
    }

    @Override
    public List<Token> getLexycal() { //obtiene todos los componentes. obtenidos en el analisis
        return component.getLexycalComponents();
    }

    @Override
    public String information() { //obtiene el estado final del analisis.
        return stateGrammar; // devuelve si fue exitoso, o no.
    }
    
}
