package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the active database table.
 * 
 */
@Entity
@NamedQuery(name="Active.findAll", query="SELECT a FROM Active a")
public class Active implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idActive;

	private int user_id;

	private int chat_id;

	public Active() {
	}

	public int getIdActive() {
		return this.idActive;
	}

	public void setIdActive(int idActive) {
		this.idActive = idActive;
	}

	public int getUser_id() {
		return this.user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getChat() {
		return this.chat_id;
	}

	public void setChat(int chat_id) {
		this.chat_id = chat_id;
	}

}