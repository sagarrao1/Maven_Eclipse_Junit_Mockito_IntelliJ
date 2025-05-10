package kotlin

import java.time.ZonedDateTime

class MainKotlin
    fun main(){
        val event= NotificationKotlin("Notfication kotlin event", ZonedDateTime.now());
        print (event.message);
    }

