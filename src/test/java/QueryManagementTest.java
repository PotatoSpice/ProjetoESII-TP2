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
    public void getQueryValidtest_ECP1() {
        assertEquals("query de teste", q.getQuery(), "A query não é igual!!");
    }

    @Test
    public void EmptyQuerytest_ECP2(){
        assertTrue(q.getQuery().length() > 0,"A query não pode estar vazia");
    }

    @Test
    public void trimmedQueryLengthtest_ECP1() {
        assertEquals(3, q.getTrimmedquery().size(), "O tamanho nao coincide");
    }

    @Test
    public void EmptytrimmedQuerytest_ECP1() {
        assertNotEquals(0, q.getTrimmedquery().size(), "A trimmedQuery nao devia estar vazia");
    }

    @Test
    public void trimQueryValidtest_ECP2() {
        assertEquals(Arrays.asList("query", "de", "teste"), q.getTrimmedquery(), "A Query nao ta devidamente distribuida pelo arrayList");
    }

    @Test
    public void trimmedQuerySizetest(){
        assertTrue(q.getTrimmedquery().size() < 4);
    }
}