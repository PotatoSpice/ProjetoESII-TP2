import Interfaces.FileManagementInterface;

import java.io.*;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManagement<T> implements FileManagementInterface<T> {


    private int filecounter; //colunas da matriz
    private int querycounter; //linhas da matriz
    private String path = "files";
    private File[] dirfiles;
    private File[] dirTempfiles;
    private int[][] matrixequivalencia;


    public FileManagement(String path) {

        this.path = path;
    }

    /**
     * Construtor genérico da classe vazio
     */
    public FileManagement() {
        this.path = path;
    }

    /**
     * Getter genérico da variavel path
     *
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     * Setter genérico do path
     *
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
    }

    /**
     * Vai buscar os ficheiros a serem lidos no caminho indicado pelo utilizador
     * LF-01.L2 + LF-01.L1
     */
    private File[] setFiles() {
        File dirpath = new File(path);

        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");

            }
        };

        File[] files = dirpath.listFiles(textFilter);

        dirfiles = files;
        filecounter = dirfiles.length;
        return dirfiles;
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
     *
     * @return um numero inteiro com a quantidade de ficheiros existentes na pasta
     */
    public int getFileNumber() {
        return new File(path).list().length;
    }

    /**
     * Verifica o array do dirfiles e verifica os nomes existentes
     *
     * @return Retorna uma lista com os nomes existentes na pasta
     */
    public ArrayList<String> getFilesName() {

        String[] f = new File(path).list();
        List n = Arrays.asList(f);

        return new ArrayList<String>(n);
    }

    public String[] getFileString() {

        String[] dirfilestring = new String[dirfiles.length];
        for (int ix = 0; ix < dirfiles.length; ix++)
            dirfilestring[ix] = dirfiles[ix].toString();
        return dirfilestring;
    }

    /**
     * Vai buscar os ficheiros temporários criados, com a informação organizada
     */
    private File[] setTempFiles() {
        File dirpath = new File(path);
        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".tmp");
            }
        };

        File[] files = dirpath.listFiles(textFilter);
        dirTempfiles = files;
        return dirTempfiles;
    }

    /**
     * LF-01.1.A
     *
     * @param content  ArrayList com o conteúdo do documento, organizado linha a linha, sem números e sem pontuações
     * @param filename Nome do ficheiro de onde a informação foi lida. Passará para o ficheiro temporário.
     * @return sucesso (ou não) do método
     */
    public boolean createTempfile(ArrayList<String> content, String filename) {

        File directory = new File(path);

        try {
            File temp = File.createTempFile(filename + "temp", ".tmp", directory);
            temp.deleteOnExit(); //como a função bem diz, quando o programa terminar, apaga os ficheiros temporários
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
            for (String string : content)
                bw.write(string);

            bw.close();

            return true;

        } catch (IOException e) {
            return false;
        }

    }

    /**
     * LF-01 + LF-01.1 + Implemetação LF-01.1.A
     * Lê os ficheiros e cria versões temporárias deles sem número e sem pontuação, invocando outro método
     *
     * @return se a operação de criação de ficheiros temporários foi bem sucecida
     */
    public boolean fileReader() {

        String line;
        ArrayList<String> content;
        setFiles();

        boolean checker = true;

        for (int ix = 0; ix < filecounter; ix++) {

            content = new ArrayList<String>();

            try {
                BufferedReader br = new BufferedReader(new FileReader(this.dirfiles[ix]));

                while ((line = br.readLine()) != null) {
                    content.add(line.replaceAll("[^\\p{L} ]", ""));
                }

                boolean work = createTempfile(content, this.dirfiles[ix].getName());
                if (work == true && checker == true) {
                    checker = true;
                } else {
                    checker = false;

                }

            } catch (IOException e) {
                return false;
            }

        }

        if (checker == true) {
            return checker;
        } else {
            return checker;
        }

    }


    public int[][] queryFile() {

        QueryManagement queryManagement = new QueryManagement();
        ArrayList<String> query = new ArrayList<String>(queryManagement.getTrimmedquery());
        setQuerycounter(query.size());
        setTempFiles();
        matrixequivalencia = new int[filecounter][query.size()];

        for (int ix = 0; ix < this.dirTempfiles.length; ix++) {
            String line;
            try {
                BufferedReader br = new BufferedReader(new FileReader(this.dirTempfiles[ix]));

                while ((line = br.readLine()) != null) {
                    for (int i = 0; i < query.size(); i++) {
                        int lastindex = 0;
                        if (line.contains(query.get(i))) {

                            while ((lastindex = line.indexOf(query.get(i), lastindex)) <= query.size() && lastindex != -1) {
                                matrixequivalencia[ix][i] = matrixequivalencia[ix][i] + 1;

                                lastindex++;

                            }
                        }
                    }
                }

            } catch (FileNotFoundException e) {
                return null;
            } catch (IOException e) {
                return null;

            }
        }

        return matrixequivalencia;

    }

    public int getQuerycounter() {
        return querycounter;
    }

    public int getFilecounter() {
        return filecounter;
    }

    public void setQuerycounter(int querycounter) {
        this.querycounter = querycounter;
    }

    public int[][] getMatrixequivalencia() {

        return this.matrixequivalencia;

    }

}

