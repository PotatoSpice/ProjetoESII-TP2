public class MatrixManagement {

    private double[][] matrizcalculada;
    private double[] grausemelhanca;
    int[][] matrizlocal;
    int columns; //documentos
    int rows; //palavras
    private int[] ocurrencias;


    /**
     * Construtor da classe MatrixManagement
     * @param fileManagement Estrutra fileManagement com os parametros a copiar
     */
    public MatrixManagement(FileManagement fileManagement){
        matrizlocal = fileManagement.getMatrixequivalencia();
        columns = fileManagement.getFilecounter();
        rows = fileManagement.getQuerycounter();
        this.grausemelhanca = new double[this.columns];
        this.ocurrencias = new int[columns];
    }

    /**
     * Define os cálculos da Matriz como requiridos no requisito LF-01.2.C
     */
    public double[][] setMatrizCalculada(){
        occurrences();
        for(int ix=0; ix<columns; ix++){
            for(int i2=0; i2<rows; i2++){
                this.matrizcalculada[ix][i2]=matrizlocal[ix][i2]*1+Math.log10(columns/ocurrencias[i2]);
            }
        }
        return matrizcalculada;
    }

    public double[][] getMatrizCalculada(){
        return this.matrizcalculada;
    }

    /**
     * Este método verifica em quantos documentos existe a palavra em qualquer posição da query.
     * Pesquisa na matriz local. Se alguma dada palvra existe(>0), incrementa na sua posição da matriz de ocurrencias.
     * Como requirido no requisito LF-01.3
     */
    public int[] occurrences() {
        for (int ix = 0; ix < columns; ix++) {
            for (int i2 = 0; i2 < rows; i2++) {
                if(matrizlocal[ix][i2]>0){
                    ocurrencias[i2]++;
                }
            }
        }
        return ocurrencias;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    /**
     * Referente ao requisito Calculos-01
     *
     * ISSUE:Problemas na interpretação da fórmula. Esclarecer problemas. Fórmula errada ou incompleta.
     */
    public double[] grauSemelhanca(){
        for(int ix=0; ix<columns; ix++){
            for (int i2=0; i2<rows;i2++){
                grausemelhanca[ix]=(matrizcalculada[ix][i2]*ocurrencias[i2])/(Math.sqrt(matrizcalculada[ix][i2]*matrizcalculada[ix][i2]))*(Math.sqrt(ocurrencias[i2]^2));
            }
        }
        return grausemelhanca;
    }

    public double[] getGrausemelhanca(){
        return grausemelhanca;
    }
}
