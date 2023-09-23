package Analyzers.Classes.Dates;


/// Son los campos del token//
public class Token {
    
    String tipo; /// dice el nombre del mismo
    String representation; // como se ve
    String lexema; // la expresion regular que lo representa
    int id; // el id del mismo
    /// constructores
    public Token() {
        this.tipo= "";
        this.representation = "";
        this.lexema = "";
        this.id = ' ';
    }
    public Token(String tipo, String representation, String lexema, int id) {
        this.tipo = tipo;
        this.representation = representation;
        this.lexema = lexema;
        this.id = id;
    }

    /// para comprararlos
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

    ///Sus getters y setters
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
