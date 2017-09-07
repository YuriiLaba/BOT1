import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
    private Scanner x;
    public void openFile(){
        try{
            x = new Scanner(new File("temp.txt"));

        } catch (Exception e) {
            System.out.println("could no find file");
        }
    }

    public List<String> readFile(){
        ArrayList<String> config = new ArrayList<String>();
        while (x.hasNext()){
            String a = x.next();
            config.add(a);
        }
        return config;
    }
}
