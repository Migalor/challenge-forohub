package com.forohub.controller;

import com.forohub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository repository;

    public TopicoController(TopicoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> crear(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriBuilder) {
        if (repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            return ResponseEntity.status(409).body("Tópico duplicado (título + mensaje)");
        }
        Topico t = new Topico();
        t.setTitulo(datos.titulo());
        t.setMensaje(datos.mensaje());
        t.setAutor(datos.autor());
        t.setCurso(datos.curso());
        repository.save(t);
        URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(t.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosDetalleTopico(t));
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopico>> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<DatosDetalleTopico> page = repository.findAll(pageable).map(DatosDetalleTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        return repository.findById(id)
                .<ResponseEntity<?>>map(t -> ResponseEntity.ok(new DatosDetalleTopico(t)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos) {
        return repository.findById(id)
                .map(t -> {
                    if (!t.getTitulo().equals(datos.titulo()) || !t.getMensaje().equals(datos.mensaje())) {
                        if (repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
                            return ResponseEntity.status(409).body("Tópico duplicado (título + mensaje)");
                        }
                    }
                    t.setTitulo(datos.titulo());
                    t.setMensaje(datos.mensaje());
                    t.setAutor(datos.autor());
                    t.setCurso(datos.curso());
                    return ResponseEntity.ok(new DatosDetalleTopico(t));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
