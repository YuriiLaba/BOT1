import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class BOTcenter {

    public static void main(String[] args) throws Exception {

        Properties prop = new Properties();
        InputStream input;
        input = new FileInputStream("temp.txt");

        prop.load(input);

        ReaderConfig readerConfig = new ReaderConfig(prop.getProperty("ProtocolForRetrievingEmail"),
                prop.getProperty("InternetStandardProtocol"),
                prop.getProperty("Host"),prop.getProperty("Email"),prop.getProperty("Password"));

        ReaderAuthentication readerAuthentication = new ReaderAuthentication(readerConfig);
        GmailReader gmailReader = new GmailReader(readerConfig, readerAuthentication);
        gmailReader.analyse();


    }
}
