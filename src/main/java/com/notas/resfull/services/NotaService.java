package com.notas.resfull.services;

import com.notas.resfull.converter.Convertidor;
import com.notas.resfull.entity.Nota;
import com.notas.resfull.model.MNota;
import com.notas.resfull.repository.NotaRepositorio;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("servicio")
public class NotaService {
    @Autowired
    @Qualifier("repositorio")
    private NotaRepositorio repositorio;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    private static final Log logger = LogFactory.getLog(NotaService.class);

    public boolean crear(Nota nota) {
        logger.info("creando nota");
        try {
            repositorio.save(nota);
            logger.info("nota creada");
            return true;
        } catch (Exception e) {
            logger.error("hubo un error");
            return false;
        }
    }

    public boolean actualizar(Nota nota) {
        try {
            repositorio.save(nota);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean borrar(String nombre , long id) {
        try {
            Nota nota = repositorio.findByNombreAndId(nombre , id);
            repositorio.delete(nota);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<MNota> obtener() {
        return  convertidor.convertirLista(repositorio.findAll());
    }

    public MNota obtenerPorNombreTitulo(String nombre,String titulo) {
        return new MNota(repositorio.findByNombreAndTitulo(nombre , titulo));
    }

    public List<MNota> obtenerTitulo(String titulo) {
        return convertidor.convertirLista(repositorio.findByTitulo(titulo));
    }

    public List<MNota> obtenerPorPaginacion(Pageable pageable) {
        return convertidor.convertirLista(repositorio.findAll(pageable).getContent());
    }
}
