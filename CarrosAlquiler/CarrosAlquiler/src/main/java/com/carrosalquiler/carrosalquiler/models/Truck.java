package com.carrosalquiler.carrosalquiler.models;

/**
 * Truck es una subclase de AbstractVehicle que representa un camión con atributos y comportamientos adicionales.
 * Esta clase incluye la capacidad de carga específica para un camión e implementa los métodos abstractos de AbstractVehicle.
 */
public class Truck extends AbstractVehicle {
    private double cargoCapacity; // Capacidad de carga en toneladas

    /**
     * Constructor para inicializar un objeto Truck.
     *
     * @param licensePlate la matrícula del camión
     * @param kilometers los kilómetros recorridos por el camión
     * @param rentalValue el valor del alquiler del camión
     * @param isElectric indica si el camión es eléctrico
     * @param cargoCapacity la capacidad de carga del camión en toneladas
     */
    public Truck(String licensePlate, int kilometers, double rentalValue, boolean isElectric, double cargoCapacity) {
        super(licensePlate, kilometers, rentalValue, isElectric); // Llama al constructor de la clase padre (AbstractVehicle)
        this.cargoCapacity = cargoCapacity;  // Inicializa la capacidad de carga del camión
    }

    /**
     * Getter para la capacidad de carga.
     *
     * @return la capacidad de carga en toneladas
     */
    public double getCargoCapacity() {
        return cargoCapacity;  // Devuelve el valor de la capacidad de carga
    }

    /**
     * Setter para la capacidad de carga.
     *
     * @param cargoCapacity la nueva capacidad de carga en toneladas
     */
    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;  // Establece un nuevo valor para la capacidad de carga
    }

    /**
     * Realiza el mantenimiento del camión.
     * Imprime un mensaje indicando que se está realizando mantenimiento en el camión.
     */
    @Override
    public void performMaintenance() {
        System.out.println("Performing maintenance on the van with license plate: " + getLicensePlate());
    }

    /**
     * Calcula el alquiler del camión.
     * El alquiler se calcula en función del número de días y el valor del alquiler.
     *
     * @param days el número de días por los que se alquila el camión
     * @return el alquiler calculado
     */
    @Override
    public double calculateRent(int days) {
        return days * getRentalValue();  // El alquiler se calcula multiplicando los días por el valor de alquiler
    }

    /**
     * Devuelve una representación en cadena del camión.
     *
     * @return una cadena que contiene los detalles del camión
     */
    @Override
    public String toString() {
        return "Truck{" +
                "cargoCapacity=" + cargoCapacity +  // Incluye la capacidad de carga del camión
                ", " + super.toString() +  // Llama al método toString() de la clase padre para incluir los detalles generales
                '}';
    }
}
