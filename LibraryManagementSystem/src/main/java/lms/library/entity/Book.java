package lms.library.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name="book")
public class Book {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @Column(name="title")
	 private String title;
	 
	 @Column(name="author")
	 private String author;
	 
	 @Column(name="bookPrice")
	 private BigDecimal bookPrice;
	 
	 @Column(name="borrowed")
	 private boolean borrowed;
	 
	 @ManyToOne
	 @JoinColumn(name = "user_id")
	 private User borrowedBy;
	 
	 @Temporal(TemporalType.DATE)
	 @Column(name = "borrowed_date")
	 private Date borrowedDate; // This field represents the borrowed date

	 
	 public Book() {
		 
	 }
	
	public Book(Long id, String title, String author, BigDecimal bookPrice, boolean borrowed, User borrowedBy,
			Date borrowedDate) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.bookPrice = bookPrice;
		this.borrowed = borrowed;
		this.borrowedBy = borrowedBy;
		this.borrowedDate = borrowedDate;
	}

	
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

	
	public BigDecimal getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(BigDecimal bookPrice) {
		this.bookPrice = bookPrice;
	}

	
	public boolean isBorrowed() {
		return borrowed;
	}

	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}

	
	public User getBorrowedBy() {
		return borrowedBy;
	}

	public void setBorrowedBy(User borrowedBy) {
		this.borrowedBy = borrowedBy;
	}


	public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }


	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", bookPrice=" + bookPrice + ", borrowed="
				+ borrowed + ", borrowedBy=" + borrowedBy + ", borrowedDate=" + borrowedDate + "]";
	}


}