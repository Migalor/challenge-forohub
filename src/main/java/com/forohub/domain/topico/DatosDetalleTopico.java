package com.forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        String curso,
        LocalDateTime fechaCreacion,
        String status
) {
    public DatosDetalleTopico(Topico t) {
        this(t.getId(), t.getTitulo(), t.getMensaje(), t.getAutor(), t.getCurso(), t.getFechaCreacion(), t.getStatus());
    }
}
