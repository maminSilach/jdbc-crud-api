package com.example.crudjdbc.dao;

import com.example.crudjdbc.repository.StudentsEntity;
import com.example.crudjdbc.utils.DBUtils;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class StudentsDAO {
    private String GET_ALL_STUDENTS = "SELECT * FROM students";
    private String DELETE_STUDENTS = "DELETE FROM students where id = ?";
    private String FETCH_STUDENTS = "SELECT * FROM students WHERE id = ?";
    private String UPDATE_STUDENT = "UPDATE students SET course = ? WHERE id = ?";
    private String CREATE_STUDENTS = "INSERT INTO students(name,surname,course) VALUES(?,?,?)";






    public List<StudentsEntity> getAllStudentsList(){
        List<StudentsEntity> students = new ArrayList<>();
       try(Connection connection = DBUtils.getConnection()){
           PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_STUDENTS);
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()){
               Long id = resultSet.getLong("id");
               String name = resultSet.getString("name");
               String surname = resultSet.getString("surname");
               Integer course = resultSet.getInt("course");
               students.add( new StudentsEntity(id,name,surname,course));
           }
       } catch (SQLException e) {
           e.printStackTrace();
           throw new RuntimeException(e);
       }
        return students;
    }



    public String createStudent(StudentsEntity students){
        try(Connection connection = DBUtils.getConnection()){
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_STUDENTS,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,students.getName());
        preparedStatement.setString(2,students.getSurname());
        preparedStatement.setInt(3,students.getCourse());
        preparedStatement.executeUpdate();
        String key = null;
         ResultSet resultSet = preparedStatement.getGeneratedKeys();
         while (resultSet.next()){
             key = resultSet.getString(1);
         }
           return "Student with id " + key + " has been added";
    } catch (SQLException e) {
        e.printStackTrace();
            throw new RuntimeException(e);
    }
    }


    public boolean deleteStudent(Long id){
      Optional<StudentsEntity> optionalStudents = Optional.ofNullable(fetchStudent(id));
        if(optionalStudents.isEmpty()){
            return false;
        }
        try(Connection connection = DBUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENTS);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String updateStudent(Long id,Integer course){
        Optional<StudentsEntity> optionalStudents = Optional.ofNullable(fetchStudent(id));
        if(optionalStudents.isPresent()) {
            try (Connection connection = DBUtils.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT);
                preparedStatement.setInt(1, course);
                preparedStatement.setLong(2, id);
                return "" + preparedStatement.executeUpdate() + " row(s) updated";
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return "Something went wrong";
    }




    public StudentsEntity fetchStudent(Long id){
        List<StudentsEntity> students = new ArrayList<>();
        try(Connection connection = DBUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FETCH_STUDENTS);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Students with id " + id + " not found");
                return null;
            } else {
                students.add(new StudentsEntity(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("course")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
          throw new RuntimeException();
        }
        return students.get(0);
    }


}
