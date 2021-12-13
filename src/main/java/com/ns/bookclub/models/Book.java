package com.ns.bookclub.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=2, message="Title must be at least 2 characters long!")
	private String title;
	@NotNull
	@Size(min=2, message="Author must be at least 2 characters long!")
	private String author;
	
	@NotNull
	@Size(min=5, message="Your thoughts must be at least 5 characters long!")
	private String thoughts;
	
	
	// ====================== Related Data - 1:n ==================================================
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user")
	private User user;
	
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;


	@PrePersist
	protected void onCreate(){
	    this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
	    this.updatedAt = new Date();
	}
	
	//========================= Constructors ======================================
	
	public Book() {}
	
	public Book(@NotNull @Size(min = 2, message = "Title must be at least 2 characters long!") String title,
			@NotNull @Size(min = 2, message = "Author must be at least 2 characters long!") String author,
			@NotNull @Size(min = 5, message = "Your thoughts must be at least 5 characters long!") String thoughts,
			User user) {
		this.title = title;
		this.author = author;
		this.thoughts = thoughts;
		this.user = user;
	}
	// ============================== Getter and Setter =======================================
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getThoughts() {
		return thoughts;
	}
	public void setThoughts(String thoughts) {
		this.thoughts = thoughts;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
