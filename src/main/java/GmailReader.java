import javax.mail.*;
import java.util.ArrayList;
import java.util.Arrays;


public class GmailReader{
    public ReaderConfig readerConfig;
    public ReaderAuthentication readerAuthentication;
    ArrayList<Message> messageArrayList = new ArrayList<Message>();

    public GmailReader(ReaderConfig readerConfig, ReaderAuthentication readerAuthentication) {
        this.readerConfig = readerConfig;
        this.readerAuthentication = readerAuthentication;

    }



    public boolean subjectAndSeenChecker(Message msg) throws MessagingException {
        return (msg.getSubject()!= null)&&(!msg.isSet(Flags.Flag.SEEN));
    }
    public boolean numberOfWordsRepetition(String str, String wrds, int count){
        return str.equals(wrds)&&(count==0);
    }

    public ArrayList<Message> model() throws Exception {

        ArrayList<String> illWords = new ArrayList<String>(
                Arrays.asList("ill","illness", "disease", "sickness", "infection",
                        "disorder", "complaint", "ailment", "contagion"));





        Folder folder = readerAuthentication.readerConnection().getFolder("Inbox");
        if(!folder.isOpen())
            folder.open(Folder.READ_WRITE);

        Message[] messages = folder.getMessages();


        for (Message msg : messages) {
            int count = 0;
            if(subjectAndSeenChecker(msg)) {

                String[] parts = msg.getSubject().split(" ");

                for(String wrds: illWords){
                    for (String str : parts) {
                        str = str.toLowerCase();
                        if (numberOfWordsRepetition(str, wrds, count)) {
                            count+=1;
                            msg.setFlag(Flags.Flag.SEEN, true);
                            messageArrayList.add(msg);

                        }
                    }
                }
            }


            //folder.close(false);
            //store.close();


            //if((msg.getSubject().equals("I'm ill")) && (msg.getSubject() != null)) {
            //numberOfIllness += 1;
            //}
            //System.out.println("Subject: " + msg.getSubject());
            //System.out.println(numberOfIllness);

            //System.out.println("From: " + msg.getFrom()[0]);
            //System.out.println("To: "+msg.getAllRecipients()[0]);
            //System.out.println("Date: "+msg.getReceivedDate());
            //System.out.println("Size: "+msg.getSize());
            //System.out.println(msg.getFlags());

            //System.out.println(msg.getContentType());
            //Calendar beginTime = Calendar.getInstance();

        }return messageArrayList;

    }
}

