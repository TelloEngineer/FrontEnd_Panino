package Analyzers.Interface;

//define el que devuelva los estados.
public interface Information {
    // pertenece o no pertenece
    public enum Belongs {
        YES, NO
    }
    public String information();
}
