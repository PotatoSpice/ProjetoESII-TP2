import java.io.*;
import java.util.ArrayList;

public class FileManagement {

    private String path;
    private String query;
    private File[] dirfiles;
    private File[] dirTempfiles;
    private int [][] matrixequivalencia;


    public FileManagement(String path){

        this.path=path;
    }

  /*  Está aqui para me lembrar de uma racicionio que estava a seguir
  public FileManagement(String path, String query){

        this.path=path;
        this.query=query;

    }
    */

    public void setFiles(){
        File dirpath= new File(path);

        dirfiles = dirpath.listFiles((dir, name) -> {
            if (name.toLowerCase().endsWith(".txt") ) {
                return new File(dir,name).isFile();
            }else{
                return false;
            }
        });

    }

    public void setTempFiles(){
        File dirpath= new File(path);

        dirTempfiles = dirpath.listFiles((dir, name) -> {
            if (name.toLowerCase().endsWith(".temp") ) {
                return new File(dir,name).isFile();
            }else{
                return false;
            }
        });

    }


    //Lê os ficheiros e cria versões temporárias deles sem número e sem pontuação;
    public void fileReader(){

        ArrayList<String> content;
        setFiles();
        for(int ix=0; ix<this.dirfiles.length; ix++){
            content = new ArrayList<String>();

            try {
                BufferedReader br = new BufferedReader(new FileReader(this.dirfiles[ix]));

                while (br.readLine()!=null){
                    content.add(br.readLine().replaceAll("[^\\p{L} ]",""));
                }

                try {
                    File temp = File.createTempFile( this.dirfiles[ix].getName()+"temp", ".tmp");
                    temp.deleteOnExit();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
                   for(String string:content)
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


    //Procura entre os ficheiros temporários pela query do utilizador. Guarda as repetições em Array de Inteiros.

    public void queryFile(){

        QueryManagement queryManagement = new QueryManagement();
        ArrayList<String> query= new ArrayList<String>(queryManagement.getTrimmedquery());
        setTempFiles();
        matrixequivalencia= new int[this.dirTempfiles.length][query.size()];

        for (int ix=0; ix<this.dirTempfiles.length; ix++){

            try {
                BufferedReader br = new BufferedReader(new FileReader(this.dirTempfiles[ix]));

                while(br.readLine()!=null){
                    for (int i=0; i<query.size();i++){
                        int lastindex=0;
                        if(br.readLine().contains(query.get(i))){
                            while((lastindex=br.readLine().indexOf(query.get(1),lastindex))<=query.size()) {
                                matrixequivalencia[ix][i] = matrixequivalencia[ix][i];
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

}
