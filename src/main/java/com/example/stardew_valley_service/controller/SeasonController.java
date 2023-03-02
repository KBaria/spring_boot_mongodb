package com.example.stardew_valley_service.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stardew_valley_service.dto.SeasonUpdateDto;
import com.example.stardew_valley_service.model.Season;
import com.example.stardew_valley_service.repository.SeasonEmoji;
import com.example.stardew_valley_service.service.SeasonService;

@RestController
@RequestMapping("seasons")
public class SeasonController {

	private SeasonService seasonService;

	@Autowired
	public SeasonController(SeasonService seasonService) {
		super();
		this.seasonService = seasonService;
	}
	
	@PostMapping()
	public ResponseEntity<Season> save(@RequestBody SeasonUpdateDto dto) {
		return new ResponseEntity<Season>(seasonService.save(dto), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Season> findById(@PathVariable("id") String _id) {
		return ResponseEntity.ok(seasonService.findById(_id));
	}
	
	@GetMapping()
	public ResponseEntity<List<Season>> findAll() {
		return ResponseEntity.ok(seasonService.findAll());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Season> updateSeason(@Valid @PathVariable("id") String _id, @RequestBody SeasonUpdateDto dto) {
		return new ResponseEntity<Season>(seasonService.updateSeason(_id, dto), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSeason(@PathVariable("id") String _id) {
		seasonService.deleteSeason(_id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping()
	public ResponseEntity<Void> deleteSeasons() {
		seasonService.hardDelete();
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("emoji")
	public ResponseEntity<List<SeasonEmoji>> findAllBy() {
		return ResponseEntity.ok(seasonService.findAllBy());
	}
}
