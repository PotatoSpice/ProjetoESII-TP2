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
        QueryManagement q = new QueryManagement("Lorem sapien");
        FileManagement f = new FileManagement(q);
        f.fileReader();
        f.queryFile();


        for (int[] x : f.getMatrixequivalencia()) {
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println("|");
        }

        System.out.println("--------------------------");
        MatrixManagement matriz = new MatrixManagement(f);
        matriz.occurrences();
        matriz.setMatrizCalculada();
        matriz.grauSemelhanca();



        for (double[] x : matriz.getMatrizCalculada()) {
            for (double y : x) {
                System.out.print(y + " ");
            }
            System.out.println("|");
        }
        System.out.println("--------------------------");

        for(int i = 0;i<matriz.getGrausemelhanca().length;i++) {
            System.out.println(matriz.getGrausemelhanca()[i]);
        }


        ListingManagement list = new ListingManagement(matriz,f);
        System.out.println(list.listaCompleta());

/*
      //  System.out.println(f.getQuerycounter());
       // System.out.println(f.getFilecounter());

        MatrixManagement matriz = new MatrixManagement(f);

        ListingManagement list = new ListingManagement(matriz, f);

        System.out.println(matriz.getRows());
    */
    }
}