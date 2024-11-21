package com.carrosalquiler.carrosalquiler.models;

/**
 * Motorcycle es una subclase de AbstractVehicle que representa una motocicleta con atributos y comportamientos adicionales.
 * Esta clase incluye el estado del sidecar específico de una motocicleta e implementa los métodos abstractos de AbstractVehicle.
 */
public class Motorcycle extends AbstractVehicle {
    private boolean hasSidecar; // Variable de instancia para almacenar si la motocicleta tiene o no sidecar.

    /**
     * Constructor para inicializar un objeto Motorcycle.
     *
     * @param licensePlate la matrícula de la motocicleta
     * @param kilometers los kilómetros recorridos por la motocicleta
     * @param rentalValue el valor de alquiler de la motocicleta
     * @param isElectric si la motocicleta es eléctrica
     * @param hasSidecar si la motocicleta tiene un sidecar
     */
    public Motorcycle(String licensePlate, int kilometers, double rentalValue, boolean isElectric, boolean hasSidecar) {
        super(licensePlate, kilometers, rentalValue, isElectric); // Llama al constructor de la superclase AbstractVehicle para inicializar los atributos heredados.
        this.hasSidecar = hasSidecar; // Inicializa el atributo específico de la clase Motorcycle.
    }

    /**
     * Método getter para el estado del sidecar.
     *
     * @return true si la motocicleta tiene sidecar, false si no lo tiene
     */
    public boolean isHasSidecar() {
        // Devuelve el valor de la variable hasSidecar, indicando si la motocicleta tiene un sidecar o no.
        return hasSidecar;
    }

    /**
     * Método setter para el estado del sidecar.
     *
     * @param hasSidecar el nuevo estado del sidecar
     */
    public void setHasSidecar(boolean hasSidecar) {
        // Establece el valor del atributo hasSidecar.
        this.hasSidecar = hasSidecar;
    }

    /**
     * Realiza mantenimiento en la motocicleta.
     * Imprime un mensaje indicando que se está realizando el mantenimiento en la motocicleta.
     */
    @Override
    public void performMaintenance() {
        // Imprime un mensaje que indica que se está realizando el mantenimiento en la motocicleta con la matrícula proporcionada.
        System.out.println("Performing maintenance on the motorcycle with license plate: " + getLicensePlate());
    }

    /**
     * Calcula el alquiler para la motocicleta.
     * El alquiler se calcula según el número de días y el valor del alquiler.
     *
     * @param days el número de días por los cuales la motocicleta es alquilada
     * @return el alquiler calculado
     */
    @Override
    public double calculateRent(int days) {
        // Calcula el alquiler multiplicando el número de días por el valor del alquiler de la motocicleta.
        return days * getRentalValue();
    }

    /**
     * Devuelve una representación en cadena de la Motorcycle.
     *
     * @return una cadena que contiene los detalles de la motocicleta
     */
    @Override
    public String toString() {
        // Devuelve una representación en cadena de la motocicleta, incluyendo el estado del sidecar y los detalles heredados de AbstractVehicle.
        return "Motorcycle{" +
                "hasSidecar=" + hasSidecar +  // Devuelve si la motocicleta tiene un sidecar o no.
                ", " + super.toString() +  // Incluye los detalles del vehículo heredados de AbstractVehicle.
                '}';
    }
}
