import java.util.ArrayList;

public class QueryManagement {

    private String query;
    private ArrayList<String> trimmedquery = new ArrayList<>();

    /**
     * Construtor genérioc da classe QueryManagement
     */
    public QueryManagement() {

    }

   /**
     * Construtor genérico que aceita uma query
     *
     * @param query
     */
    public QueryManagement(String query) {
        this.query = query;
        trimQuery();

    }

    //Possívelmente é desnecessário, mas fica para já!

    /**
     * Getter genérico da variable query
     *
     * @return String da query
     */
    public String getQuery() {
        return query;
    }

    //Não sei se assim funcionará direito, mas depois vê-se. Diz-me o que achares!

    /**
     * Recorta a query existente em listas diferenças de forma a ser mais facilmente comparaveis.
     * Divide as palavras por cada espaço
     */
    public void trimQuery() {
        
        for(String w:query.split("\\s",0))
            this.trimmedquery.add(w);

    }

    /**
     * Getter genérico da variavel trimmedQuery
     *
     * @return Uma lista com uma palavra em cada lista.
     */
    public ArrayList<String> getTrimmedquery() {
        return trimmedquery;
    }
}
