package com.enviro.grad001.mduduzijelemduduzi.controller;

import com.enviro.grad001.mduduzijelemduduzi.dto.payload.request.GuidelineRequestDto;
import com.enviro.grad001.mduduzijelemduduzi.dto.payload.response.GuidelineResponseDto;
import com.enviro.grad001.mduduzijelemduduzi.service.GuidelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guidelines")
public class GuidelineController {

    private final GuidelineService guidelineService;

    @Autowired
    public GuidelineController(GuidelineService guidelineService){
        this.guidelineService = guidelineService;
    }

    @GetMapping
    public ResponseEntity<List<GuidelineResponseDto>> getAllGuidelines(){
        List<GuidelineResponseDto> guidelines = guidelineService.getAllGuidelines();
        return ResponseEntity.ok(guidelines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuidelineResponseDto> getGuidelineById(@PathVariable Long id){
        GuidelineResponseDto guidelineResponseDto = guidelineService.getGuidelineById(id);
        return ResponseEntity.ok(guidelineResponseDto);
    }

    @PostMapping
    public ResponseEntity<GuidelineResponseDto> createGuideline(@RequestBody GuidelineRequestDto guidelineDetails){
        GuidelineResponseDto createdGuideline = guidelineService.createGuideline(guidelineDetails);
        return ResponseEntity.ok(createdGuideline);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuidelineResponseDto> updateGuideline(@PathVariable Long id, @RequestBody GuidelineRequestDto guidelineDetails){
        GuidelineResponseDto updateGuideline = guidelineService.updateGuideline(id, guidelineDetails);
        return ResponseEntity.ok(updateGuideline);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuideline(@PathVariable Long id){
        guidelineService.deleteGuideline(id);
        return ResponseEntity.noContent().build();
    }
}
