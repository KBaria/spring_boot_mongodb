package com.example.stardew_valley_service.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stardew_valley_service.dto.CropUpdateDto;
import com.example.stardew_valley_service.exception.ResourceNotFoundException;
import com.example.stardew_valley_service.model.Crop;
import com.example.stardew_valley_service.repository.CropRepository;
import com.example.stardew_valley_service.repository.JustCrops;
import com.example.stardew_valley_service.util.CropDataUtil;

@Service
public class CropService {

	private CropRepository cropRepository;
	private CropDataUtil cropDataUtil;

	@Autowired
	public CropService(CropRepository cropRepository, CropDataUtil cropDataUtil) {
		super();
		this.cropRepository = cropRepository;
		this.cropDataUtil = cropDataUtil;
	}
	
	public Crop save(CropUpdateDto dto) {
		Crop crop = cropDataUtil.dtoToCrop(new Crop(), dto);
		crop.set_id(dto.get_id() != null ? dto.get_id() : crop.get_id());
		crop.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
		
		return cropRepository.save(crop);
	}

	public Crop findById(String _id) {
		return cropRepository.findById(_id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("crop with id: %s not found!", _id)));
	}
	
	public List<Crop> findAll() {
		return cropRepository.findAll();
	}
	
	public Crop updateCrop(String _id, CropUpdateDto dto) {
		Crop crop = cropDataUtil.dtoToCrop(findById(_id), dto);
		crop.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
		
		return cropRepository.save(crop);
	}
	
	public void deleteCrop(String _id) {
		Crop crop = findById(_id);
		crop.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
		
		crop.setDeleted(true);
		
		cropRepository.save(crop);
	}
	
	public void hardDelete() {
		List<String> deletedCrops = findAll().stream()
				.filter(crop -> crop.getDeleted())
				.map(crop -> crop.get_id())
				.toList();
		
		cropRepository.deleteAllById(deletedCrops);
	}
	
	public List<JustCrops> findJustCrops() {
		return cropRepository.findJustCropsBy();
	}
	
}
