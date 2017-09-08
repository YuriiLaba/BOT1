import javax.mail.Message;
import java.util.ArrayList;
import java.util.List;

public class BOTcenter {

    public static void main(String[] args) throws Exception {
        FileReader configFile = new FileReader();
        configFile.openFile();
        List<String> configResult = configFile.readFile();
        //System.out.println(configResult.get(0));
        //System.out.println(configResult.get(1)) ;
        //configFile.readFile();

        ReaderConfig readerConfig = new ReaderConfig(configResult.get(0),configResult.get(1),
                configResult.get(2),configResult.get(3),configResult.get(4));

        ReaderAuthentication readerAuthentication = new ReaderAuthentication(readerConfig);
        GmailReader gmailReader = new GmailReader(readerConfig, readerAuthentication);
        ArrayList<Message> lstOfIllPeople = gmailReader.model();
        for(int i = 0; i<lstOfIllPeople.size(); i++){
            WriterAuthentication writerAuthentication = new WriterAuthentication();
            EventManager newEvent = new EventManager(writerAuthentication, lstOfIllPeople.get(i));
            newEvent.eventCreator();
        }

    }
}
