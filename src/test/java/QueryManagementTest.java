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
    public void getQueryValidtest() {
        assertEquals("query de teste",q.getQuery(),"A query não é igual!!");
    }

    @Test
    public void getQueryInvalidtest(){
        assertNotEquals("asdfsd", q.getQuery(),"A query nao devia ser igual");
    }

    @Test
    public void trimmedQueryLengthtest(){
        assertEquals(3,q.getTrimmedquery().size(),"O tamanho nao coincide");
    }

    @Test
    public void EmptyQuerytest(){
        assertNotEquals(0,q.getTrimmedquery().size(),"A trimmedQuery nao devia estar vazia");
    }

    @Test
    public void trimQueryValidtest() {
        assertEquals(Arrays.asList("query", "de", "teste"), q.getTrimmedquery(),"A Query nao ta devidamente distribuida pelo arrayList");
    }


}