import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
        assertEquals(dirTest+"/"+f.getPath(),f.getCurrentDirectory(),"O caminho não é igual");
    }

    @Test
    public void getCurrentDirectoryInvalidTest() throws IOException {
        f.setPath("/asdsfdz");
        assertThrows(IOException.class,() -> {f.getCurrentDirectory();});
    }

    @Test
    void getFileNumber() {
        assertEquals(6,f.getFileNumber(),"O numero de ficheiros não é igual");
    }

    @Test
    void getFilesName() {
        assertEquals(Arrays.asList("ficheiro1","ficheiro2","ficheiro3","fucheiro4","ficheiro5","READ ME"),f.getFilesName());
    }

    @Test
    void fileReader() {

    }

    @Test
    void queryFile() {
    }

    @Test
    void sortedResult() {
    }
}