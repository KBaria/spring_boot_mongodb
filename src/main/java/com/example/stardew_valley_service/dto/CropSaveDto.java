package com.example.stardew_valley_service.dto;

import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CropSaveDto {

	private String _id;
	
	@NotBlank(message = "name cannot be blank")
	private String name;
	
	@NotBlank(message = "emoji cannot be blank")
	private String emoji;
	
	@DecimalMin(value = "0.00", message = "price cannot be below 0.0")
	@NotNull(message = "price cannot be null")
	private Float price;
	
	@NotEmpty(message = "must contain atleast one season Id")
	@NotNull(message = "list of season Ids cannot be null")
	private List<String> seasonIds;
	
}
