import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

public class ReaderAuthentication {

    public ReaderConfig readerConfig;
    public Store store;
    public ReaderAuthentication(ReaderConfig readerConfig) {
        this.readerConfig = readerConfig;
    }
    public Session setReaderAuthenticationProperties() throws MessagingException {
        Properties props = new Properties();
        props.put(readerConfig.protocolForRetrievingData, readerConfig.messageAccessProtocol);
        Session session = Session.getDefaultInstance(props, null);
        return session;


    }
    public Store readerConnection() throws MessagingException {
        store = setReaderAuthenticationProperties().getStore(readerConfig.messageAccessProtocol);
        store.connect(readerConfig.host, readerConfig.email, readerConfig.password);
        return store;
    }



}
