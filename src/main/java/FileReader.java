import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReader {

    public ArrayList<String> readFile(String filename) throws IOException {
        ArrayList<String> configList = new ArrayList<String>();
        BufferedReader br = null;
        java.io.FileReader fr = null;

        try {

            fr = new java.io.FileReader(filename);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                String[] tempListOfSplitedLines = sCurrentLine.split(" = ");
                configList.add(tempListOfSplitedLines[tempListOfSplitedLines.length - 1]);

            }

        }
        catch (IOException e) {

            e.printStackTrace();

        }finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }return configList;

    }


}
