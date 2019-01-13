import java.util.ArrayList;

public class ListingManagement<T> {

    MatrixManagement matrixManagement;


    private double[] arrayGraus;
    private String[] arrayFicheiros;
    private ArrayList<String> classificacao;
    int maxcheck;
    int max;


    /**
     * O construtor vai buscar a instância desejada da matrixManagement e vai buscar a lista dos graus de semelhança,
     * comparando com o array com os ficheiros.
     *
     * @param matrixManagement Instância da MatrixManagement
     * @param fileManagement   Instância do FileManagement
     */
    public ListingManagement(MatrixManagement matrixManagement, FileManagement fileManagement) {
        arrayGraus = matrixManagement.getGrausemelhanca();
        arrayFicheiros = fileManagement.getFileString();
        max = arrayFicheiros.length;
        maxcheck = arrayGraus.length;
        classificacao = new ArrayList<String>();
    }

    /**
     * Listagens-01
     *
     * @return a lista com os dados. Encontram-se pela ordem de leitura.
     */
    public ArrayList<String> listaCompleta() {

        classificacao = new ArrayList<String>();

        for (int ix = 0; ix < max; ix++)
            classificacao.add("Ficheiro: " + arrayFicheiros[ix] + "; Grau de Semelhança: " + arrayGraus[ix] + "\n");


        return classificacao;

    }

    /**
     * Listagens-01.2
     *
     * @param maximo referente ao valor máximo de equivalência estipulado pelo utilizador.
     * @return a lista com os dados. Encontram-se pela ordem de leitura.
     */
    public ArrayList<String> listaMaximo(int maximo) {

        classificacao = new ArrayList<String>();

        if (maximo >= 0) {
            for (int ix = 0; ix < max; ix++) {
                if (arrayGraus[ix] < maximo)
                    classificacao.add("Ficheiro: " + arrayFicheiros[ix] + "; Grau de Semelhança: " + arrayGraus[ix] + "\n");
            }
        }
        return classificacao;
    }

    /**
     * Listagens-01.1
     *
     * @param minimo referente ao valor mínimo de equivalência estipulado pelo utilizador.
     * @return a lista com os dados. Encontram-se pela ordem de leitura.
     */
    public ArrayList<String> listaMinimo(int minimo) {

        classificacao = new ArrayList<String>();
        if (minimo > 0) {
            for (int ix = 0; ix < max; ix++)
                if (arrayGraus[ix] > minimo)
                    classificacao.add("Ficheiro: " + arrayFicheiros[ix] + "; Grau de Semelhança: " + arrayGraus[ix] + "\n");
        }
        return classificacao;
    }

    /**
     * Listagens-01.3
     *
     * @param maxtotal o total de documentos que o utilizador deseja ver
     * @return a lista, organizada pela ordem de leitura.
     */
    public ArrayList<String> listaTotalMax(int maxtotal) {

        classificacao = new ArrayList<String>();
        if (maxtotal >= 0) {
            for (int ix = 0; ix < maxtotal; ix++)
                classificacao.add("Ficheiro: " + arrayFicheiros[ix] + "; Grau de Semelhança: " + arrayGraus[ix] + "\n");
        }
        return classificacao;
    }

    public void setArrayGraus(double[] arrayGraus) {
        this.arrayGraus = arrayGraus;
    }

    public void setArrayFicheiros(String[] arrayFicheiros) {
        this.arrayFicheiros = arrayFicheiros;
    }
}
