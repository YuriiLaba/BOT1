import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import javax.mail.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class readGmail{
    public String protocolForRetrievingData;
    public String messageAccessProtocol;
    public String host;
    public String email;
    public String password;
    public Store store;

    readGmail(String protocolForRetrievingData, String messageAccessProtocol,
              String host, String email, String password){
        this.protocolForRetrievingData = protocolForRetrievingData;
        this.messageAccessProtocol = messageAccessProtocol;
        this.host = host;
        this.email = email;
        this.password = password;

    }


    public List<Integer> model() throws Exception {

        ArrayList<String> illWords = new ArrayList<String>(
                Arrays.asList("ill","illness", "disease", "sickness", "infection",
                        "disorder", "complaint", "ailment", "contagion"));


        Properties props = new Properties();
        //props.put("mail.store.protocol", "imaps");
        props.put(protocolForRetrievingData, messageAccessProtocol);
        Session session = Session.getDefaultInstance(props, null);
        //Store store = session.getStore("imaps");
        store = session.getStore(messageAccessProtocol);
        //store.connect("imap.googlemail.com","apostollmatt9@gmail.com", "Slayer41");
        store.connect(host, email, password);

        List<Integer> list = new ArrayList<Integer>();
        int numberOfIllness = 0;


        writeGmail writeGmailBOT = new writeGmail();
        Folder folder = store.getFolder("Inbox");
        if(!folder.isOpen())
            folder.open(Folder.READ_WRITE);

        Message[] messages = folder.getMessages();


        for (Message msg : messages) {
            //System.out.println(msg.getSubject());
            int count = 0;
            if((msg.getSubject()!= null)&&(!msg.isSet(Flags.Flag.SEEN))) {

                String[] parts = msg.getSubject().split(" ");

                for(String wrds: illWords){
                    for (String str : parts) {
                        str = str.toLowerCase();
                        if (str.equals(wrds)&&(count==0)) {
                            count+=1;
                            msg.setFlag(Flags.Flag.SEEN, true);
                            numberOfIllness += 1;
                            com.google.api.services.calendar.Calendar service =
                                    writeGmailBOT.getCalendarService();


                            Event event = new Event()
                                    .setSummary(String.valueOf(msg.getFrom()[0]));


                            DateTime startDateTime = new DateTime(msg.getReceivedDate());
                            EventDateTime start = new EventDateTime()
                                    .setDateTime(startDateTime);
                            event.setStart(start);

                            DateTime endDateTime = new DateTime(msg.getReceivedDate());
                            EventDateTime end = new EventDateTime()
                                    .setDateTime(endDateTime);
                            event.setEnd(end);

                            String calendarId = email;
                            event = service.events().insert(calendarId, event).execute();
                            System.out.printf("Event created: %s\n", event.getHtmlLink());
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
            //System.out.println("Body: \n"+ msg.getContent());
            //System.out.println(msg.getContentType());
            //Calendar beginTime = Calendar.getInstance();
        }
        list.add(numberOfIllness);
        list.add(folder.getMessageCount());
        list.add(folder.getMessageCount()-numberOfIllness);
        return list;
    }
}
