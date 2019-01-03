import java.io.*;
import java.util.ArrayList;

public class FileManagement {

    private int filecounter; //colunas da matriz
    private int querycounter; //linhas da matriz
    private String path;
    private File[] dirfiles;
    private File[] dirTempfiles;
    private int[][] matrixequivalencia;

    public int[][] getMatrixequivalencia() {

        return this.matrixequivalencia;

    }

    public FileManagement(String path) {
        this.path = path;
    }

  /*  Está aqui para me lembrar de uma racicionio que estava a seguir
  public FileManagement(String path, String query){

        this.path=path;
        this.query=query;

    }
    */

    /**
     * Vai buscar os ficheiros a serem lidos no caminho indicado pelo utilizador
     * LF-01.L2 + LF-01.L1
     */
    private void setFiles() {
        File dirpath = new File(path);

        dirfiles = dirpath.listFiles((dir, name) -> {
            if (name.toLowerCase().endsWith(".txt")) {
                return new File(dir, name).isFile();
            } else {
                return false;
            }
        });
        filecounter = dirfiles.length;
    }

    public String[] getFileString(){

        String[] dirfilestring = new String[dirfiles.length];
        for(int ix=0; ix<dirfiles.length; ix++)
            dirfilestring[ix]=dirfiles[ix].toString();
        return dirfilestring;
    }

    /**
     * Vai buscar os ficheiros temporários criados, com a informação organizada
     */
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

    /** LF-01.1.A
     * @param content ArrayList com o conteúdo do documento, organizado linha a linha, sem números e sem pontuações
     * @param filename Nome do ficheiro de onde a informação foi lida. Passará para o ficheiro temporário.
     */
    public void createTempfile(ArrayList<String> content, String filename) {

        try {
            File temp = File.createTempFile(filename + "temp", ".tmp");
            temp.deleteOnExit(); //como a função bem diz, quando o programa terminar, apaga os ficheiros temporários
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
            for (String string : content)
                bw.write(string);

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * LF-01 + LF-01.1 + Implemetação LF-01.1.A
     *   Lê os ficheiros e cria versões temporárias deles sem número e sem pontuação, invocando outro método
     */

    public void fileReader() {

        String line;
        ArrayList<String> content;
        setFiles();
        for (int ix = 0; ix < this.dirfiles.length; ix++) {
            content = new ArrayList<String>();

            try {
                BufferedReader br = new BufferedReader(new FileReader(this.dirfiles[ix]));

                while ((line = br.readLine()) != null) {
                    content.add(line.replaceAll("[^\\p{L} ]", ""));
                }

                createTempfile(content, this.dirfiles[ix].getName());

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }




    /** LF-01.2
     *  Procura entre os ficheiros temporários pela query do utilizador. Guarda as repetições em Matriz de Inteiros, a chamada Matriz de
     *  de equivalência.
     */
    public void queryFile() {

        QueryManagement queryManagement = new QueryManagement();
        ArrayList<String> query = new ArrayList<String>(queryManagement.getTrimmedquery());
        setQuerycounter(query.size());
        setTempFiles();
        matrixequivalencia = new int[this.dirTempfiles.length][query.size()];

        for (int ix = 0; ix < this.dirTempfiles.length; ix++) {
            String line;
            try {
                BufferedReader br = new BufferedReader(new FileReader(this.dirTempfiles[ix]));

                while ((line = br.readLine()) != null) {
                    for (int i = 0; i < query.size(); i++) {
                        int lastindex = 0;
                        if (line.contains(query.get(i))) {
                            while ((lastindex = line.indexOf(query.get(1), lastindex)) <= query.size()) {
                                matrixequivalencia[ix][i] = matrixequivalencia[ix][i] + 1;
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

    public int getQuerycounter() {
        return querycounter;
    }

    public int getFilecounter() {
        return filecounter;
    }

    public void setQuerycounter(int querycounter) {
        this.querycounter = querycounter;
    }


}

