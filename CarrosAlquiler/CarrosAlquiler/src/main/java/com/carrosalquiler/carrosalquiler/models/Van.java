package com.carrosalquiler.carrosalquiler.models;

/**
 * Van es una subclase de AbstractVehicle que representa una furgoneta con atributos y comportamientos adicionales.
 * Esta clase incluye la capacidad de asientos específica para una furgoneta e implementa los métodos abstractos de AbstractVehicle.
 */
public class Van extends AbstractVehicle {
    private int seatingCapacity; // Capacidad de asientos

    /**
     * Constructor para inicializar un objeto Van.
     *
     * @param licensePlate la matrícula de la furgoneta
     * @param kilometers los kilómetros recorridos por la furgoneta
     * @param rentalValue el valor del alquiler de la furgoneta
     * @param isElectric indica si la furgoneta es eléctrica
     * @param seatingCapacity la capacidad de asientos de la furgoneta
     */
    public Van(String licensePlate, int kilometers, double rentalValue, boolean isElectric, int seatingCapacity) {
        super(licensePlate, kilometers, rentalValue, isElectric);  // Llama al constructor de la clase base AbstractVehicle
        this.seatingCapacity = seatingCapacity;  // Inicializa la capacidad de asientos de la furgoneta
    }

    /**
     * Getter para la capacidad de asientos.
     *
     * @return la capacidad de asientos
     */
    public int getSeatingCapacity() {
        return seatingCapacity;  // Devuelve la capacidad de asientos
    }

    /**
     * Setter para la capacidad de asientos.
     *
     * @param seatingCapacity la nueva capacidad de asientos
     */
    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;  // Establece la nueva capacidad de asientos
    }

    /**
     * Realiza el mantenimiento de la furgoneta.
     * Imprime un mensaje indicando que se está realizando el mantenimiento en la furgoneta.
     */
    @Override
    public void performMaintenance() {
        System.out.println("Performing maintenance on the van with license plate: " + getLicensePlate());
    }

    /**
     * Calcula el alquiler de la furgoneta.
     * El alquiler se calcula en función de los días y el valor del alquiler.
     *
     * @param days el número de días por los que se alquila la furgoneta
     * @return el alquiler calculado
     */
    @Override
    public double calculateRent(int days) {
        return days * getRentalValue();  // Calcula el alquiler multiplicando los días por el valor del alquiler
    }

    /**
     * Devuelve una representación en cadena de la furgoneta.
     *
     * @return una cadena que contiene los detalles de la furgoneta
     */
    @Override
    public String toString() {
        return "Van{" +
                "seatingCapacity=" + seatingCapacity +  // Muestra la capacidad de asientos
                ", " + super.toString() +  // Llama al método toString de la clase base AbstractVehicle
                '}';
    }
}
