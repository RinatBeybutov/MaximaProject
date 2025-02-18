package com.maxima.orderService;

import com.maxima.orderService.dto.CategoryCreateDto;
import com.maxima.orderService.dto.CategoryDto;

import java.util.UUID;

public class TestData {
    public static final String testUUID="fcc49792-9c0b-49f7-9fce-5d9d631d042f";
    public static final String testUUID2="fcc49792-9c0b-49f7-9fce-5d9d631d044f";
    public static final String CATEGORY_NAME="Тестовая категория1";
    private static final CategoryCreateDto categoryCreateDto = new CategoryCreateDto("Тестовая категория");

    public static CategoryCreateDto getCategoryCreateDto(){
        return categoryCreateDto;
    }

    private static final CategoryDto categoryDto = new CategoryDto(4L, UUID.fromString("fcc49792-9c0b-49f7-9fce-5d9d631d042f"),"Чипсы");

    public static CategoryDto getCategoryDto(){
        return categoryDto;
    }

    private static final CategoryDto categoryDtoForCreate = new CategoryDto(10L, UUID.fromString("1cc49792-9c0b-49f7-9fce-5d9d631d042f"),"Тестовая категория");

    public static CategoryDto getCategoryDtoForCreate(){
        return categoryDtoForCreate;
    }

    private static final CategoryDto categoryDtoForUpdate = new CategoryDto(10L, UUID.fromString("1cc49792-9c0b-49f7-9fce-5d9d631d042f"),CATEGORY_NAME);

    public static CategoryDto getCategoryDtoForUpdate(){
        return categoryDtoForUpdate;
    }

    private static final CategoryDto categoryDtoFirstInList = new CategoryDto(1L, UUID.fromString("fcc49792-9c0b-49f7-9fce-5d9d631d045f"),"Напитки");

    public static CategoryDto getCategoryDtoFirstInList(){
        return categoryDtoFirstInList;
    }
}
