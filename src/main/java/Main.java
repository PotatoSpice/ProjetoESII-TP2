import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
        QueryManagement q = new QueryManagement("query de teste");
        FileManagement f = new FileManagement(q);
        System.out.println(f.fileReader());
        f.queryFile();


       /* for (int[] x : f.getMatrixequivalencia())
        {
            for (int y : x)
            {
                System.out.print(y + "| ");
            }
            System.out.println();
        }

      //  System.out.println(f.getQuerycounter());
       // System.out.println(f.getFilecounter());

        MatrixManagement matriz = new MatrixManagement(f);

        ListingManagement list = new ListingManagement(matriz, f);

        System.out.println(matriz.getRows());
    */
    }
}