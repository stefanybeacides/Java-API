package com.hogwarts.students.service;

import com.hogwarts.students.dto.StudentDTO;
import com.hogwarts.students.dto.ZipCodeDTO;
import com.hogwarts.students.entities.Student;
import com.hogwarts.students.repository.StudentRepository;
import com.hogwarts.students.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Service
public class ZipCodeService {

    @Autowired
    private StudentRepository repository;


    public ZipCodeDTO findByZipCode(String zipcode) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://viacep.com.br/ws/"+zipcode+"/json/";
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        try {
            var variavel = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ZipCodeDTO.class);
            return variavel.getBody();
        } catch (Exception e) {
            throw new Exception("Erro ao executar!" + e);
        }
    }
}
