package com.example.stardew_valley_service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.stardew_valley_service.model.Season;

@Repository
public interface SeasonRepository extends MongoRepository<Season, String> {

	@Query(fields = "{'emoji': 1}")
	List<SeasonEmoji> findSeasonEmojiBy();
	
}
