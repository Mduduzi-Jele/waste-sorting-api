package com.enviro.grad001.mduduzijelemduduzi.dto.payload.response;

public class TipResponseDto {

    private Long id;

    private String message;

    private Long categoryId;


    public TipResponseDto() {
    }

    public TipResponseDto(Long id, String message, Long categoryId) {
        this.id = id;
        this.message = message;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategory_id(Long categoryId) {
        this.categoryId = categoryId;
    }
}
