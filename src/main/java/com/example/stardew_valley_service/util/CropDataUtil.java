package com.example.stardew_valley_service.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stardew_valley_service.dto.CropSaveDto;
import com.example.stardew_valley_service.dto.CropUpdateDto;
import com.example.stardew_valley_service.model.Crop;
import com.example.stardew_valley_service.model.Season;
import com.example.stardew_valley_service.repository.SeasonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CropDataUtil {

	private SeasonRepository seasonRepository;
	private ObjectMapper mapper;

	@Autowired
	public CropDataUtil(SeasonRepository seasonRepository, ObjectMapper mapper) {
		super();
		this.seasonRepository = seasonRepository;
		this.mapper = mapper;
	}
	
	public Crop saveDtoToCrop(CropSaveDto dto) {
		try {
			Crop crop = mapper.readValue(mapper.writeValueAsString(dto), Crop.class);
			crop.setSeasons(dto.getSeasonIds() != null && !dto.getSeasonIds().isEmpty() ? 
					(List<Season>) seasonRepository.findAllById(dto.getSeasonIds()) : crop.getSeasons());
			
			return crop;
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public Crop dtoToCrop(Crop crop, CropUpdateDto dto) {
		try {
			Crop updatedCrop = mapper.readValue(mapper.writeValueAsString(dto), Crop.class);
			updatedCrop.setSeasons(dto.getSeasonIds() != null && !dto.getSeasonIds().isEmpty() ? 
					(List<Season>) seasonRepository.findAllById(dto.getSeasonIds()) : crop.getSeasons());
			
			crop = mapper.readerForUpdating(crop).readValue(mapper.writeValueAsString(updatedCrop));
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return crop;
	}
	
}
