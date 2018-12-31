public class MatrixManagement {

    //Construtor da classe
    public MatrixManagement(FileManagement fileManagement){
        matrizlocal(fileManagement);
    }

    private double[][]matrizcalculada;
    private double[] grausemelhanca;

    int[][] matrizlocal;
    int columns; //documentos
    int rows; //palavras

    private int[] ocurrencias;

    /**
     * Define os cálculos da Matriz como requiridos no requisito LF-01.2.C
     */
    public void setMatrizCalculada(){
        occurrences();
        for(int ix=0; ix<columns; ix++){
            for(int i2=0; i2<rows; i2++){
                this.matrizcalculada[ix][i2]=matrizlocal[ix][i2]*Math.log10(columns/ocurrencias[i2]);
            }
        }
    }

    public double[][] getMatrizCalculada(){
        return this.matrizcalculada;
    }

    /**
     * Este método verifica em quantos documentos existe a palavra em qualquer posição da query.
     * Pesquisa na matriz local. Se alguma dada palvra existe(>0), incrementa na sua posição da matriz de ocurrencias.
     * Como requirido no requisito LF-01.3
     */
    public void occurrences() {
        ocurrencias= new int[rows];
        for (int ix = 0; ix < columns; ix++) {
            for (int i2 = 0; i2 < rows; i2++) {
                if(matrizlocal[ix][i2]>0){
                    ocurrencias[i2]++;
                }
            }
        }
    }

    /**
     * Esta função vai buscar os dados da matriz dos documentos e passa para esta classe. Chama-se no construtor.
     * Não existe em requisito, mas é fundamental para a correta utilização desta classe.
     * @param fileManagement instância do FileManagement criado no Main
     */
    public void matrizlocal(FileManagement fileManagement){
        matrizlocal = fileManagement.getMatrixequivalencia();
        columns = fileManagement.getFilecounter();
        rows = fileManagement.getQuerycounter();
    }

    /**
     * Referente ao requisito Calculos-01
     *
     * ISSUE:Problemas na interpretação da fórmula. Esclarecer problemas. Fórmula errada ou incompleta.
     */
    public void grauSemelhanca(){
        grausemelhanca=new double[columns];

        for(int ix=0; ix<columns; ix++){
            for (int i2=0; i2<rows;i2++){
                grausemelhanca[ix]=(matrizcalculada[ix][i2]*ocurrencias[i2])/(Math.sqrt(matrizcalculada[ix][i2]*matrizcalculada[ix][i2]))*(Math.sqrt(ocurrencias[i2]^2));
            }
        }
    }
}
