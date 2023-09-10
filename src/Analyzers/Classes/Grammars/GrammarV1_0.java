package Analyzers.Classes.Grammars;

import java.util.List;
import java.util.concurrent.CancellationException;

import Analyzers.Classes.Dates.Token;
import Analyzers.Classes.Supporters.LexycalComponents;
import Analyzers.Classes.Supporters.SyntaticManager;
import Analyzers.Interface.GetLexycal;
import Analyzers.Interface.Grammar;
import Analyzers.Interface.Information.Belongs;

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
                new Token("parentesis abierto","(", "[(]", '('),
                new Token("parentesis cerrado",")", "[)]", ')'),
                new Token("operador aritmetico","*", "[*]", '*'),
                new Token("operador aritmetico","/", "[/]", '/'),
                new Token("operador aritmetico","+", "[+]", '+'),
                new Token("operador aritmetico","-", "[-]", '-'),
                new Token("numero entero","entero", "[0-9]+", 'e'),
                new Token("numero decimal","float", "[0-9]+.[0-9]+", 'f'),
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
        if(tokenFound.getId() == 'i' ){
            tokenFound.setRepresentation(lexical.information());
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
        component.seeComponents();
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
