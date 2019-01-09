import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MatrixManagementTest {

    QueryManagement q;
    FileManagement f;
    MatrixManagement matriz;

    @BeforeEach
    void setUp() {
        q = new QueryManagement("Java");
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
        assertEquals(matriz.getColumns(),f.getFileNumber());
    }

    @Test
    public void ocurrencesArraytest_MM2(){
        assertArrayEquals(new int[]{1},matriz.occurrences());
    }

    @Test
    void grauSemelhanca() {
    }
}