package com.example.stardew_valley_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeasonUpdateDto {

	private String _id;
	private String name;
	private String emoji;
	private LocalDateTime updatedAt;
	private Boolean deleted;
	
}
