import org.junit.Test;
import javax.mail.AuthenticationFailedException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class Tests {


    @Test(expected = AuthenticationFailedException.class)
    public void testValidPassword() throws Exception {
        Properties prop = new Properties();
        InputStream input;
        input = new FileInputStream("temp.txt");

        prop.load(input);

        ReaderConfig readerConfig = new ReaderConfig(prop.getProperty("ProtocolForRetrievingEmail"),
                prop.getProperty("InternetStandardProtocol"),
                prop.getProperty("Host"), prop.getProperty("Email"), prop.getProperty("Password"));
        ReaderAuthentication readerAuthentication = new ReaderAuthentication(readerConfig);
        GmailReader gmailReader = new GmailReader(readerConfig, readerAuthentication);
        gmailReader.analyse();
    }
    @Test(expected = javax.mail.MessagingException.class)
    public void testValidness() throws Exception {
        Properties prop = new Properties();
        InputStream input;
        input = new FileInputStream("temp.txt");

        prop.load(input);

        ReaderConfig readerConfig = new ReaderConfig(prop.getProperty("ProtocolForRetrievingEmail"),
                prop.getProperty("InternetStandardProtocol"),
                prop.getProperty("Host"), prop.getProperty("Email"), prop.getProperty("Password"));
        ReaderAuthentication readerAuthentication = new ReaderAuthentication(readerConfig);
        GmailReader gmailReader = new GmailReader(readerConfig, readerAuthentication);
        gmailReader.analyse();
    }
    @Test
    public void testLongVacation() throws Exception {
        Properties prop = new Properties();
        InputStream input;
        input = new FileInputStream("temp.txt");

        prop.load(input);

        ReaderConfig readerConfig = new ReaderConfig(prop.getProperty("ProtocolForRetrievingEmail"),
                prop.getProperty("InternetStandardProtocol"),
                prop.getProperty("Host"), prop.getProperty("Email"), prop.getProperty("Password"));
        ReaderAuthentication readerAuthentication = new ReaderAuthentication(readerConfig);
        GmailReader gmailReader = new GmailReader(readerConfig, readerAuthentication);
        gmailReader.analyse();
        ArrayList<String> lst = gmailReader.getCheckList();
        assertEquals(true,lst.contains("Too long vacation: you don't deserve it"));
    }
    @Test
    public void testBadDateFormat() throws Exception {
        Properties prop = new Properties();
        InputStream input;
        input = new FileInputStream("temp.txt");

        prop.load(input);

        ReaderConfig readerConfig = new ReaderConfig(prop.getProperty("ProtocolForRetrievingEmail"),
                prop.getProperty("InternetStandardProtocol"),
                prop.getProperty("Host"), prop.getProperty("Email"), prop.getProperty("Password"));
        ReaderAuthentication readerAuthentication = new ReaderAuthentication(readerConfig);
        GmailReader gmailReader = new GmailReader(readerConfig, readerAuthentication);
        gmailReader.analyse();
        ArrayList<String> lst = gmailReader.getCheckList();
        assertEquals(true,lst.contains("Bad Date Format"));
    }


}
