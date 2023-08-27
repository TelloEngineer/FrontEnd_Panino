package Analyzers.Classes.Dates;

public class Token {
    public Token() {
        this.representation = "";
        this.lexema = "";
        this.id = ' ';
    }
    public Token(String representation, String lexema, char id) {
        this.representation = representation;
        this.lexema = lexema;
        this.id = id;
    }
    String representation;
    String lexema;
    char id;
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lexema == null) ? 0 : lexema.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Token other = (Token) obj;
        if (lexema == null) {
            if (other.lexema != null)
                return false;
        } else if (!lexema.equals(other.lexema))
            return false;
        return true;
    }
    public String getRepresentation() {
        return representation;
    }
    public void setRepresentation(String representation) {
        this.representation = representation;
    }
    public String getLexema() {
        return lexema;
    }
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
    public char getId() {
        return id;
    }
    public void setId(char id) {
        this.id = id;
    }
    
    
}
