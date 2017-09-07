import java.util.List;

public class BOTcenter {
    public static void main(String[] args) throws Exception {
    //readGmail gmailBOT = new readGmail("mail.store.protocol","imaps",
            //"imap.googlemail.com","apostollmatt9@gmail.com", "Slayer41");


    ReadFile configFile = new ReadFile();
    configFile.openFile();
    List<String> configResult = configFile.readFile();
    readGmail gmailBOT = new readGmail(configResult.get(0),configResult.get(1),
            configResult.get(2),configResult.get(3), configResult.get(4));
    gmailBOT.model();
    }
}
