import java.util.Scanner;

public class InputManager {

    private String path;
    private String query;
    Scanner sc1;


    public String getPath() {
        return path;
    }

    public void setPath() {
        System.out.print("Insira o filepath: ");
        sc1 = new Scanner(System.in);
        String path=sc1.next();
        this.path = path;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery() {
        System.out.print("Insira a query: ");
        sc1 = new Scanner(System.in);
        String query=sc1.next();
        this.query = query;
    }

    //Fica aqui até ver!
    /*public void setQueryEnter(){
        while(!in.hasNextLine()) {
            trimmedQuery.add(in.next());
        }
    } */
}
