public class MatrixManagement {

    //Construtor da classe
    public MatrixManagement(FileManagement fileManagement) {
        matrizlocal(fileManagement);
        setMatrizCalculada();
        grauSemelhanca();
    }

    private double[][] matrizcalculada;
    private double[] grausemelhanca;

    int[][] matrizlocal;
    int columns; //documentos
    int rows; //palavras
    int praticalrows;

    private int[] ocurrencias;

    /**
     * Define os cálculos da Matriz como requiridos no requisito LF-01.2.C
     */
    public double[][] setMatrizCalculada() {
        occurrences();
        for (int ix = 0; ix < columns; ix++) {
            for (int i2 = 0; i2 < rows; i2++) {
                this.matrizcalculada[ix][i2] = matrizlocal[ix][i2] *( 1 + Math.log10(columns/ocurrencias[i2]));
            }
        }
        return matrizcalculada;
    }

    public double[][] getMatrizCalculada() {
        return this.matrizcalculada;
    }

    /**
     * Este método verifica em quantos documentos existe a palavra em qualquer posição da query.
     * Pesquisa na matriz local. Se alguma dada palvra existe(>0), incrementa na sua posição da matriz de ocurrencias.
     * Como requirido no requisito LF-01.3
     */
    public void occurrences() {
        ocurrencias = new int[praticalrows];
        for (int ix = 0; ix < columns; ix++) {
            for (int i2 = 0; i2 < rows; i2++) {
                if (matrizlocal[ix][i2] > 0) {
                    ocurrencias[i2]++;
                }
            }
        }
    }

    /**
     * Esta função vai buscar os dados da matriz dos documentos e passa para esta classe. Chama-se no construtor.
     * Não existe em requisito, mas é fundamental para a correta utilização desta classe.
     *
     * @param fileManagement instância do FileManagement criado no Main
     */
    public void matrizlocal(FileManagement fileManagement) {
        matrizlocal = fileManagement.getMatrixequivalencia();
        columns = fileManagement.getFilecounter();
        rows = fileManagement.getQuerycounter()-1;
        praticalrows= fileManagement.getQuerycounter();
        matrizcalculada = new double[columns][rows];
    }

    public void getMatrizPrint(){

        System.out.println("Colunas; Rows;"+columns+rows);
        for(int ix=0; ix<columns; ix++)
            for(int i2=0; i2<rows; i2++)
                System.out.println(matrizlocal[ix][i2]);
    }

    /**
     * Referente ao requisito Calculos-01
     * <p>
     * ISSUE:Problemas na interpretação da fórmula. Esclarecer problemas. Fórmula errada ou incompleta.
     */
    public double[] grauSemelhanca() {
        grausemelhanca = new double[columns];
        double sumatorio1 = 0.0; //sumatorio para a primeira parte da fórmula
        double sumatorio2 = 0.0; //sumatório para a parte de baixo da fórmula
        double sumatorio3 = 0.0; //sumatório para a parte de baixo da fórmula

        for (int ix = 0; ix < columns; ix++) {
            for (int i2 = 0; i2 < rows; i2++) {
                sumatorio1 = sumatorio1 + (matrizcalculada[ix][i2] * ocurrencias[i2]);
            }
        }

        for (int ix = 0; ix < columns; ix++) {
            for (int i2 = 0; i2 < rows; i2++) {
                sumatorio2 = sumatorio2 + (matrizcalculada[ix][i2] * matrizcalculada[ix][i2]);
            }
        }

        for (int i2 = 0; i2 < rows; i2++) {
            sumatorio3 = sumatorio3 + (ocurrencias[i2] ^ 2);
        }

        for (int ix = 0; ix < columns; ix++) {
            for (int i2 = 0; i2 < rows; i2++) {
                grausemelhanca[ix] = (sumatorio1) / (Math.sqrt(sumatorio2)) * (Math.sqrt(sumatorio3));
            }
        }
        return grausemelhanca;
    }

    public double[] getGrausemelhanca() {
        return grausemelhanca;
    }
}
