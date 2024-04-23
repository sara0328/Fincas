package com.webdynamos.fincas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SolicitudDTO {
    private Long id_solicitud;
    private int duracion;
    private int estado;
    private Long id_arrendador;
    private Long id_arrendatario;
    private Long id_propiedad;
}
