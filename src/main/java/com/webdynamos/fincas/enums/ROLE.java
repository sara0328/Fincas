package com.webdynamos.fincas.enums;

import org.springframework.security.core.GrantedAuthority;

public enum ROLE implements GrantedAuthority{
    ADMIN,
    ARRENDADOR,
    ARRENDATARIO;

    @Override
    public String getAuthority(){
        return this.name();
    }
}
