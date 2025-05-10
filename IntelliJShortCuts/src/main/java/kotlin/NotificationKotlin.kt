package kotlin

import java.time.ZonedDateTime

class NotificationKotlin(//+" D";
        var message: String, var timestamp: ZonedDateTime) {
    override fun toString(): String {
        return "Notification{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}'
    }
}