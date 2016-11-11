package bean;

import java.sql.Blob;

import org.hibernate.type.BlobType;


public class Message {
	private int id;
	private String command;
	private String description;
	private String content;
	private Blob picture;
	private User user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Blob getPicture() {
		return picture;
	}
	public void setPicture(Blob image) {
		this.picture = image;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
