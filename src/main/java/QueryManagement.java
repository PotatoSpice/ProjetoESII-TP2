import java.util.ArrayList;

public class QueryManagement {

    private String query;
    private ArrayList<String> trimmedquery = new ArrayList<>();

    //Construtor Vazio, just in case
    public QueryManagement(){

    }

    public QueryManagement(String query){
        this.query=query;
        trimQuery();
    }

    //Possívelmente é desnecessário, mas fica para já!
    public String getQuery() {
        return query;
    }

    //Não sei se assim funcionará direito, mas depois vê-se. Diz-me o que achares! Mas acho que está
    public void trimQuery(){

      /*  for(String w:query.split("\\s"))

            this.trimmedquery.add(w); */

        String[] tempstring=query.trim().split("[\\s+]");

        for(int ix=0; ix<tempstring.length; ix++)
            this.trimmedquery.add(tempstring[ix]);



        }

    public ArrayList<String> getTrimmedquery() {
        return trimmedquery;

    }
}
