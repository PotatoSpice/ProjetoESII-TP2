import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MatrixManagementTest {

    QueryManagement q;
    FileManagement f;
    MatrixManagement matriz;

    @BeforeEach
    void setUp() {
        q = new QueryManagement("lorem sapien");
        f = new FileManagement(q);
        f.fileReader();
        f.queryFile();
        matriz = new MatrixManagement(f);
    }

    @Test
    void setMatrizCalculada() {
    }

    @Test
    void occurrencesArraySizeTestValid_MM1() {
        assertEquals(matriz.getColumns(),f.getFileNumber(),"O numero de colunas não tá igual ao numero da query");
    }

    @Test
    public void ArraytestSizeROwstest_MM2(){
        assertEquals(q.getTrimmedquery().size(),matriz.getRows(),"O numero de filas não ta igual ao numero de ficheiros");
    }

    @Test
    public void OcorrencesValidtest_MM3(){
        int[][] testmatriz = matriz.getMatrizlocal();
        System.out.println(matriz.getColumns());
        System.out.println(matriz.getRows());
        testmatriz[2][0] = 1;
        for (int[] x : testmatriz) {
            for (int y : x) {
                System.out.print(y + "! ");
            }
            System.out.println("|");
        }

        assertArrayEquals(new int[]{5,3},matriz.occurrences(),"O array não tem o numero de ocorrencias esperado");
    }

    @Test
    public void OccurrencesArraySizetest_MM4(){
        assertTrue(matriz.occurrences().length == matriz.getRows());
    }


    @Test
    void grauSemelhanca() {
    }
}