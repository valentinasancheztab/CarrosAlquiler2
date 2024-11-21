package com.carrosalquiler.carrosalquiler.models;

/**
 * Role es una enumeración que define los posibles roles para los usuarios en el sistema.
 * Los roles incluyen USER y ADMIN, los cuales determinan los permisos y niveles de acceso de los usuarios.
 */
public enum Role {
    USER,   // Usuario regular con permisos estándar
    ADMIN   // Administrador con permisos elevados
}

