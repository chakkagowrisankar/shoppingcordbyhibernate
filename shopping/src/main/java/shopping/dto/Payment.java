package shopping.dto;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String  mode;
	private double bill;
	private LocalDateTime time=LocalDateTime.now();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public double getBill() {
		return bill;
	}
	public void setBill(double bill) {
		this.bill = bill;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public Payment(String mode, double bill) {
		super();
		this.mode = mode;
		this.bill = bill;
	}
	public Payment() {
		super();
	}
	@Override
	public String toString() {
		return "Payment [id=" + id + ", mode=" + mode + ", bill=" + bill + ", time=" + time + "]";
	}
	
	
	

}
