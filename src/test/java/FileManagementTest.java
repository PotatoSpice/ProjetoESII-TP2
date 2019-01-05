import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class FileManagementTest {

    private FileManagement f;

    @BeforeEach
    public void setUp() {
        f = new FileManagement();
    }

    @Test
    void getCurrentDirectoryValidTest() throws IOException {
        String dirTest = System.getProperty("user.dir");
        assertEquals(dirTest+"\\"+f.getPath(),f.getCurrentDirectory(),"O caminho não é igual");
    }

    @Test
    public void getCurrentDirectoryInvalidTest() throws IOException {
        f.setPath("ds<dfsadfasf");
        assertThrows(IOException.class,() -> {f.getCurrentDirectory();});
    }

    @Test
    void getFileNumber() {
        assertEquals(5,f.getFileNumber(),"O numero de ficheiros não é igual");
    }

    @Test
    void getFilesName() {
        assertEquals(Arrays.asList("ficheiro1.txt","ficheiro2.txt","ficheiro3.txt","ficheiro4.txt","ficheiro5.txt"),f.getFilesName());
    }

    @Test
    void fileReader() {

    }

    @Test
    void queryFile() {
    }

    @Test
    public void QueryCounterInvalidtest_BVA1(){
        f.setQuerycounter(-1);
        assertTrue(f.getQuerycounter() > 0,"As colunas nao podem ser negativas");
    }

    @Test
    public void QueryCounterValidtest_BVA2(){
        f.setQuerycounter(1);
        assertTrue(f.getQuerycounter() > 0,"");
    }

    @Test
    public void fileReaderInvalidtest_ECP2(){
        f.setPath("sdfdggafdz");
        assertFalse(f.fileReader());
    }

    @Test
    public void createTempFiletestValid_ECP1(){
        ArrayList content = new ArrayList<>();

        content.add("asdfdsf");
        content.add("bncvnvcb");
        content.add("fsd98f46d");

        assertTrue(f.createTempfile(content,"testfile"));
    }

    @Test
    public void createTempFileEmptytest_ECP2(){
        ArrayList content = new ArrayList();

        assertTrue(f.createTempfile(content,""));
    }

    @Test
    public void createTempFileInvalidtest(){
        f.setPath("asdsf");

        ArrayList content = new ArrayList();

        assertFalse(f.createTempfile(content,"test"));
    }

    @Test
    public void fileReadertest_ECP1(){
        assertTrue(f.fileReader());
    }

    @Test
    public void fileReadertestInvalid_ECP2(){
        f.setPath("asdsdfdsfg");
        assertTrue(f.fileReader());
    }
}