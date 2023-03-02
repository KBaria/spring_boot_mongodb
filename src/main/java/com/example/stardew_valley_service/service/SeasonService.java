package com.example.stardew_valley_service.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stardew_valley_service.dto.SeasonUpdateDto;
import com.example.stardew_valley_service.model.Season;
import com.example.stardew_valley_service.repository.SeasonEmoji;
import com.example.stardew_valley_service.repository.SeasonRepository;
import com.example.stardew_valley_service.util.SeasonDataUtil;

@Service
public class SeasonService {

	private SeasonRepository seasonRepository;
	private SeasonDataUtil seasonDataUtil;
	
	@Autowired
	public SeasonService(SeasonRepository seasonRepository, SeasonDataUtil seasonDataUtil) {
		super();
		this.seasonRepository = seasonRepository;
		this.seasonDataUtil = seasonDataUtil;
	}
	
	public Season save(SeasonUpdateDto dto) {
		Season season = seasonDataUtil.dtoToSeason(new Season(), dto);
		season.set_id(dto.get_id() != null ? dto.get_id() : season.get_id());
		season.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
		
		return seasonRepository.save(season);
	}
	
	public Season findById(String _id) {
		return seasonRepository.findById(_id).orElseThrow(() -> new IllegalArgumentException("season not found!"));
	}
	
	public List<Season> findAll() {
		return seasonRepository.findAll();
	}
	
	public Season updateSeason(String _id, SeasonUpdateDto dto) {
		Season season = seasonDataUtil.dtoToSeason(findById(_id), dto);
		season.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
		
		return seasonRepository.save(season); 
	}
	
	public void deleteSeason(String _id) {
		Season season = findById(_id);
		season.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
		
		season.setDeleted(true);
		
		seasonRepository.save(season);
	}
	
	public void hardDelete() {
		List<String> deletedSeasons = findAll().stream()
				.filter(season -> season.getDeleted())
				.map(season -> season.get_id())
				.toList();
		
		seasonRepository.deleteAllById(deletedSeasons);
	}
	
	public List<SeasonEmoji> findAllBy() {
		return seasonRepository.findSeasonEmojiBy();
	}
	
}
