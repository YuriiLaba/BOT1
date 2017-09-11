import org.junit.Test;

import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class tests {

    @Test(expected = IndexOutOfBoundsException.class)
    public void testConfigFileIntegrity() throws IOException {
        FileReader configFile = new FileReader();
        //configFile.openFile();
        List<String> configResult = configFile.readFile("temp.txt");
        new ReaderConfig(configResult.get(0),configResult.get(1),
                configResult.get(2),configResult.get(3),configResult.get(4));
    }

    @Test(expected = AuthenticationFailedException.class)
    public void testAuthentication() throws Exception {
        FileReader configFile = new FileReader();
        //configFile.openFile();
        List<String> configResult = configFile.readFile("temp.txt");

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
    @Test(expected = NoSuchProviderException.class)
    public void testProviderForISP() throws Exception {
        FileReader configFile = new FileReader();
        //configFile.openFile();
        List<String> configResult = configFile.readFile("temp.txt");

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
    @Test(expected = com.sun.mail.util.MailConnectException.class)
    public void testFailedConnectToHost() throws Exception {
        FileReader configFile = new FileReader();
        //configFile.openFile();
        List<String> configResult = configFile.readFile("temp.txt");

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
