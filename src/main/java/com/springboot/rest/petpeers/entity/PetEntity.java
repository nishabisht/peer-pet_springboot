package com.springboot.rest.petpeers.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(of="petId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name="pets")
public class PetEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long petId;

    @NotBlank(message = "Mandatory Field")
    private String petName;

    @Min(value = 0,message = "pet age can not be then than 0")
    @Max(value = 99,message = "page age can not be greater then 99")
    @NotNull(message = "Mandatory Field")
    @PositiveOrZero
    private Integer petAge;

    @NotBlank(message = "Mandatory Field")
    private String petPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_uid")
    @JsonIgnore
    private UserEntity user;

}

