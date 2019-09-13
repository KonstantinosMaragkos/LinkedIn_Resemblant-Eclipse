package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the chat database table.
 * 
 */
@Entity
@NamedQuery(name="Chat.findAll", query="SELECT c FROM Chat c")
public class Chat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idChat;

	private int status;

	private int user1;

	private int user2;

	public Chat() {
	}

	public int getIdChat() {
		return this.idChat;
	}

	public void setIdChat(int idChat) {
		this.idChat = idChat;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUser1() {
		return this.user1;
	}

	public void setUser1(int user1) {
		this.user1 = user1;
	}

	public int getUser2() {
		return this.user2;
	}

	public void setUser2(int user2) {
		this.user2 = user2;
	}

}