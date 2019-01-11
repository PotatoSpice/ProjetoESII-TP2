import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

class QueryManagementTest {

    private QueryManagement q;

    @BeforeEach
    public void setUp() {
        q = new QueryManagement("query de teste");
    }

    @Test
    public void EmptyQuerytest_QM1() {
        assertTrue(q.getQuery().length() > 0, "A query não pode estar vazia");
    }

    @Test
    public void trimmedQueryLengthtest_QM4() {
        assertEquals(3, q.getTrimmedquery().size(), "O tamanho nao coincide");
    }

    @Test
    public void EmptytrimmedQuerytest_QM3() {
        assertNotEquals(0, q.getTrimmedquery().size(), "A trimmedQuery nao devia estar vazia");
    }

    @Test
    public void EmptyQueryTrimmedtest_QM5() {
        QueryManagement q1 = new QueryManagement("");
        assertFalse(q1.getTrimmedquery().isEmpty());
    }

    @Test
    public void nullQueryTrimmedtest_QM6() {
        QueryManagement q1 = new QueryManagement();
        assertTrue(q1.getTrimmedquery().isEmpty());
    }

    @Test
    public void trimQueryValidtest_QM2() {
        assertEquals(Arrays.asList("query", "de", "teste"), q.getTrimmedquery(), "A Query nao ta devidamente distribuida pelo arrayList");
    }

    @Test
    public void trimmedQuerySizetest_QM7() {
        assertTrue(q.getTrimmedquery().size() < 4);
    }
}