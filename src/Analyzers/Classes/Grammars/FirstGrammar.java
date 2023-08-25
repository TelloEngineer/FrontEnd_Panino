package Analyzers.Classes.Grammars;

import java.util.concurrent.CancellationException;

import Analyzers.Interface.Grammar;

public class FirstGrammar implements Grammar {
    
    public FirstGrammar(){}
    // private
   //---tribute---
   private String inString;
   private int index;
   private int cursor;
   private char preanalis;
   private String errorMessage;
   //---methods---
   private void coincidir(char character){
        //System.out.println("coincidi");
        final boolean itIsMatching = (character == preanalis);
        if (!itIsMatching){
            errorMessage= "deberia tener un '" + character + "' en el caracter: " + cursor;  
            throw new CancellationException(); 
        }
        System.out.println(character);
        if(index < inString.length()-1){
            index++;
            cursor = index + 2;
        }
   }
    private void subAdd(){ // usando iteraciones.
        while(true){
            //System.out.println("subAdd");
            preanalis = inString.charAt(index);
            switch (preanalis){ //pre-analysis
                case '+':
                    coincidir('+');
                    term();
                    continue;
                case '-':
                    coincidir('-');
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
            System.out.println("divMul");
            preanalis = inString.charAt(index);
            switch (preanalis){ //pre-analysis
                case '*':
                    coincidir('*');
                    factor();
                    continue;
                case '/':
                    coincidir('/');
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
            preanalis = inString.charAt(index);
            switch(preanalis){
                case '(':   
                    coincidir('(');
                    expression();
                    coincidir(')');
                    return;
                default:
                    digito();
                    return;
            }
        }
    }
    private void digito(){
        preanalis = inString.charAt(index);
        final boolean digit = Character.isDigit(preanalis);
        //System.out.println("digito");
        if(digit){  
            coincidir(preanalis);
            return; 
        }
        //here, we will detect this string isn't from the language.
        errorMessage = "deberia tener un digito, en el caracter: " + cursor;
        throw new CancellationException();
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
            return Belongs.YES;
        }catch(CancellationException expected){
            return Belongs.NO;
        }
    }
    
}
