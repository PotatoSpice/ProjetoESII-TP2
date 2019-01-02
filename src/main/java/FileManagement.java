import Interfaces.FileManagementInterface;

import java.io.*;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class FileManagement<T> implements FileManagementInterface<T> {

    private String path = "files/";
    private QueryManagement query;
    private File[] dirfiles;
    private File[] dirTempfiles;
    private int[][] matrixEquivalencia;


    /**
     * Construtor genérico da classe FileManagement com redifinição do path
     * @param path
     */
    public FileManagement(String path) {

        this.path = path;
    }

    /**
     * Construtor genérico da classe vazio
     */
    public FileManagement(){
        this.path = path;
    }

    /**
     * Getter genérico da variavel path
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     * Setter genérico do path
     * @param path caminho para tratamento dos ficheiros a realizar
     */
    public void setPath(String path) {

        this.path = path;
    }

    //  Está aqui para me lembrar de uma racicionio que estava a seguir
    //(tambem percebi o teu raciocinio. se queres fazer uma classe pa query tens de fazer assim
    //se nao nao consegues instt
    public FileManagement(String path, QueryManagement query) {

        this.path = path;
        this.query = query;

    }

    /**
     * @return Retorna o caminho que está a ser utilizado
     * @throws IOException
     */
    public String getCurrentDirectory() throws IOException {
        return new File(path).getCanonicalPath();
    }

    /**
     * Verifica o numero de ficheiros que existe dentro do directório
     * @return um numero inteiro com a quantidade de ficheiros existentes na pasta
     */
    public int getFileNumber() {
        return new File(path).list().length;
    }

    /**
     * Verifica o array do dirfiles e verifica os nomes existentes
     * @return Retorna uma lista com os nomes existentes na pasta
     */
    public ArrayList<String> getFilesName() {
        ArrayList<String> output = new ArrayList<>();

        for (int i = 0; i < this.dirfiles.length; i++) {
            output.add(dirfiles[i].getName());
        }

        return output;
    }

    /**
     *
     */
    private void setFiles() {
        File dirpath = new File(path);

        this.dirfiles = dirpath.listFiles((dir, name) -> {
            if (name.toLowerCase().endsWith(".txt")) {
                return new File(dir, name).isFile();
            } else {
                return false;
            }
        });

    }

    private void setTempFiles() {
        File dirpath = new File(path);

        dirTempfiles = dirpath.listFiles((dir, name) -> {
            if (name.toLowerCase().endsWith(".tmp")) {
                return new File(dir, name).isFile();
            } else {
                return false;
            }
        });

    }


    /**
     * Lê os ficheiros e cria versões temporárias deles sem número e sem pontuação;
     */
    public void fileReader() {

        String line;
        ArrayList<String> content;
        this.setFiles();
        for (int ix = 0; ix < this.dirfiles.length; ix++) {
            content = new ArrayList<String>();

            try {
                BufferedReader br = new BufferedReader(new FileReader(this.dirfiles[ix]));

                while ((line = br.readLine()) != null) {
                    content.add(line.replaceAll("[^\\p{L} ]", ""));
                }

                try {
                    File temp = File.createTempFile(this.dirfiles[ix].getName() + "temp", ".tmp");
                    temp.deleteOnExit();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
                    for (String string : content)
                        bw.write(string);

                    bw.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    /**
     * Procura entre os ficheiros temporários pela query do utilizador.
     * Guarda as repetições em Array de Inteiros.
     */
    public void queryFile() {

        QueryManagement queryManagement = new QueryManagement();
        ArrayList<String> query = new ArrayList<String>(queryManagement.getTrimmedquery());
        setTempFiles();
        matrixEquivalencia = new int[this.dirTempfiles.length][query.size()];

        for (int ix = 0; ix < this.dirTempfiles.length; ix++) {
            String line;
            try {
                BufferedReader br = new BufferedReader(new FileReader(this.dirTempfiles[ix]));

                while ((line = br.readLine()) != null) {
                    for (int i = 0; i < query.size(); i++) {
                        int lastindex = 0;
                        if (line.contains(query.get(i))) {
                            while ((lastindex = line.indexOf(query.get(1), lastindex)) <= query.size()) {
                                matrixEquivalencia[ix][i] = matrixEquivalencia[ix][i] + 1;
                                lastindex++;
                            }
                        }
                    }
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void SortedResult(ArrayList<FileManagement> lista, int maxFiles){
        ArrayList<FileManagement> output = new ArrayList();
        for(int i = 0;i<lista.size();i++){

        }
    }

}
