package notifcation;

import kotlin.NotificationKotlin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        kotlin.NotificationKotlin noti = new NotificationKotlin("Test message" , null);

        assertEquals(noti.getMessage(), "Test message" );

    }
}