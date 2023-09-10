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
        Token tokenFound = lexical.firstToken(wordsToAnalize, inString);
        //System.out.println(tokenFound.getLexema() + lexical.information());
        if(!(1 < inString.length())){
           stateGrammar = "fin de la cadena, cursor: " + cursor;
           return true; 
        }
        if(tokenFound.equals(new Token())){
            stateGrammar = "se encontro un token no valido, cursor: " + cursor;
            return true;
        }
        switch(tokenFound.getId()){
            case 'i': case 'e': case 'f':
                tokenFound.setRepresentation(lexical.information());
            break;
        }
        component.setComponent(tokenFound);
        int indexAdded = lexical.getLastIndex();
        index = index + indexAdded;
        inString = inString.substring(indexAdded);
        cursor = index + 1;
        return false;
        
   }

    @Override
    public Belongs Start(String set_to_analyze) {
        inString = set_to_analyze;
        boolean finished = false;
        while(!finished){
            finished = found();
        }
        return Belongs.YES;
    }

    @Override
    public List<Token> getLexycal() {
        return component.getLexycalComponents();
    }

    @Override
    public String information() {
        return stateGrammar;
    }
    
}
