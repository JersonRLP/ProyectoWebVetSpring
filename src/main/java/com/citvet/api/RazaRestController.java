package com.citvet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.citvet.model.Raza;
import com.citvet.repository.IRazaRepository;

@RestController
@RequestMapping("/api/Raza")
public class RazaRestController {

    @Autowired
    private IRazaRepository razaRepo;

    @GetMapping(value = "findall", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Raza>> findAll(){
        try {
            return new ResponseEntity<Iterable<Raza>>(razaRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<Raza>>(HttpStatus.BAD_REQUEST);
        }
    }
}
