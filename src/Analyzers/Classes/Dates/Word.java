package Analyzers.Classes.Dates;

public class Word {
    String token;
    String lexema;
    char id;
    public Word(){
        this.token = "";
        this.lexema = "";
        this.id = ' '; 
    }
    public Word(String token, String lexema, char id) {
        this.token = token;
        this.lexema = lexema;
        this.id = id;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getlexema() {
        return lexema;
    }
    public void setlexema(String lexema) {
        this.lexema = lexema;
    }
    public char getId() {
        return id;
    }
    public void setId(char id) {
        this.id = id;
    }
    
}
