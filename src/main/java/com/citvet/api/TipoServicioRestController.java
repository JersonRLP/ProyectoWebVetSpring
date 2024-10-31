package com.citvet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.citvet.model.TipoServicio;
import com.citvet.repository.ITipoServicioRepository;

@RestController
@RequestMapping("/api/TipoServicio")
public class TipoServicioRestController {

    @Autowired
    private ITipoServicioRepository tipoServicioRepo;

    @GetMapping(value = "findall", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<TipoServicio>> findAll(){
        try {
            return new ResponseEntity<Iterable<TipoServicio>>(tipoServicioRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<TipoServicio>>(HttpStatus.BAD_REQUEST);
        }
    }
}
