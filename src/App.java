import Analyzers.Classes.Formats.StringAnalyzer;
import Analyzers.Classes.Grammars.FirstGrammar;
import Analyzers.Classes.Supporters.SyntaticManager;
import Analyzers.Interface.Analyzer;
import Analyzers.Interface.Information.Belongs;

public class App {
    public static void main(String[] args) throws Exception {
        Analyzer analyzer = new StringAnalyzer();
        SyntaticManager s = new SyntaticManager();
        Belongs toGrammar = analyzer.isFromGrammar("2+",new FirstGrammar());
        System.out.println(toGrammar + " - " + analyzer.information());
        System.out.println(s.analizer("[+]","  3+4", 3) + " " + s.getLastIndex());


    }
}
