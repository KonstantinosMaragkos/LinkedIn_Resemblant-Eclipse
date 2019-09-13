package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the friends database table.
 * 
 */
@Entity
@Table(name="friends")
@NamedQuery(name="Friend.findAll", query="SELECT f FROM Friend f")
public class Friend implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int relationship_id;

	private int status;
	
	private int user_id;
	
	private int friend_id;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="user_id")
	private transient User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="friend_id", referencedColumnName="user_id")
	private transient User user2;

	public Friend() {
	}

	public int getRelationship_id() {
		return this.relationship_id;
	}

	public void setRelationship_id(int relationship_id) {
		this.relationship_id = relationship_id;
	}

	public int getStatus() {
		return this.status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	public void setUser_id(int u_id) {
		this.user_id = u_id;
	}
	
	public int getUser_id() {
		return this.user_id;
	}

	public void setFriend_id(int f_id) {
		this.friend_id = f_id;
	}
	
	public int getFriend_id() {
		return this.friend_id;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}