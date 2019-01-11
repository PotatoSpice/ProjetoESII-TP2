import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        //Vou colocar aqui só para ficar já

        InputManager inputManager = new InputManager();

        do{

            inputManager.setPath();

        }while(inputManager.getPath().equals(null));

        do{

            inputManager.setQuery();

        }while(inputManager.getQuery().equals(null));

        QueryManagement queryManagement = new QueryManagement(inputManager.getQuery());
        FileManagement fileManagement = new FileManagement("C:\\Users\\Rodrigo\\OneDrive\\ESTG\\TXT FIles");

        System.out.println(fileManagement.fileReader());
        fileManagement.queryFile();
        System.out.println(fileManagement.getFilecounter()+" "+ fileManagement.getQuerycounter());
        MatrixManagement matrixManagement = new MatrixManagement(fileManagement);
        ListingManagement listingManagement = new ListingManagement(matrixManagement, fileManagement);

        ArrayList<String> listagem = listingManagement.listaCompleta();

        for(String s: listagem){

            System.out.println(s);

        }


    }

}
