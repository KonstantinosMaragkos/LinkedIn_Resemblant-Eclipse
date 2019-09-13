package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the chat_data database table.
 * 
 */
@Entity
@Table(name="chat_data")
@NamedQuery(name="ChatData.findAll", query="SELECT c FROM ChatData c")
public class ChatData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idChat_Data;

	private String date;

	@Column(name="sr_no")
	private int srNo;

	private String text;

	private String time;

	private int user_id;

	private int chat_id;

	public ChatData() {
	}

	public int getIdChat_Data() {
		return this.idChat_Data;
	}

	public void setIdChat_Data(int idChat_Data) {
		this.idChat_Data = idChat_Data;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getSrNo() {
		return this.srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getUser_id() {
		return this.user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public int getChat_id() {
		return this.chat_id;
	}

	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
	}

/*
 	//bi-directional many-to-one association to Chat
	@ManyToOne
	@JoinColumn(name="Chat_id")
	private Chat chat;
	public Chat getChat() {
		return this.chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}
*/
}