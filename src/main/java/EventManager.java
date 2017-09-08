import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import javax.mail.Message;


public class EventManager {

    public WriterAuthentication writerAuthentication;
    public Message msg;

    public EventManager(WriterAuthentication writerAuthentication, Message msg) {
        this.writerAuthentication = writerAuthentication;
        this.msg = msg;
    }



    public void eventCreator() throws Exception {
        com.google.api.services.calendar.Calendar service =
                writerAuthentication.getCalendarService();
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

        String calendarId = "primary";
        event = service.events().insert(calendarId, event).execute();
        System.out.printf("Event created: %s\n", event.getHtmlLink());
    }

}
