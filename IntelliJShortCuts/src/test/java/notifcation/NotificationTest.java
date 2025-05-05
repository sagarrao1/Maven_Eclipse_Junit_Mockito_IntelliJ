package notifcation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
//    @Disabled
    void getMessage() {
        System.out.println("test message");
        assertTrue( 11>10);
    }

    @Test
    void setMessage() {
    }

    @Test
    void getTimestamp() {
    }

    @Test
    void setTimestamp() {
    }

    @Test
    void testNotificationMessage(){
        Notification noti = new Notification("Test message" , null);

        assertEquals(noti.getMessage(), "Test message" );

    }
}