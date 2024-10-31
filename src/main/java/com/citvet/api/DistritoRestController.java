package com.citvet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.citvet.model.Distrito;
import com.citvet.repository.IDistritoRepository;

@RestController
@RequestMapping("/api/Distrito")
public class DistritoRestController {

    @Autowired
    private IDistritoRepository distritoRepo;

    @GetMapping(value = "findall", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Distrito>> findAll(){
        try {
            return new ResponseEntity<Iterable<Distrito>>(distritoRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<Distrito>>(HttpStatus.BAD_REQUEST);
        }
    }
}