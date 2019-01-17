package com.notas.resfull.converter;

import com.notas.resfull.entity.Nota;
import com.notas.resfull.model.MNota;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("convertidor")
public class Convertidor {
    public List<MNota> convertirLista(List<Nota> notas) {
        List<MNota> mnotas = new ArrayList<>();
        for(Nota nota : notas) {
            mnotas.add(new MNota(nota));
        }
        return mnotas;
    }
}
