import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

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

        FileManagement f = new FileManagement("fadsfasdfas");
        System.out.println(f.getCurrentDirectory());

    }
}