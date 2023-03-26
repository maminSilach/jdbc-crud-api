package com.example.crudjdbc.controller;

import com.example.crudjdbc.dao.StudentsDAO;
import com.example.crudjdbc.dto.StudentsDto;
import com.example.crudjdbc.factory.FactoryStudents;
import com.example.crudjdbc.repository.StudentsEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {
    private final static String CREATE_STUDENT= "/student";
    private final static String DELETE_STUDENT = "/student/{id}";
    private final static String UPDATE_STUDENT= "/student/{id}";
    private final static String GET_STUDENT = "/student";

    StudentsDAO studentsDAO;
    FactoryStudents factoryStudents;


    public Controller(FactoryStudents factoryStudents, StudentsDAO studentsDAO) {
        this.factoryStudents = factoryStudents;
        this.studentsDAO = studentsDAO;
    }


    @GetMapping(GET_STUDENT)
    public List<StudentsDto> getStudent(){
        List<StudentsEntity> students = studentsDAO.getAllStudentsList();
        return students.stream().map(factoryStudents::getStudent).collect(Collectors.toList());
    }

    @PostMapping(CREATE_STUDENT)
    public String createStudent(@RequestBody() StudentsEntity student){
        return  studentsDAO.createStudent(student);
    }

    @DeleteMapping(DELETE_STUDENT)
    public boolean deleteStudent(@PathVariable("id") Long id){
        return studentsDAO.deleteStudent(id);
    }

    @PatchMapping(UPDATE_STUDENT)
    public String updateStudent(@PathVariable("id") Long id,@RequestParam(name = "course") Integer course){
       return studentsDAO.updateStudent(id,course);
    }



}
