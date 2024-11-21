package com.carrosalquiler.carrosalquiler.models;

/**
 * La clase ResponseMessage representa un mensaje de respuesta estándar.
 * Contiene información sobre si una operación fue exitosa y un mensaje asociado.
 */
public class ResponseMessage {
    private boolean success;  // Variable de instancia para almacenar el estado de éxito de la operación
    private String message;  // Variable de instancia para almacenar el mensaje asociado con la operación

    /**
     * Constructor para inicializar un ResponseMessage.
     *
     * @param success indica si la operación fue exitosa
     * @param message el mensaje asociado con la operación
     */
    public ResponseMessage(boolean success, String message) {
        this.success = success;  // Inicializa el atributo success con el valor proporcionado
        this.message = message;  // Inicializa el atributo message con el mensaje proporcionado
    }

    /**
     * Obtiene el estado de éxito de la operación.
     *
     * @return true si la operación fue exitosa, false en caso contrario
     */
    public boolean isSuccess() {
        //issucces:es útil para obtener el estado de una operación (si fue exitosa o no) sin tener que acceder directamente al atributo success.
        // Devuelve el valor del atributo success, indicando si la operación fue exitosa
        return success;
    }

    /**
     * Obtiene el mensaje asociado con la operación.
     *
     * @return el mensaje como cadena de texto
     */
    public String getMessage() {
        // Devuelve el mensaje asociado con la operación
        return message;
    }
}

