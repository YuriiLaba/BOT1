import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GmailReader {
    public ReaderConfig readerConfig;
    public ReaderAuthentication readerAuthentication;


    public GmailReader(ReaderConfig readerConfig, ReaderAuthentication readerAuthentication) {
        this.readerConfig = readerConfig;
        this.readerAuthentication = readerAuthentication;

    }


    public boolean subjectAndSeenChecker(Message msg) throws MessagingException {
        return (msg.getSubject() != null) && (!msg.isSet(Flags.Flag.SEEN));
    }

    public boolean numberOfWordsRepetition(String str, String words, int count) {
        return str.equals(words) && (count == 0);
    }

    public ArrayList<Date> dataParser(String start, String end) throws ParseException {
        ArrayList<Date> datesOfVacation = new ArrayList<Date>();
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date startVacation = format.parse(start);
        Date endVacation = format.parse(end);
        datesOfVacation.add(startVacation);
        datesOfVacation.add(endVacation);
        return datesOfVacation;
    }

    public String stringCleaner(String str) {
        str = str.toLowerCase();
        str = str.replace(",", "");
        str = str.replace(".", "");
        str = str.replace(";", "");
        str = str.replace(":", "");

        return str;
    }

    public String[] getDate(String description) {
        int count = 0;
        String[] allMatches = new String[2];
        Matcher match =
                Pattern.compile("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d").matcher(description);
        while (match.find()) {
            allMatches[count] = match.group();
            count++;
        }
        return allMatches;
    }


    public void analyse() throws Exception {


        ArrayList<String> searchWords = new ArrayList<String>(
                Arrays.asList("ill", "illness", "disease", "sickness", "infection",
                        "disorder", "complaint", "ailment", "contagion"));


        Folder folder = readerAuthentication.readerConnection().getFolder("Inbox");
        if (!folder.isOpen()) {
            folder.open(Folder.READ_WRITE);
        }
        Message[] messages = folder.getMessages();

        for (Message msg : messages) {
            int vocationCount = 0;
            int illnessCount = 0;

            if (subjectAndSeenChecker(msg)) {

                String[] parts = msg.getSubject().split(" ");
                try {
                    for (String str : parts) {
                        str = stringCleaner(str);
                        if (numberOfWordsRepetition(str, "leave", vocationCount)) {
                            vocationCount += 1;
                            //msg.setFlag(Flags.Flag.SEEN, true);
                            String[] dates = getDate(msg.getSubject());
                            ArrayList<Date> parsedDates = dataParser(dates[0], dates[1]);
                            long diff = parsedDates.get(1).getTime() - parsedDates.get(0).getTime();
                            long diffDays = diff / (60 * 60 * 1000)/24;
                            if(diffDays <= 14){
                                WriterAuthentication writerAuthentication = new WriterAuthentication();
                                EventManager newEvent = new EventManager(writerAuthentication, msg);
                                newEvent.eventCreator(parsedDates.get(0), parsedDates.get(1), "Vacation");
                            } else{
                                System.out.println("Too long vacation: you don't deserve it");
                            }
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println("Bad Date Format");
                }

                for (String words : searchWords) {
                    for (String str : parts) {
                        str = stringCleaner(str);
                        if (numberOfWordsRepetition(str, words, illnessCount)) {
                            illnessCount += 1;
                            //msg.setFlag(Flags.Flag.SEEN, true);
                            WriterAuthentication writerAuthentication = new WriterAuthentication();
                            EventManager newEvent = new EventManager(writerAuthentication, msg);
                            newEvent.eventCreator(msg.getReceivedDate(), msg.getReceivedDate(), "Illness");
                        }
                    }
                }
            }
        }
    }
}

