package com.example.stardew_valley_service.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CropUpdateDto {

	private String _id;
	private String name;
	private String emoji;
	private Float price;
	private Set<String> seasonIds;
	private Boolean deleted;
	
}
