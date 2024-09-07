package com.irctc.irctc.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "train")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Train {
	

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private String name;
		private String source;
		private String destination;
		private int totalSeats;
		private int availableSeats;
	

}
