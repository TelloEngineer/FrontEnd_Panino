package Analyzers.Interface;

//define el analizador
public interface Analyzer extends Information{
    //obtiene la gramatica con la que va a analizar la entrada.
    //y la entrada misma.
    public Belongs isFromGrammar(String to_analyze, Grammar grammar_to_analyze);

}
