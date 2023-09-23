package Analyzers.Classes.Formats;

import Analyzers.Interface.Analyzer;
import Analyzers.Interface.Grammar;

//  guarda en String, la cadena a analizar
public class StringAnalyzer implements Analyzer{
    private String infoAnalyze; //es el estado del analisis de la cadena

    public StringAnalyzer() { //inicializa la variable
        this.infoAnalyze = "haga una analisis antes";
    }

    @Override //obtiene la cadena a analizar, y la gramatica que analizara
    public Belongs isFromGrammar(String string_to_analyze, Grammar grammar_to_analyze) {
        Boolean stringExists = string_to_analyze != null;
        if (!stringExists) {
            infoAnalyze = "No hay cadena para analizar";
            return Belongs.NO;
        }
        Belongs belongsHere = grammar_to_analyze.Start(string_to_analyze);
        infoAnalyze = grammar_to_analyze.information();
        return belongsHere;
    }

    //obtiene el resultado del analisis.
    @Override
    public String information() {
        return this.infoAnalyze;
    }
    
}
