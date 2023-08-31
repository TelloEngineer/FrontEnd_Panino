import Analyzers.Classes.Dates.Token;
import Analyzers.Classes.Formats.StringAnalyzer;
import Analyzers.Classes.Grammars.FirstGrammar2;
import Analyzers.Classes.Supporters.LexycalComponents;
import Analyzers.Classes.Supporters.SyntaticManager;
import Analyzers.Interface.Analyzer;
import Analyzers.Interface.Information.Belongs;

public class App {
    public static void main(String[] args) throws Exception {
        Analyzer analyzer = new StringAnalyzer();
        SyntaticManager s = new SyntaticManager();
        Belongs toGrammar = analyzer.isFromGrammar("   2 + ( 8 - as -9)",new FirstGrammar2());
        System.out.println(toGrammar + " - " + analyzer.information());
        //Token words[] = {new Token("3","3",'3'),new Token("2","2",'2')};
        //System.out.println(s.preanalisis(words,"  2+4", 0) + " " + s.getLastIndex());
    }
}
