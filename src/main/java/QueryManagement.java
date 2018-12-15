import java.util.ArrayList;

public class QueryManagement {

    private String query;
    private ArrayList<String> trimmedquery = new ArrayList<>();

    //Construtor Vazio, just in case
    public QueryManagement(){

    }

    public QueryManagement(String query){
        this.query=query;
    }

    //Possívelmente é desnecessário, mas fica para já!
    public String getQuery() {
        return query;
    }

    //Não sei se assim funcionará direito, mas depois vê-se. Diz-me o que achares!
    public void trimQuery(){


       while(query.trim().split("\\s+")!=null)
        this.trimmedquery.add(String.valueOf(query.trim().split("\\s+")));

    }

    public ArrayList<String> getTrimmedquery() {
        return trimmedquery;
    }
}
