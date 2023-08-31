package Analyzers.Classes.Grammars;

import java.util.List;
import java.util.concurrent.CancellationException;

import Analyzers.Classes.Dates.Token;
import Analyzers.Classes.Supporters.LexycalComponents;
import Analyzers.Classes.Supporters.SyntaticManager;
import Analyzers.Interface.GetLexycal;
import Analyzers.Interface.Grammar;

public class FirstGrammar2 implements Grammar, GetLexycal{
    
    public FirstGrammar2(){}
    // private
   //---tribute---
   private String inString;
   private int index;
   private int cursor;
   private char preanalis;
   private String errorMessage;
   private LexycalComponents component = new LexycalComponents();
   private SyntaticManager lexical = new SyntaticManager();
   
   //---methods---
   private void coincidir(String regular_expression){
        //System.out.println("coincidi");
        final Belongs itIsMatching = lexical.analizer_cursor(regular_expression, inString,cursor);
        //System.out.println(inString + " : "+ regular_expression);
        if (itIsMatching == Belongs.NO){
            errorMessage= lexical.information();  
            throw new CancellationException(); 
        }
        if(1 < inString.length()){
            int indexAdded = lexical.getLastIndex();
            index = index + indexAdded;
            inString = inString.substring(indexAdded);
            cursor = index + 1;
        }
   }
    private void subAdd(){ // usando iteraciones.
        while(true){
            //System.out.println("subAdd");
            Token[] wordsToAnalize =  {
                new Token("+", "[+]", '+'),
                new Token("-", "[-]", '-')
            };
            preanalis = lexical.preanalisis(wordsToAnalize, inString);
            switch (preanalis){ //pre-analysis
                case '+':
                    coincidir("[+]");
                    component.setComponent(wordsToAnalize[0]);
                    term();
                    continue;
                case '-':
                    coincidir("[-]");
                    component.setComponent(wordsToAnalize[1]);
                    term();
                    continue;
                default:
                    return;
            }
        }
    } 
    private void term(){
        //System.out.println("term");
        factor();
        divMul();
    }
    private void divMul(){ // usando iteraciones.
        while(true){
            //System.out.println("divMul");
            Token[] wordsToAnalize =  {
                new Token("*", "[*]", '*'),
                new Token("/", "[/]", '/')
            };
            preanalis = lexical.preanalisis(wordsToAnalize, inString);
            switch (preanalis){ //pre-analysis
                case '*':
                    coincidir("[*]");
                    component.setComponent(wordsToAnalize[0]);
                    factor();
                    continue;
                case '/':
                    coincidir("[/]");
                    component.setComponent(wordsToAnalize[1]);
                    factor();
                    continue;
                default:
                    return;
            }
        }
    } 
    private void factor(){
        while(true){
            //System.out.println("factor");
            Token[] wordsToAnalize =  {
                new Token("(", "[(]", '(')
            };
            preanalis = lexical.preanalisis(wordsToAnalize, inString);
            switch(preanalis){
                case '(':   
                    coincidir("[(]");
                    component.setComponent(wordsToAnalize[0]);
                    expression();
                    coincidir("[)]");
                    component.setComponent(new Token(")", "[)]", ')'));
                    return;
                default:
                    digito();
                    return;
            }
        }
    }
    private void digito(){
        Token[] wordsToAnalize =  {
                new Token("entero", "[0-9]+", 'e'),
                new Token("float", "[0-9]+.[0-9]+", 'f')
            };
            preanalis = lexical.preanalisis(wordsToAnalize, inString);
            switch(preanalis){
                case 'e':
                    coincidir("[0-9]+");
                    component.setComponent(new Token(lexical.information(), "[0-9]+", 'e'));
                break;
                case 'f':
                    coincidir("[0-9]+.[0-9]+");
                    component.setComponent(new Token(lexical.information(), "[0-9]+.[0-9]+", 'f'));
                break;
                default:
                    coincidir("[a-zA-Z][a-zA-Z0-9]*");
                    component.setComponent(new Token(lexical.information(), "[a-zA-Z][a-zA-Z0-9]*", 'i'));
            }
    }
    private void expression(){
        //System.out.println("expression");
        term(); 
        subAdd();  
    }
// public
    public String information(){
        return errorMessage;
    }
    @Override
    public Belongs Start(String set_to_analyze) {
        this.inString = set_to_analyze;
        this.index = 0;
        try {
            expression();
            errorMessage = "sin errores";
            component.seeComponents();
            return Belongs.YES;
        }catch(CancellationException expected){
            return Belongs.NO;
        }
    }
    @Override
    public List<Token> getLexycal() {
        return component.getLexycalComponents();     
    }
    
}
