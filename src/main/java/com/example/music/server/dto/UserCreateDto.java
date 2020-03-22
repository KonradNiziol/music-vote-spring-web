package com.example.music.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDto {

    @NotNull
    @Pattern(regexp = "[A-Za-z]+", message = "name is not correct")
    private String name;
    @NotNull
    @Pattern(regexp = "[A-Za-z]+", message = "surname is not correct")
    private String surname;
    @Min(value = 18, message = "user's age is too low")
    private Integer age;
}
