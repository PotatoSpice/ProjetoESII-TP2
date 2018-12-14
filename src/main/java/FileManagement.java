import java.io.*;
import java.util.ArrayList;

public class FileManagement {

    private String path;
    private String query;
    protected File[] dirfiles;


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


    //Lê os ficheiros e cria versões temporárias deles sem número e sem pontuação;
    public void fileReader(){

        ArrayList<String> content;

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

    public  void queryFile(){

        QueryManagement queryManagement = new QueryManagement();

        


    }


}
