package com.example.stardew_valley_service.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stardew_valley_service.dto.CropUpdateDto;
import com.example.stardew_valley_service.model.Crop;
import com.example.stardew_valley_service.repository.JustCrops;
import com.example.stardew_valley_service.service.CropService;

@RestController
@RequestMapping("crops")
public class CropController {

	private CropService cropService;

	@Autowired
	public CropController(CropService cropService) {
		super();
		this.cropService = cropService;
	}
	
	@PostMapping()
	@Validated()
	public ResponseEntity<Crop> save(@Valid @RequestBody CropUpdateDto dto) {
		return new ResponseEntity<Crop>(cropService.save(dto), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Crop> findById(@PathVariable("id") String _id) {
		return ResponseEntity.ok(cropService.findById(_id));
	}
	
	@GetMapping()
	public ResponseEntity<List<Crop>> findAll() {
		return ResponseEntity.ok(cropService.findAll());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Crop> updateCrop(@Valid @PathVariable("id") String _id, @RequestBody CropUpdateDto dto) {
		return new ResponseEntity<Crop>(cropService.updateCrop(_id, dto), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCrop(@PathVariable("id") String _id) {
		cropService.deleteCrop(_id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping()
	public ResponseEntity<Void> deleteCrops() {
		cropService.hardDelete();
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("just")
	public ResponseEntity<List<JustCrops>> findJustCrops() {
		return ResponseEntity.ok(cropService.findJustCrops());
	}
	
}
