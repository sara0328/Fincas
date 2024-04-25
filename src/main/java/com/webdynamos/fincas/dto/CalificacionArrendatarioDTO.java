package com.webdynamos.fincas.dto;


import com.webdynamos.fincas.models.CalificacionArrendatario;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CalificacionArrendatarioDTO {
    private int calificacion;
    private Long id_arrendador;
    private Long id_arrendatario;

    public boolean isPresent() {
        return calificacion != 0;
        
    }

    public CalificacionArrendatario get() {
        CalificacionArrendatario calificacionArrendatario = new CalificacionArrendatario();
        calificacionArrendatario.setCalificacion(calificacion);
        return calificacionArrendatario;
    }
}
