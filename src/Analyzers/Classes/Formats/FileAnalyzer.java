package Analyzers.Classes.Formats;

import Analyzers.Classes.Supporters.FileManager;
import Analyzers.Interface.Analyzer;
import Analyzers.Interface.Grammar;

public class FileAnalyzer implements Analyzer{
    private FileManager fileManager;
    private String infoAnalyze;

    public FileAnalyzer() {
        this.fileManager = new FileManager();
        this.infoAnalyze = "haga una analisis antes";
    }

    public String information(){
        return this.infoAnalyze;
    }  

    @Override
    public Belongs isFromGrammar(String file_to_analyze, Grammar grammar_to_analyze) {
        String stringAnalyze;
        stringAnalyze = fileManager.fileToString(file_to_analyze);
        Boolean fileExists = stringAnalyze != null;
        if(!fileExists){
            infoAnalyze = "No se encontro el archivo";
            return Belongs.NO;
        }
        Belongs belongsHere = grammar_to_analyze.Start(stringAnalyze);
        infoAnalyze = grammar_to_analyze.information();
        return belongsHere;
    }
    
}
