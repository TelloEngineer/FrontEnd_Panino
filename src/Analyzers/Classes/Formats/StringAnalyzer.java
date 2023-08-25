package Analyzers.Classes.Formats;

import Analyzers.Interface.Analyzer;
import Analyzers.Interface.Grammar;

public class StringAnalyzer implements Analyzer{
    private String infoAnalyze;

    public StringAnalyzer() {
        this.infoAnalyze = "haga una analisis antes";
    }

    @Override
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

    @Override
    public String information() {
        return this.infoAnalyze;
    }
    
}
