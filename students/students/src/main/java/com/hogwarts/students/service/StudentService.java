package com.hogwarts.students.service;

import com.hogwarts.students.dto.StudentDTO;
import com.hogwarts.students.entities.Student;
import com.hogwarts.students.repository.StudentRepository;
import com.hogwarts.students.service.exceptions.DatabaseException;
import com.hogwarts.students.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Transactional(readOnly = true)
    public StudentDTO findById(Long id) {
        Student student = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar o aluno!"));
        return new StudentDTO(student);
    }

    public List<StudentDTO> findByHouse(String house) {
        List<Student> students = repository.findByHouseLetter(house);
        if (students.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum aluno encontrado para a casa: " + house);
        }
        return students.stream().map(student -> new StudentDTO(student)).collect(Collectors.toList());
    }

    public List<StudentDTO> findByAge(Integer age) {
        List<Student> students = repository.findByAge(age);
        return students.stream().map(student -> new StudentDTO(student)).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public Page<StudentDTO> findAll(Pageable pageable) {
        Page<Student> result = repository.findAll(pageable);
        return result.map(x -> new StudentDTO(x));
    }


    @Transactional
    public StudentDTO insert(StudentDTO dto) {
        Student entity = new Student();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new StudentDTO(entity);
    }

    @Transactional
    public StudentDTO update(Long id, StudentDTO dto) {
        try {
            Student entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new StudentDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Não foi possível encontrar o aluno!");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("ID não consta no banco de dados de Hogwarts!");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não foi possível deletar o cadastro!");
        }
    }

    private void copyDtoToEntity(StudentDTO dto, Student entity) {
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setBirthDate(dto.getBirthDate());
        entity.setHouse(dto.getHouse());
        entity.setMagic(dto.getMagic());
        entity.setSubscriptionDate(dto.getSubscriptionDate());
    }
}
