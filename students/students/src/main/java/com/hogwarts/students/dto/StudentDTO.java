package com.hogwarts.students.dto;

import com.hogwarts.students.entities.Student;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class StudentDTO {

    private Long id;
    @Size(min = 3, max = 100, message = "O nome do aluno deve ter entre 3 e 100 caracteres!")
    @NotBlank(message = "Este campo deve ser preenchido!")
    private String name;
    private Integer age;
    private LocalDate birthDate;
    private String house;
    private String magic;
    private LocalDate subscriptionDate;

    public StudentDTO(Long id, String name, Integer age, LocalDate birthDate, String house, String magic, LocalDate subscriptionDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
        this.house = house;
        this.magic = magic;
        this.subscriptionDate = subscriptionDate;
    }

    public StudentDTO(Student entity) {
        id = entity.getId();
        name = entity.getName();
        age = entity.getAge();
        birthDate = entity.getBirthDate();
        house = entity.getHouse();
        magic = entity.getMagic();
        subscriptionDate = entity.getSubscriptionDate();
    }

    public StudentDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getMagic() {
        return magic;
    }

    public void setMagic(String magic) {
        this.magic = magic;
    }

    public LocalDate getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDate subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
