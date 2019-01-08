import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
    void getCurrentDirectoryValidTest_FM1() throws IOException {
        String dirTest = System.getProperty("user.dir");
        assertEquals(dirTest + "\\" + f.getPath(), f.getCurrentDirectory(), "O caminho não é igual");
    }

    @Test
    public void getCurrentDirectoryInvalidTest_FM2() throws IOException {
        f.setPath("dsdfsadfasf");
        assertThrows(IOException.class, () -> {
            f.getCurrentDirectory();
        });
    }

    @Test
    void getFileNumberValid_FM3() {

        assertEquals(5, f.getFileNumber(), "O numero de ficheiros não é igual");
    }

    @Test
    public void getFileNumberEmptyFoldertest_FM4(){
        f.setPath("\\files\\vazia");
        assertEquals(0,f.getFileNumber());

    }

    @Test
    public void getFileNumberInvalidfoldertest_FM5(){
        f.setPath("adsfdasdsd");
        assertEquals(0,f.getFileNumber());
    }

    @Test
    void getFilesNameValidFoldertest_FM6() {
        assertEquals(Arrays.asList("ficheiro1.txt", "ficheiro2.txt", "ficheiro3.txt", "ficheiro4.txt", "ficheiro5.txt"), f.getFilesName());
    }

    @Test
    public void getFilesNamesInvalidFolder_FM7(){
        f.setPath("asdasdasd");
        assertTrue(f.getFilesName().isEmpty());
    }

    @Test
    public void getFilesNamEmptyFoldertest_FM8(){
        f.setPath("\\files\\vazia");
        assertTrue(f.getFilesName().isEmpty());
    }

    @Test
    public void createTempFiletestValid_FM9() {
        ArrayList content = new ArrayList<>();

        content.add("asdfdsf");
        content.add("bncvnvcb");
        content.add("fsd98f46d");

        assertTrue(f.createTempfile(content, "testfile"));
    }

    @Test
    public void createTempFileInvalidFoldertest_FM10() {
        f.setPath("asdsfadffasd");
        ArrayList content = new ArrayList();

        content.add("asdfdsf");
        content.add("bncvnvcb");
        content.add("fsd98f46d");

        assertFalse(f.createTempfile(content, ""));
    }

    @Test
    public void createTempFileEmptyFoldertest_FM11(){
        f.setPath("\\files\\vazia");
        ArrayList content = new ArrayList();

        content.add("asdfdsf");
        content.add("bncvnvcb");
        content.add("fsd98f46d");

        assertFalse(f.createTempfile(content, ""));

    }

    @Test
    public void getFileStringValid_FM12(){
        f.fileReader();
        assertArrayEquals(new String[]{f.getPath()+"\\ficheiro1.txt",f.getPath()+"\\ficheiro2.txt",f.getPath()+"\\ficheiro3.txt",f.getPath()+"\\ficheiro4.txt",f.getPath()+"\\ficheiro5.txt"},f.getFileString());
    }

    @Test
    public void getFileStringInvalidFolder_FM13(){
        f.setPath("asdsgsdgfd");
        f.fileReader();
        assertEquals(0,f.getFileString().length);
    }

    @Test
    public void getFileStringEmptyFolder_FM14(){
        f.setPath("\\files\\vazia");
        f.fileReader();
        assertEquals(0,f.getFileString().length);
    }

    @Test
    public void fileReadertestValid_FM15() {
        assertTrue(f.fileReader());
    }

    @Test
    public void fileReaderNofile_FM16(){
        f.setPath("asddfdgsdsgfsd");
        assertFalse(f.fileReader());
    }

}