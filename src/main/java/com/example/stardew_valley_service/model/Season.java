package com.example.stardew_valley_service.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("seasons")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Season {

	@Id
	private String _id;
	
	@NotBlank(message = "name cannot be blank or null")
	private String name;
	
	@NotBlank(message = "name cannot be blank or null")
	private String emoji;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@Builder.Default
	private Boolean deleted = false;
	
}
