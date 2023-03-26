package com.example.crudjdbc.factory;


import com.example.crudjdbc.dto.StudentsDto;
import com.example.crudjdbc.repository.StudentsEntity;
import org.springframework.stereotype.Component;

@Component("FactoryStudents")
public class FactoryStudents {


    public StudentsDto getStudent(StudentsEntity students){
        return new StudentsDto(students.getId(),
                students.getName(),
                students.getSurname(),
                students.getCourse());
    }
}
