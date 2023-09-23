package Analyzers.Classes.Supporters;

import java.util.LinkedList;
import java.util.List;

import Analyzers.Classes.Dates.Token;

public class LexycalComponents { //guarda los componentenes lexicos
    private List<Token> components = new LinkedList<Token>(); //aqui los guarda

    public void setComponent(Token component){ // guarda el token individual
        components.add(component);
    }
    public Token getComponent(int index){ // obtiene un token
        if(components.size() > index){ // revisa si hay tokens
            return components.get(index); // lo devuelve
        }
        return new Token(); // devuelve uno vacio
    }
    public List<Token> getLexycalComponents(){  // devuelve todos los componentes
        return new LinkedList<Token>(components); // crea una nueva lista, con los datos
    }
    public void seeComponents(){ // imprime los componente
        for(Token index : components){ // imprimira, hasta ya no alla componentes.
            System.out.println(index.getRepresentation()+ " : " +  index.getTipo() +" : "+ index.getId());
        }
    }
}
