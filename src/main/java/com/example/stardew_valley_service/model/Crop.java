package com.example.stardew_valley_service.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("crops")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Crop {

	@Id
	private String _id;
	
	@NotBlank(message = "name cannot be blank or null")
	private String name;
	
	@NotBlank(message = "emoji cannot be blank or null")
	private String emoji;
	
	@DecimalMin(value = "0.00", message = "price cannot be below 0.0")
	@NotNull(message = "price cannot be null")
	private Float price;
	
	@DBRef(lazy = true)
	@NotEmpty(message = "must contain atleast one season Id")
	@NotNull(message = "list of season Ids cannot be null")
	private List<Season> seasons;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@Builder.Default
	private Boolean deleted = false;
	
}
