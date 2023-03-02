package com.example.stardew_valley_service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stardew_valley_service.dto.SeasonSaveDto;
import com.example.stardew_valley_service.dto.SeasonUpdateDto;
import com.example.stardew_valley_service.model.Season;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SeasonDataUtil {
	
	private ObjectMapper mapper;
	
	@Autowired
	public SeasonDataUtil(ObjectMapper mapper) {
		super();
		this.mapper = mapper;
	}
	
	public Season saveDtoToSeason(SeasonSaveDto dto) {
		try {
			Season season = mapper.readValue(mapper.writeValueAsString(dto), Season.class);
			return season;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public Season dtoToSeason(Season season, SeasonUpdateDto dto) {
		try {
			Season updatedSeason = mapper.readValue(mapper.writeValueAsString(dto), Season.class);
			season = mapper.readerForUpdating(season).readValue(mapper.writeValueAsString(updatedSeason));
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return season;
	}
	
}
