package com.example.stardew_valley_service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.stardew_valley_service.model.Crop;

@Repository
public interface CropRepository extends MongoRepository<Crop, String> {
	
	@Query(value = "{}", fields = "{'seasons': 0}")
	public List<JustCrops> findJustCropsBy();
	
}
