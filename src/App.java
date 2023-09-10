import Analyzers.Classes.Dates.Token;
import Analyzers.Classes.Formats.FileAnalyzer;
import Analyzers.Classes.Formats.StringAnalyzer;
import Analyzers.Classes.Grammars.FirstGrammar2;
import Analyzers.Classes.Grammars.GrammarV1_0;
import Analyzers.Classes.Supporters.LexycalComponents;
import Analyzers.Classes.Supporters.SyntaticManager;
import Analyzers.Interface.Analyzer;
import Analyzers.Interface.Information.Belongs;

public class App {
    public static void main(String[] args) throws Exception {
        Analyzer analyzer = new FileAnalyzer();
        SyntaticManager s = new SyntaticManager();
        Belongs toGrammar = analyzer.isFromGrammar("file.txt",new GrammarV1_0());
        System.out.println(toGrammar + " - " + analyzer.information());
        //Token words[] = {new Token("3","3",'3'),new Token("2","2",'2')};
        //System.out.println(s.preanalisis(words,"  2+4", 0) + " " + s.getLastIndex());
    }
}
