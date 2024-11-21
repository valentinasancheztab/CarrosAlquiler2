package com.carrosalquiler.carrosalquiler.models;

/**
 * Car es una subclase de AbstractVehicle que representa un automóvil con atributos y comportamientos adicionales.
 * Esta clase incluye el número de puertas específico de un automóvil e implementa los métodos abstractos de AbstractVehicle.
 */
public class Car extends AbstractVehicle {
    private int numberOfDoors;  // Variable de instancia para almacenar el número de puertas del automóvil.

    /**
     * Constructor para inicializar un objeto Car.
     *
     * @param licensePlate la matrícula del automóvil
     * @param kilometers los kilómetros recorridos por el automóvil
     * @param rentalValue el valor de alquiler del automóvil
     * @param isElectric si el automóvil es eléctrico
     * @param numberOfDoors el número de puertas que tiene el automóvil
     */

    public Car(String licensePlate, int kilometers, double rentalValue, boolean isElectric, int numberOfDoors) {
        // Llama al constructor de la clase AbstractVehicle para inicializar los atributos heredados
        super(licensePlate, kilometers, rentalValue, isElectric);
        //super:se utiliza para acceder a la funcionalidad de la superclase desde una clase derivada.(se utiliza para referirse a la clase base (superclase) de la que una clase hereda.)
        this.numberOfDoors = numberOfDoors; // Inicializa el número de puertas con el valor proporcionado.
    }

    /**
     * Método getter para el número de puertas.
     *
     * @return el número de puertas
     */

    public int getNumberOfDoors() {
        return numberOfDoors;
        // Devuelve el valor del número de puertas del automóvil.
    }

    /**
     * Método setter para el número de puertas.
     *
     * @param numberOfDoors el nuevo número de puertas
     */

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    } // Establece el valor del número de puertas.


    /**
     * Realiza mantenimiento en el automóvil.
     * Imprime un mensaje indicando que se está realizando el mantenimiento en el automóvil.
     */

    @Override
    public void performMaintenance() {
        // Imprime un mensaje de mantenimiento específico para el automóvil utilizando su matrícula.
        System.out.println("Performing maintenance on the car with license plate: " + getLicensePlate());
    }


    /**
     * Calcula el alquiler para el automóvil.
     * El alquiler se calcula según el número de días y el valor del alquiler.
     *
     * @param days el número de días por los cuales el automóvil es alquilado
     * @return el alquiler calculado
     */

    @Override
    public double calculateRent(int days) {
        return days * getRentalValue();
    }// Calcula el alquiler multiplicando el número de días por el valor de alquiler del automóvil.


    /**
     * Devuelve una representación en cadena del Car.
     *
     * @return una cadena que contiene los detalles del automóvil
     */
    @Override
    public String toString() {
        // Devuelve una representación en cadena del automóvil, incluyendo el número de puertas y los detalles heredados de AbstractVehicle.
        return "Car{" +
                "numberOfDoors=" + numberOfDoors + // Devuelve el número de puertas.
                ", " + super.toString() +// Incluye los detalles del vehículo heredados de AbstractVehicle.
                '}';
    }






}
