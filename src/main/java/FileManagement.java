import Interfaces.FileManagementInterface;

import java.io.*;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileManagement<T> implements FileManagementInterface<T> {


    private int filecounter; //colunas da matriz
    private int querycounter; //linhas da matriz
    private String path = "files";
    private File[] dirfiles;
    private File[] dirTempfiles;
    private int[][] matrixequivalencia;
    QueryManagement query;


    public FileManagement(String path) {

        this.path = path;
    }

    /**
     * Construtor genérico da classe vazio
     */
    public FileManagement() {
        this.path = path;
    }

    public FileManagement(QueryManagement query) {
        this.query = query;
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


    /**
     * Vai buscar os ficheiros a serem lidos no caminho indicado pelo utilizador
     * LF-01.L2 + LF-01.L1
     */
    private File[] setFiles() {
        File dirpath = new File(path);

        FilenameFilter textFilter = (dir, name) -> name.toLowerCase().endsWith(".txt");

        dirfiles = dirpath.listFiles(textFilter);
        filecounter = dirfiles.length;
        return dirfiles;
    }

    /**
     * @return Retorna o caminho que está a ser utilizado
     * @throws IOException
     */
    public String getCurrentDirectory() throws IOException {

        File test = new File(path);
        String dir;
        if (test.isDirectory())
            dir = test.getCanonicalPath();
        else
            throw new IOException("A pasta não existe!");

        return dir;
    }


    /**
     * Verifica o numero de ficheiros que existe dentro do directório
     *
     * @return um numero inteiro com a quantidade de ficheiros existentes na pasta
     */
    public int getFileNumber() {

        File dirct = new File(path);

        if (dirct.isDirectory()) {
            if (dirct.list().length > 0)
                return dirct.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt")).length;
        }

        return 0;
    }

    /**
     * Verifica o array do dirfiles e verifica os nomes existentes
     *
     * @return Retorna uma lista com os nomes existentes na pasta
     */
    public ArrayList<String> getFilesName() {


        File[] f = new File(path).listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
        ArrayList<String> n = new ArrayList<String>();

        for (int i = 0; i < f.length; i++) {
            n.add(f[i].getName());
        }

        return n;
    }

    /**
     * @return um array de strings com os nomes dos ficheiros armazenados no arrays dirFiles
     */
    public String[] getFileString() {

        String[] dirfilestring = new String[dirfiles.length];
        if (this.dirfiles.length > 0) {
            for (int ix = 0; ix < dirfiles.length; ix++)
                dirfilestring[ix] = dirfiles[ix].toString();
        }
        return dirfilestring;
    }

    /**
     * Vai buscar os ficheiros temporários criados, com a informação organizada
     *
     * @return um array de Files com os ficheiros temporarios limpos de caracteres especiais e numeros
     */
    private File[] setTempFiles() {
        File dirpath = new File(path);
        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".tmp");
            }
        };

        dirTempfiles = dirpath.listFiles(textFilter);
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

            BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
            for (String string : content)
                bw.write(string);

            bw.close();
            temp.deleteOnExit(); //como a função bem diz, quando o programa terminar, apaga os ficheiros temporários
            return true;

        } catch (IOException e) {
            return false;
        }

    }

    private String clearNumbersandChars(String content) throws IOException {
        content = content.replaceAll("[^\\p{L} ]", "");
        return content;
    }


    /**
     * LF-01 + LF-01.1 + Implemetação LF-01.1.A
     * Lê os ficheiros e cria versões temporárias deles sem número e sem pontuação, invocando outro método
     *
     * @return se a operação de criação de ficheiros temporários foi bem sucecida
     */
    public boolean fileReader() {

        boolean checker = false;
        setFiles();

        try {
            for (int i = 0; i < this.dirfiles.length; i++) {

                Path newfile = Paths.get(this.dirfiles[i].toString());
                Charset cset = StandardCharsets.UTF_8;

                String content = new String(Files.readAllBytes(newfile), cset);
                content = clearNumbersandChars(content);

                Files.write(newfile, content.getBytes(cset));

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        /*
        String line;
        ArrayList<String> content;
        setFiles();

        boolean checker = true;

        for (int ix = 0; ix < filecounter; ix++) {

            content = new ArrayList<String>();

            try {
                BufferedReader br = new BufferedReader(new FileReader(this.dirfiles[ix]));

                clearNumbersandChars(br, content);

                boolean work = createTempfile(content, this.dirfiles[ix].getName());
                if (work == true && checker == true) {
                    checker = true;
                } else {
                    checker = false;

                }

            } catch (IOException e) {
                return false;
            }

        }*/


        return checker;
    }

    /**
     * Pesquiza nos ficheiros pelas palavras nas queries
     *
     * @return uma matriz preenchida com a similiariedade da query com o ficheiro
     */
    public int[][] queryFile() {

        setQuerycounter(query.getTrimmedquery().size());
        setTempFiles();
        matrixequivalencia = new int[filecounter][query.getTrimmedquery().size()];

        for (int ix = 0; ix < this.dirTempfiles.length; ix++) {
            String line;
            try {
                BufferedReader br = new BufferedReader(new FileReader(this.dirTempfiles[ix]));

                while ((line = br.readLine()) != null) {
                    for (int i = 0; i < query.getTrimmedquery().size(); i++) {
                        int lastindex = 0;
                        if (line.contains(query.getTrimmedquery().get(i))) {

                            while ((lastindex = line.indexOf(query.getTrimmedquery().get(i), lastindex)) <= query.getTrimmedquery().size() && lastindex != -1) {
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

    /**
     * Getter de querycounter
     *
     * @return
     */
    public int getQuerycounter() {
        return querycounter;
    }

    /**
     * Getter filecounter
     *
     * @return numero de colunas da matrix de equivalencias
     */
    public int getFilecounter() {
        return filecounter;
    }

    /**
     * Setter de querycounter
     *
     * @param querycounter tamanho das linhas da matriz de equivalencia
     */
    public void setQuerycounter(int querycounter) {
        this.querycounter = querycounter;
    }

    /**
     * @return
     */
    public int[][] getMatrixequivalencia() {

        return this.matrixequivalencia;

    }

}

