package com.example.crudjdbc.repository;

public class StudentsEntity {

    private Long id;
    private String name, surname;
    private Integer course;

    public StudentsEntity(Long id, String name, String surname, Integer course) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.course = course;
    }

    public StudentsEntity(String name, String surname, Integer course) {
        this.name = name;
        this.surname = surname;
        this.course = course;
    }

    public StudentsEntity() {
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


}
