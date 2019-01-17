package com.notas.resfull.controller;

import com.notas.resfull.entity.Nota;
import com.notas.resfull.model.MNota;
import com.notas.resfull.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class NotaController {

    @Autowired
    @Qualifier("servicio")
    NotaService service;

    @PostMapping("/nota")
    public boolean agregarNota(@RequestBody @Valid Nota nota) {
        return  service.crear(nota);
    }

    @PutMapping("/nota")
    public boolean actualizarNota(@RequestBody @Valid Nota nota) {
        return  service.actualizar(nota);
    }

    @DeleteMapping("/nota/{id}/{nombre}")
    public boolean borrarNota(@PathVariable("id") long id ,
                              @PathVariable("nombre") String nombre) {
        return  service.borrar(nombre , id);
    }

    @GetMapping("/notas")
    public List<MNota> obtenerNotas(Pageable pageable){
        return service.obtenerPorPaginacion(pageable);
    }
}
