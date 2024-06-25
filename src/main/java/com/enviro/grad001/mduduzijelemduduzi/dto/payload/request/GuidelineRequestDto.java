package com.enviro.grad001.mduduzijelemduduzi.dto.payload.request;

public class GuidelineRequestDto {

    private Long categoryId;

    private String message;

    public GuidelineRequestDto(Long categoryId, String message) {
        this.categoryId = categoryId;
        this.message = message;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
