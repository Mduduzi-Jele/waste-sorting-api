package com.enviro.grad001.mduduzijelemduduzi.service;

import com.enviro.grad001.mduduzijelemduduzi.dto.payload.request.GuidelineRequestDto;
import com.enviro.grad001.mduduzijelemduduzi.dto.payload.response.GuidelineResponseDto;
import com.enviro.grad001.mduduzijelemduduzi.exception.GuidelineNotFoundException;
import com.enviro.grad001.mduduzijelemduduzi.model.Category;
import com.enviro.grad001.mduduzijelemduduzi.model.Guideline;
import com.enviro.grad001.mduduzijelemduduzi.repository.GuidelineRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuidelineService {

    private final GuidelineRepository guidelineRepository;

    private final CategoryService categoryService;

    @Autowired
    public GuidelineService(GuidelineRepository guidelineRepository, CategoryService categoryService){
        this.guidelineRepository = guidelineRepository;
        this.categoryService = categoryService;
    }

    public List<GuidelineResponseDto> getAllGuidelines(){
        List<Guideline> guidelines = guidelineRepository.findAll();
        return guidelines.stream().map(guideline -> new GuidelineResponseDto(guideline.getId(), guideline.getMessage(), guideline.getCategory().getId()))
                .collect(Collectors.toList());
    }

    public GuidelineResponseDto getGuidelineById(Long id){
        Guideline guideline = guidelineRepository.findById(id)
                .orElseThrow(() -> new GuidelineNotFoundException("Guideline with id " + id + " not found"));
        return new GuidelineResponseDto(guideline.getId(), guideline.getMessage(), guideline.getCategory().getId());
    }

    @Transactional
    public GuidelineResponseDto createGuideline(GuidelineRequestDto guidelineDetails){
        if(guidelineDetails.getMessage() == null || guidelineDetails.getMessage().isEmpty()){
            throw new IllegalArgumentException("Guideline message cannot be null or empty");
        }

        if(guidelineDetails.getCategoryId() == null){
            throw new IllegalArgumentException("Category id message cannot be null or empty");
        }

        Category category = categoryService.getCategoryById(guidelineDetails.getCategoryId());
        Guideline guideline = new Guideline(guidelineDetails.getMessage());
        guideline.setCategory(category);
        Guideline createdGuideline = guidelineRepository.save(guideline);
        return new GuidelineResponseDto(createdGuideline.getId(), createdGuideline.getMessage(), createdGuideline.getCategory().getId());
    }

    @Transactional
    public GuidelineResponseDto updateGuideline(Long id, GuidelineRequestDto guidelineDetails){
        if(guidelineDetails.getMessage() == null || guidelineDetails.getMessage().isEmpty()){
            throw new IllegalArgumentException("Guideline message cannot be null or empty");
        }

        Guideline guideline = guidelineRepository.findById(id)
                .orElseThrow(() -> new GuidelineNotFoundException("Guideline not found with id "+ id));
        guideline.setMessage(guidelineDetails.getMessage());
        Guideline updatedGuideline = guidelineRepository.save(guideline);
        return new GuidelineResponseDto(updatedGuideline.getId(), updatedGuideline.getMessage(), updatedGuideline.getCategory().getId());
    }

    public void deleteGuideline(Long id){
        if(!guidelineRepository.existsById(id)){
            throw new GuidelineNotFoundException("Guideline not found with id"+ id);
        }
        guidelineRepository.deleteById(id);
    }
}
