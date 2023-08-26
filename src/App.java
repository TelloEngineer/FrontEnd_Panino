import Analyzers.Classes.Dates.Word;
import Analyzers.Classes.Formats.StringAnalyzer;
import Analyzers.Classes.Grammars.FirstGrammar;
import Analyzers.Classes.Grammars.FirstGrammar2;
import Analyzers.Classes.Supporters.SyntaticManager;
import Analyzers.Interface.Analyzer;
import Analyzers.Interface.Information.Belongs;

public class App {
    public static void main(String[] args) throws Exception {
        Analyzer analyzer = new StringAnalyzer();
        SyntaticManager s = new SyntaticManager();
        Belongs toGrammar = analyzer.isFromGrammar("   2 - 2 - 23",new FirstGrammar2());
        System.out.println(toGrammar + " - " + analyzer.information());
        Word words[] = {new Word("3","3",'3'),new Word("2","2",'2')};
        System.out.println(s.preanalisis(words,"  2+4", 0) + " " + s.getLastIndex());


    }
}
