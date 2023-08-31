package Analyzers.Classes.Supporters;

import java.util.LinkedList;
import java.util.List;

import Analyzers.Classes.Dates.Token;

public class LexycalComponents {
    private List<Token> components = new LinkedList<Token>();

    public void setComponent(Token component){
        components.add(component);
    }
    public Token getComponent(int index){
        if(components.size() > index){
            return components.get(index);
        }
        return new Token();
    }
    public List<Token> getLexycalComponents(){
        return new LinkedList<Token>(components);
    }
    public void seeComponents(){
        for(Token index : components){
            System.out.println(index.getId() +" - " + index.getLexema() +" - "+ index.getRepresentation());
        }
    }
}
