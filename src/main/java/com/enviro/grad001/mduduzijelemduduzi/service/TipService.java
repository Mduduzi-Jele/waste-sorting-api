package com.enviro.grad001.mduduzijelemduduzi.service;

import com.enviro.grad001.mduduzijelemduduzi.dto.payload.request.TipRequestDto;
import com.enviro.grad001.mduduzijelemduduzi.dto.payload.response.TipResponseDto;
import com.enviro.grad001.mduduzijelemduduzi.exception.GuidelineNotFoundException;
import com.enviro.grad001.mduduzijelemduduzi.exception.TipNotFoundException;
import com.enviro.grad001.mduduzijelemduduzi.model.Category;
import com.enviro.grad001.mduduzijelemduduzi.model.Tip;
import com.enviro.grad001.mduduzijelemduduzi.repository.TipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipService {

    private final TipRepository tipRepository;

    private final CategoryService categoryService;

    @Autowired
    public TipService(TipRepository tipRepository, CategoryService categoryService){
        this.tipRepository = tipRepository;
        this.categoryService = categoryService;
    }

    public List<TipResponseDto> getAllTips(){
        List<Tip> tips = tipRepository.findAll();
        return tips.stream().map(tip -> new TipResponseDto(tip.getId(), tip.getMessage(), tip.getCategory().getId()))
                .collect(Collectors.toList());
    }

    public TipResponseDto getTipById(Long id){
        Tip tip = tipRepository.findById(id)
                .orElseThrow(() -> new GuidelineNotFoundException("Tip with id " + id + " not found"));
        return new TipResponseDto(tip.getId(), tip.getMessage(), tip.getCategory().getId());
    }

    public TipResponseDto createTip(TipRequestDto tipDetails){
        if(tipDetails.getMessage() == null || tipDetails.getMessage().isEmpty()){
            throw new IllegalArgumentException("Tip message cannot be null or empty");
        }

        if(tipDetails.getCategoryId() == null){
            throw new IllegalArgumentException("Category id message cannot be null or empty");
        }
        Category category = categoryService.getCategoryById(tipDetails.getCategoryId());
        Tip tip = new Tip(tipDetails.getMessage());
        tip.setCategory(category);
        Tip createdTip = tipRepository.save(tip);
        return new TipResponseDto(createdTip.getId(), createdTip.getMessage(), createdTip.getCategory().getId());
    }

    public TipResponseDto updateTip(Long id, TipRequestDto tipDetails){
        if(tipDetails.getMessage() == null || tipDetails.getMessage().isEmpty()){
            throw new IllegalArgumentException("Tip message cannot be null or empty");
        }

        Tip tip = tipRepository.findById(id)
                .orElseThrow(() -> new TipNotFoundException("Tip not found with id "+ id));
        tip.setMessage(tipDetails.getMessage());
        Tip updatedTip = tipRepository.save(tip);
        return new TipResponseDto(updatedTip.getId(), updatedTip.getMessage(), updatedTip.getCategory().getId());
    }

    public void deleteTip(Long id){
        if(!tipRepository.existsById(id)){
            throw new TipNotFoundException("Tip not found with id"+ id);
        }
        tipRepository.deleteById(id);
    }
}
