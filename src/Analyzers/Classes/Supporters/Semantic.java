package Analyzers.Classes.Supporters;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Analyzers.Classes.Dates.Identifier;
import Analyzers.Classes.Dates.Token;
import Analyzers.Interface.Information;

//// *** FUTURO USO ***//
public class Semantic implements Information{
    private List<Identifier> symbologyTable = new LinkedList<Identifier>(); //guarda los identificadores
    private List<Token> reservedWords; //guarda las palabras reservadas.
    private String analyzed; // se guarda la palabra analizada

    public Semantic(ArrayList<Token> reservedWords){
        this.reservedWords = reservedWords; //guarda la lista de palabaras reservadas
    }

    public boolean addIdentifier(Identifier word_to_add){ //agrega nuevo identificador
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
    public boolean isExisting(Identifier word_to_search){ // revisa si existe
        final boolean isThere = symbologyTable.contains(word_to_search);
        if(isThere){ //si esta, devuelve un true.
            this.analyzed = word_to_search.getName(); //guarda la palabra encontrada.
            return true;
        }
        this.analyzed = "It's not a existing identifier"; //guarda mensaje de error
        return false; //devuelve falso
    }
    @Override
    public String information() {
        return this.analyzed; //devuelve le analizado.
    }
}
