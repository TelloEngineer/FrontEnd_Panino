import javax.swing.SwingUtilities;

import UI.mainWindow.Menu;

public class App {
    public static void main(String[] args) throws Exception {
       /*Analyzer analyzer;
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
    	*/
        /* 
        Finder s = new Finder();
        //s.setInString("  /* hola   ");
        //s.firstOcurrency("[^\\s]");
        System.out.println(s.retrieveBetween("[/][*]", "[*][/]"));
        System.out.println(s.getSubString() + " - " + s.getStart() + " - " + s.getEnd());
        */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Menu();
            }
        });
        
    }
}
