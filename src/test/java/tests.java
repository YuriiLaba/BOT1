import org.junit.Test;

import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class tests {

    @Test(expected = AuthenticationFailedException.class)
    public void testConfigFileIntegrity() throws IOException {
        Properties prop = new Properties();
        InputStream input;
        input = new FileInputStream("temp.txt");

        prop.load(input);

        new ReaderConfig(prop.getProperty("ProtocolForRetrievingEmail"),
                prop.getProperty("InternetStandardProtocol"),
                prop.getProperty("Host"),prop.getProperty("Email"),prop.getProperty("Password"));
    }


}
