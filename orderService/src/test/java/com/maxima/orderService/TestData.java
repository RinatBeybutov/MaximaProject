package com.maxima.orderService;

import com.maxima.orderService.dto.CategoryCreateDto;
import com.maxima.orderService.dto.CategoryDto;

import java.util.UUID;

public class TestData {
    public static final String testUUID="fcc49792-9c0b-49f7-9fce-5d9d631d042f";
    public static final String CATEGORY_NAME="Тестовая категория1";
    private static final CategoryCreateDto categoryCreateDto = new CategoryCreateDto("Тестовая категория");

    public static CategoryCreateDto getCategoryCreateDto(){
        return categoryCreateDto;
    }

    private static final CategoryDto categoryDto = new CategoryDto(4L, UUID.fromString("fcc49792-9c0b-49f7-9fce-5d9d631d042f"),"Чипсы");

    public static CategoryDto getCategoryDto(){
        return categoryDto;
    }
}
