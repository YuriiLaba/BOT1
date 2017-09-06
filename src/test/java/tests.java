import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class tests {
    @Test
    public void numberOfIllness() throws Exception {
        readGmail a = new readGmail("mail.store.protocol","imaps",
                "imap.googlemail.com","apostollmatt9@gmail.com", "Slayer41");
        a.setProperties();
        List<Integer> list1 = a.model();
        int b = list1.get(0);
        assertEquals(3, b);
    }
    @Test
    public void numberOfLetters() throws Exception {
        readGmail a = new readGmail("mail.store.protocol","imaps",
                "imap.googlemail.com","apostollmatt9@gmail.com", "Slayer41");
        a.setProperties();
        List<Integer> list1 = a.model();
        int b = list1.get(1);
        assertEquals(7, b);
    }
    @Test
    public void differentLetters() throws Exception {
        readGmail a = new readGmail("mail.store.protocol","imaps",
                "imap.googlemail.com","apostollmatt9@gmail.com", "Slayer41");
        a.setProperties();
        List<Integer> list1 = a.model();
        int b = list1.get(2);
        assertEquals(4, b);
    }

}
