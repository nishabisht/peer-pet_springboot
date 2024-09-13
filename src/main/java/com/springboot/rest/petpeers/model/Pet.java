package com.springboot.rest.petpeers.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pet {

    private Long id;

    @NotBlank(message = "Mandatory Field")
    private String name;

    @Min(value = 0,message = "pet age can not be then than 0")
    @Max(value = 99,message = "page age can not be greater then 99")
    @NotNull(message = "Mandatory Field")
    @PositiveOrZero
    private Integer age;

    @NotBlank(message = "Mandatory Field")
    private String place;

    //private User user;


}
