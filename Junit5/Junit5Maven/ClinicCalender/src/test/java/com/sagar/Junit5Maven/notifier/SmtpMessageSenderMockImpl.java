package com.sagar.Junit5Maven.notifier;

import java.util.ArrayList;
import java.util.List;

public class SmtpMessageSenderMockImpl implements EmailNotifier {

	public List<Message> recievedMessages= new ArrayList<>();
	
	@Override
	public void sendNotification(String subject, String body, String address) {
		recievedMessages.add(new Message(subject, body, address));
	}
	
	class Message{
		String subject;
		String body;
		String address;
		
		public Message(String subject, String body, String address) {
			this.subject = subject;
			this.body = body;
			this.address = address;
		}	
	}
	
//	public List<Message> getMessages(){
//		return recievedMessages;
//	}

}
