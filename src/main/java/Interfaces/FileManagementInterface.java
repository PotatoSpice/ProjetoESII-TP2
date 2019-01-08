package Interfaces;

import java.io.IOException;
import java.util.ArrayList;


public interface FileManagementInterface<T> {

    public String getCurrentDirectory() throws IOException;

    public int getFileNumber();

    public ArrayList<String> getFilesName();

    public boolean fileReader();

}
