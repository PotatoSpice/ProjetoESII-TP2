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
        this.matrizlocal = fileManagement.getMatrixequivalencia();
        columns = fileManagement.getFilecounter();
        rows = fileManagement.getQuerycounter();
        this.grausemelhanca = new double[this.columns];
        this.ocurrencias = new int[rows];
        this.matrizcalculada = new double[columns][rows];
        convertMatrixToDouble(fileManagement);
    }

    /**
     * converte a matrixEquivalencia(int) para a matrixCalculada(double)
     * @param file
     */
    private void convertMatrixToDouble(FileManagement file){
        int[][] tempmatriz = file.getMatrixequivalencia();
        for(int i = 0;i<this.columns;i++){
            for(int j = 0;j<this.rows;j++) {
                this.matrizcalculada[i][j] = tempmatriz[i][j];
            }
        }
    }

    /**
     * Define os cálculos da Matriz como requiridos no requisito LF-01.2.C
     * Pela minha lógica, se vai dizwer por 0, é porque não existe, logo mantem-se inalterado.
     */
    public double[][] setMatrizCalculada(){
        for(int ix=0; ix<columns; ix++){
            for(int i2=0; i2<rows; i2++){
                if(ocurrencias[i2] > 0)
                this.matrizcalculada[ix][i2]=matrizlocal[ix][i2]*(1+Math.log10(columns/ocurrencias[i2]));
            }
        }
        return matrizcalculada;
    }

    public double[][]getMatrizCalculada(){
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
     * Executa fórmula pretendida, incluindo os somatórios.
     * Executa o sumatório referente a um determinado ficheiro, e depois executa a fórmula final quando
     */
    public double[] grauSemelhanca() {
        grausemelhanca = new double[columns];
        double sumatorio1 = 0.0; //sumatorio para a primeira parte da fórmula
        double sumatorio2 = 0.0; //sumatório para a parte de baixo da fórmula
        double sumatorio3 = 0.0; //sumatório para a parte de baixo da fórmula

        for (int ix = 0; ix < columns; ix++) {
            for (int i2 = 0; i2 < rows; i2++) {
                sumatorio1 = sumatorio1 + (matrizcalculada[ix][i2] * ocurrencias[i2]);
                sumatorio2 = sumatorio2 + (matrizcalculada[ix][i2] * matrizcalculada[ix][i2]);
                sumatorio3 = sumatorio3 + (ocurrencias[i2] ^ 2);
            }
            grausemelhanca[ix] = (sumatorio1) / (Math.sqrt(sumatorio2)) * (Math.sqrt(sumatorio3));
            sumatorio1=0;
            sumatorio2=0;
            sumatorio3=0;
        }

        return grausemelhanca;
    }

    public double[] getGrausemelhanca(){
        return grausemelhanca;
    }

    public int[][] getMatrizlocal() {
        return matrizlocal;
    }

    public int[] getOcurrencias() {
        return ocurrencias;
    }
}


