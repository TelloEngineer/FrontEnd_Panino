package Analyzers.Classes.Supporters;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Analyzers.Classes.Dates.Identifier;
import Analyzers.Classes.Dates.Token;
import Analyzers.Interface.Information;


public class Semantic implements Information{
    private List<Identifier> symbologyTable = new LinkedList<Identifier>();
    private List<Token> reservedWords;
    private String analyzed;

    public Semantic(ArrayList<Token> reservedWords){
        this.reservedWords = reservedWords;
    }

    public boolean addIdentifier(Identifier word_to_add){
        final boolean isThere = symbologyTable.contains(word_to_add);
        if(isThere){
            this.analyzed = "Identifier repeated";
            return false;
        }
        final boolean isReserved = reservedWords.contains(new Token("id_se","id",word_to_add.getName(),'c'));
        if(isReserved){
            this.analyzed = "Identifier is a reserved word";
            return false;
        }
        symbologyTable.add(word_to_add);
        this.analyzed = word_to_add.getName();
        return true;
    }
    public boolean isExisting(Identifier word_to_search){
        final boolean isThere = symbologyTable.contains(word_to_search);
        if(isThere){
            this.analyzed = word_to_search.getName();
            return true;
        }
        this.analyzed = "It's not a existing identifier";
        return false;
    }
    @Override
    public String information() {
        return this.analyzed;
    }
}
