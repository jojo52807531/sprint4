package com.youssef.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Singer {
	public Singer(String name, double featureprice, Date dateBirth) {
		super();
		this.name = name;
		this.Featureprice = featureprice;
		this.dateBirth = dateBirth;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long IdSinger;
@Size(min = 2,max = 7)
private String name;
@Min(100)
private double Featureprice;
@PastOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd") // Specify expected date format
	private Date dateBirth;
@ManyToOne
private Type Type;






}




