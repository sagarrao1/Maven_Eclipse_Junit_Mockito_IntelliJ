package com.sagar.Junit5Maven.notifier;

public interface EmailNotifier {

	void sendNotification(String subject, String body, String address);

}