package com.hogwarts.students.controller;

import com.hogwarts.students.dto.StudentDTO;
import com.hogwarts.students.service.StudentService;
import com.hogwarts.students.service.exceptions.InvalidHouseException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable Long id) {
        StudentDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/house/{house}")
    public ResponseEntity<List<StudentDTO>> findByHouse(@PathVariable String house) {
        if (house.matches("\\d+")) {
            throw new InvalidHouseException("Casa não encontrada. Por favor insira um nome de casa válido!");
        }
        List<StudentDTO> dtos = service.findByHouse(house);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(value = "/age/{age}")
    public ResponseEntity<List<StudentDTO>> findByAge(@PathVariable Integer age) {
        List<StudentDTO> dtos = service.findByAge(age);
        return ResponseEntity.ok(dtos);
    }


    @GetMapping
    public ResponseEntity <Page<StudentDTO>> findAll(Pageable pageable) {
        Page<StudentDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> insert(@Valid @RequestBody StudentDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable Long id, @RequestBody StudentDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
