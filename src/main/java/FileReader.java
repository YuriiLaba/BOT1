import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    private Scanner scannerFile;
    public void openFile(){
        try{
            scannerFile = new Scanner(new File("temp.txt"));

        } catch (Exception e) {
            System.out.println("could no find file");
        }
    }

    public List<String> readFile(){
        int i = 0;
        ArrayList<String> config = new ArrayList<String>();
        while (scannerFile.hasNext()){
            i++;
            String a = scannerFile.next();
            if(i == 3){
                config.add(a);
                i = 0;
            }
        }

        return config;

    }
}
