package com.ironhack.postservice.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ContentDto {
    @NotNull
    @Size(min = 20, max = 300, message = "Post size must be 20-300 characters")
    private String content;

    public ContentDto(){}

    public ContentDto(@NotNull @Size(min = 20, max = 300, message = "Post size must be 20-300 characters") String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
