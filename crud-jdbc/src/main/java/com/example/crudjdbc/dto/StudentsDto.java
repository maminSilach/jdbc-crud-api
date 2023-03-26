package com.example.crudjdbc.dto;

public class StudentsDto {
    private Long id;
    private String name, surname;
    private Integer course;

    public StudentsDto(Long id, String name, String surname, Integer course) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.course = course;
    }

    public StudentsDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", course=" + course +
                '}';
    }

}
