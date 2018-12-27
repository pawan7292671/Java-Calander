package com.example.demo;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CalendarQuickstart {
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = CalendarQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8080).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static void main(String... args) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        /*//Creating Recurring Event
        Event event = new Event();

        event.setSummary("Appointment");
        event.setLocation("Somewhere");

        EventAttendee[] attendees = new EventAttendee[] {
        	    new EventAttendee().setEmail("pawan@connectivitysolutions.in"),
        	    new EventAttendee().setEmail("pawankumarec62@gmail.com"),
        	};

        DateTime start = DateTime.parseRfc3339("2018-12-25T20:00:00+05:30");
        DateTime end = DateTime.parseRfc3339("2019-01-01T20:00:00+05:30");
        event.setStart(new EventDateTime().setDateTime(start).setTimeZone("Asia/Kolkata"));
        event.setEnd(new EventDateTime().setDateTime(end).setTimeZone("Asia/Kolkata"));
        event.setRecurrence(Arrays.asList("RRULE:FREQ=WEEKLY;UNTIL=20110701T170000Z"));

        Event recurringEvent = service.events().insert("primary", event).execute();
        System.out.println(recurringEvent.getId());*/
        
        
        
    /*    //Modifying or deleating Recurring Events
     // First retrieve the instances from the API.
        Events instances = service.events().instances("primary", "t4v3upot0t3ggmo0j0uscakiu8").execute();

        // Select the instance to cancel.
        Event instance = instances.getItems().get(0);
        instance.setStatus("cancelled");

        Event updatedInstance = service.events().update("primary", instance.getId(), instance).execute();

        // Print the updated date.
        System.out.println(updatedInstance.getUpdated());*/
        
        
        
        
     
        //For Creating an Event
        /*	Event event = new Event()
        	    .setSummary("Appointment")
        	    .setLocation("India Bangalore")
        	    .setDescription("create event");

        	DateTime startDateTime = new DateTime("2018-12-20T10:00:00+05:30");
        	EventDateTime start = new EventDateTime()
        	    .setDateTime(startDateTime)
        	    .setTimeZone("Asia/Kolkata");
        	event.setStart(start);

        	DateTime endDateTime = new DateTime("2018-12-21T20:00:00+05:30");
        	EventDateTime end = new EventDateTime()
        	    .setDateTime(endDateTime)
        	    .setTimeZone("Asia/Kolkata");
        	event.setEnd(end);

        	String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
        	event.setRecurrence(Arrays.asList(recurrence));

        	EventAttendee[] attendees = new EventAttendee[] {
        	    new EventAttendee().setEmail("pawan@connectivitysolutions.in"),
        	    new EventAttendee().setEmail("pawankumarec62@gmail.com"),
        	};
        	event.setAttendees(Arrays.asList(attendees));

        	EventReminder[] reminderOverrides = new EventReminder[] {
        	    new EventReminder().setMethod("email").setMinutes(24 * 60),
        	    new EventReminder().setMethod("popup").setMinutes(10),
        	};
        	Event.Reminders reminders = new Event.Reminders()
        	    .setUseDefault(false)
        	    .setOverrides(Arrays.asList(reminderOverrides));
        	event.setReminders(reminders);

        	String calendarId = "primary";
        	event = service.events().insert(calendarId, event).execute();
        	System.out.printf("Event created: %s\n", event.getHtmlLink());
        	*/
        	
        /*// List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }*/
        
        
           
          /* String pageToken = null;
           do {
             com.google.api.services.calendar.model.CalendarList calendarList = service.calendarList().list().setPageToken(pageToken).execute();
             List<CalendarListEntry> items = calendarList.getItems();

             for (CalendarListEntry calendarListEntry : items) {
   	        System.out.println("Kind: " + calendarListEntry.getKind());
   	        System.out.println("ETag: " + calendarListEntry.getEtag());
           	System.out.println("ID: " + calendarListEntry.getId());
               System.out.println("Summary: " + calendarListEntry.getSummary());
               System.out.println("TimeZone: " + calendarListEntry.getTimeZone());
               System.out.println("ColorId: " + calendarListEntry.getColorId());
               System.out.println("BackgroundColoe: " + calendarListEntry.getBackgroundColor());
               System.out.println("ForegroundColor: " + calendarListEntry.getForegroundColor());
               System.out.println("Selected:" + calendarListEntry.getSelected());
               System.out.println("Role: " + calendarListEntry.getAccessRole());
               System.out.println("Reminders: " + calendarListEntry.getDefaultReminders());
               System.out.println("NotificationSettings: " + calendarListEntry.getNotificationSettings());
               System.out.println();
             }
             pageToken = calendarList.getNextPageToken();
           } while (pageToken != null);*/
    }
}
