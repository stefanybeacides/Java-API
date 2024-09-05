package com.hogwarts.students.controller;

import com.hogwarts.students.dto.StudentDTO;
import com.hogwarts.students.dto.ZipCodeDTO;
import com.hogwarts.students.service.StudentService;
import com.hogwarts.students.service.ZipCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/students")
public class ZipCodeController {

    @Autowired
    private ZipCodeService service;

    /*@GetMapping(value = "/zipcode/{id}")
    public ResponseEntity<ZipCodeDTO> findById(@PathVariable Long id) {
        ZipCodeDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }*/

    @GetMapping(value = "/zipcode/{zipcode}")
    public ResponseEntity<ZipCodeDTO> findByZipCode(@PathVariable String zipcode) throws Exception {
        ZipCodeDTO dto = service.findByZipCode(zipcode);
        return ResponseEntity.ok(dto);
    }
}
