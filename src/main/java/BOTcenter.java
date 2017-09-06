
public class BOTcenter {
    public static void main(String[] args) throws Exception {
    readGmail a = new readGmail("mail.store.protocol","imaps",
            "imap.googlemail.com","apostollmatt9@gmail.com", "Slayer41");
    a.setProperties();
    a.model();
    }
}
