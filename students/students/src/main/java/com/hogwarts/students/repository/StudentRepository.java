package com.hogwarts.students.repository;

import com.hogwarts.students.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByHouse(String house);

    @Query("SELECT s FROM Student s WHERE LOWER(s.house) LIKE LOWER(CONCAT('%', :house, '%'))")
    List<Student> findByHouseLetter(@Param("house") String house);

    @Query("SELECT s FROM Student s WHERE s.age >= :age")
    List<Student> findByAge(@Param("age") Integer age);

}
