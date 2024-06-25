package com.enviro.grad001.mduduzijelemduduzi.controller;

import com.enviro.grad001.mduduzijelemduduzi.dto.payload.request.TipRequestDto;
import com.enviro.grad001.mduduzijelemduduzi.dto.payload.response.TipResponseDto;
import com.enviro.grad001.mduduzijelemduduzi.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tips")
public class TipController {

    private final TipService tipService;

    @Autowired
    public TipController(TipService tipService){
        this.tipService = tipService;
    }

    @GetMapping
    public ResponseEntity<List<TipResponseDto>> getAllTips(){
        List<TipResponseDto> tips = tipService.getAllTips();
        return ResponseEntity.ok(tips);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipResponseDto> getTipById(@PathVariable Long id){
        TipResponseDto tipResponseDto = tipService.getTipById(id);
        return ResponseEntity.ok(tipResponseDto);
    }

    @PostMapping
    public ResponseEntity<TipResponseDto> createTip(@RequestBody TipRequestDto tipDetails){
        TipResponseDto createdTip = tipService.createTip(tipDetails);
        return ResponseEntity.ok(createdTip);
    }

   @PutMapping("/{id}")
   public ResponseEntity<TipResponseDto> updateTip(@PathVariable Long id, @RequestBody TipRequestDto tipDetails){
        TipResponseDto updatedTip = tipService.updateTip(id, tipDetails);
        return ResponseEntity.ok(updatedTip);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteTip(@PathVariable Long id){
        tipService.deleteTip(id);
        return ResponseEntity.noContent().build();
   }
}
