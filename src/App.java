import Analyzers.Classes.Dates.Token;
import Analyzers.Classes.Formats.FileAnalyzer;
import Analyzers.Classes.Grammars.GrammarV1_0;
import Analyzers.Interface.Analyzer;
import Analyzers.Interface.Grammar;
import Analyzers.Interface.Information.Belongs;

public class App {
    public static void main(String[] args) throws Exception {
        Analyzer analyzer;
        analyzer = new FileAnalyzer(); // desde un archivo
        // analyzer = new StringAnalyzer();//desde una cadena
        GrammarV1_0 grammar = new GrammarV1_0();
        Grammar grammarToAnalize = grammar;
        Belongs toGrammar;
        // si es un archivo, tendra la direccion del mismo
        toGrammar = analyzer.isFromGrammar("prueba.txt",grammarToAnalize);  
        // si es una cadena, sera la cadena a analizar.
        //toGrammar = analyzer.isFromGrammar("2 + void int float while if else",grammarToAnalize);
        System.out.println(toGrammar + " - " + analyzer.information());
        for (Token index : grammar.getLexycal()) {
            System.out.println(index.getRepresentation() + " : " + index.getTipo() + " : " + index.getId());
        }
    }
}
