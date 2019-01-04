import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        //Vou colocar aqui só para ficar já

        /*InputManager inputManager = new InputManager();

        do{

            inputManager.setPath();

        }while(inputManager.getPath().equals(null));

        do{

            inputManager.setQuery();

        }while(inputManager.getQuery().equals(null));

        QueryManagement queryManagement = new QueryManagement(inputManager.getQuery());
        FileManagement fileManagement = new FileManagement(inputManager.getPath());
    */

        QueryManagement q = new QueryManagement("test de trimm da query");
        System.out.println(q.getQuery());

    }
}