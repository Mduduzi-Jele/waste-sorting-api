package com.enviro.grad001.mduduzijelemduduzi.dto.payload.response;

public class GuidelineResponseDto {

    private Long id;

    private String message;

    private Long category_id;

    public GuidelineResponseDto(Long id, String message, Long category_id) {
        this.id = id;
        this.message = message;
        this.category_id = category_id;
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

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }
}
