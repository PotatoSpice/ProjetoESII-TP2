import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        System.out.print("Intoduza query a pesquisar: ");
        String query = in.nextLine();
        QueryManagement q = new QueryManagement(query);

        System.out.println("Escolha a listagem: ");
        System.out.println("1- Listagem completa");
        System.out.println("2- Listagem com valor maximo");
        System.out.println("3- Listagem com valor minimo");
        System.out.println("4- Listagem com maximo de resultados");

        Scanner typelist = new Scanner(System.in);
        int[] escolha = new int[5];
        escolha[0] = typelist.nextInt();
        FileManagement f = new FileManagement(q);
        f.fileReader();
        f.queryFile();

        MatrixManagement matriz = new MatrixManagement(f);
        matriz.occurrences();
        matriz.setMatrizCalculada();
        matriz.grauSemelhanca();

        ListingManagement list = new ListingManagement(matriz, f);
        switch (escolha[0]) {
            case 1:
                System.out.println(list.listaCompleta());
                break;
            case 2:
                System.out.print("valor máximo: ");
                escolha[1] = typelist.nextInt();
                System.out.println(list.listaMaximo(escolha[1]));
                break;
            case 3:
                System.out.print("valor minimo: ");
                escolha[2] = typelist.nextInt();
                System.out.println(list.listaMinimo(escolha[2]));
            case 4:
                System.out.print("Escolha o numero de resultados a apresentar: ");
                escolha[3] = typelist.nextInt();
                System.out.println(list.listaTotalMax(escolha[3]));
                break;
            default:
                System.out.println("Opção nao valida!");
        }
        in.close();
        typelist.close();

    }
}