package com.citvet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.citvet.model.Especie;
import com.citvet.repository.IEspecieRepository;

@RestController
@RequestMapping("/api/Especie")
public class EspecieRestController {

    @Autowired
    private IEspecieRepository especieRepo;

    @GetMapping(value = "findall", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Especie>> findAll(){
        try {
            return new ResponseEntity<Iterable<Especie>>(especieRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<Especie>>(HttpStatus.BAD_REQUEST);
        }
    }
}
