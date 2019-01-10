import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class FileManagementTest {

    private FileManagement f;

    @BeforeEach
    public void setUp() {
        f = new FileManagement();
    }

    @Test
    void getCurrentDirectoryValidTest_FM1() throws IOException {
        String dirTest = System.getProperty("user.dir");
        System.out.println(dirTest + f.getPath());
        assertEquals(dirTest + File.separator + f.getPath(), f.getCurrentDirectory(), "O caminho não é igual");
    }

    @Test
    public void getCurrentDirectoryInvalidTest_FM2() throws IOException {
        f.setPath("dsdfsadfasf");
        assertThrows(IOException.class, () -> {
            f.getCurrentDirectory();
        }, "Devia ter mandado uma excepção");
    }

    @Test
    void getFileNumberValid_FM3() {

        assertEquals(5, f.getFileNumber(), "O numero de ficheiros não é igual");
    }

    @Test
    public void getFileNumberEmptyFoldertest_FM4() {
        f.setPath("files" + File.separator + "vazia");
        assertEquals(0, f.getFileNumber(), "Devia ter 0");

    }

    @Test
    public void getFileNumberInvalidfoldertest_FM5() {
        f.setPath("adsfdasdsd");
        assertEquals(0, f.getFileNumber(), "Devia ter 0");
    }

    @Test
    void getFilesNameValidFoldertest_FM6() {
        f.fileReader();
        ArrayList teststr = f.getFilesName();
        Collections.sort(teststr);
        assertEquals(Arrays.asList("ficheiro1.txt", "ficheiro2.txt", "ficheiro3.txt", "ficheiro4.txt", "ficheiro5.txt"), teststr);
    }

    @Test
    public void getFilesNamesInvalidFolder_FM7() {
        f.setPath("asdasdasd");
        assertTrue(f.getFilesName().isEmpty(), "A lista deveria tar vazia");
    }

    @Test
    public void getFilesNamEmptyFoldertest_FM8() {
        f.setPath("files" + File.separator + "vazia");
        assertTrue(f.getFilesName().isEmpty(), "A lista devia tar vazia");
    }

    @Test
    public void getFileStringValid_FM12() {
        f.fileReader();
        String[] str = new String[]{f.getPath() + File.separator+"ficheiro1.txt", f.getPath() + File.separator +"ficheiro2.txt", f.getPath() +File.separator +"ficheiro3.txt", f.getPath() + File.separator+"ficheiro4.txt", f.getPath() + File.separator+"ficheiro5.txt"};
        String[] teststr = f.getFileString();
        Arrays.sort(teststr);
        assertArrayEquals(str, teststr, "Os nomes dos ficheiros nao sao iguais");
    }

    @Test
    public void getFileStringInvalidFolder_FM13() {
        f.setPath(File.separator + "asdsgsdgfd");
        f.fileReader();
        assertTrue(f.getFileString() == null, "Devia ter dado true (null)");
    }

    @Test
    public void getFileStringEmptyFolder_FM14() {
        f.setPath(File.separator + "files" + File.separator + "vazia");
        f.fileReader();
        assertTrue(f.getFileString() == null, "Devia ter dado true (null)");
    }

    @Test
    public void fileReadertestValid_FM15() {
        assertTrue(f.fileReader(), "Devia devolver true");
    }

    @Test
    public void fileReaderNofile_FM16() {
        f.setPath("asddfdgsdsgfsd");
        assertFalse(f.fileReader(), "Devia devolver False");
    }

}