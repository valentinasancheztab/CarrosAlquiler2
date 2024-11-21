package com.carrosalquiler.carrosalquiler.models;

/**
 * AbstractVehicle es una clase abstracta que representa las propiedades y comportamientos comunes de los vehículos.
 * Esta clase incluye atributos como la matrícula, los kilómetros recorridos, el valor de alquiler y el estado eléctrico.
 * También incluye métodos para realizar mantenimiento y calcular el alquiler, los cuales deben ser implementados por las subclases.
 */
public abstract class AbstractVehicle {
    // Atributos
    private String licensePlate;  // Matrícula del vehículo.
    private int kilometers;  // Kilómetros recorridos por el vehículo.
    private double rentalValue; // Valor de alquiler del vehículo.
    private boolean isElectric; // Estado de si el vehículo es eléctrico
    private boolean isAvailable = true; // Estado de disponibilidad del vehículo, por defecto disponible.

    /**
     * Constructor para inicializar un AbstractVehicle.
     *
     * @param licensePlate la matrícula del vehículo
     * @param kilometers los kilómetros recorridos por el vehículo
     * @param rentalValue el valor de alquiler del vehículo
     * @param isElectric si el vehículo es eléctrico
     */

    public AbstractVehicle(String licensePlate, int kilometers, double rentalValue, boolean isElectric) {
        this.licensePlate = licensePlate; // Asigna la matrícula al vehículo.
        this.kilometers = kilometers; // Asigna los kilómetros recorridos al vehículo.
        this.rentalValue = rentalValue;  // Asigna el valor de alquiler al vehículo.
        this.isElectric = isElectric;  // Asigna el estado eléctrico del vehículo.
    }

    // Métodos getter y setter para los atributos

    public String getLicensePlate() {
        return licensePlate;
    } // Devuelve la matrícula del vehículo.

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    } // Asigna una nueva matrícula al vehículo.

    public int getKilometers() {
        return kilometers;
    } // Devuelve los kilómetros recorridos por el vehículo.

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }  // Asigna un nuevo valor para los kilómetros recorridos por el vehículo.

    public double getRentalValue() {
        return rentalValue;
    }  // Devuelve el valor de alquiler del vehículo.

    public void setRentalValue(double rentalValue) {
        this.rentalValue = rentalValue;
    }// Asigna un nuevo valor de alquiler al vehículo.

    public boolean isElectric() {
        return isElectric;
    } // Devuelve si el vehículo es eléctrico.

    public void setElectric(boolean electric) {
        isElectric = electric;
    }  // Asigna si el vehículo es eléctrico o no.

    public boolean isAvailable() {
        return isAvailable;
    }   // Devuelve el valor de la variable isAvailable, que indica si el vehículo está disponible.

    public void setAvailable(boolean available) {
        isAvailable = available;
    }   // Establece el valor de la variable isAvailable según el parámetro available.

    /**
     * Método abstracto para realizar el mantenimiento del vehículo.
     * Las subclases deben proporcionar una implementación para este método.
     */
    public abstract void performMaintenance();
// Método abstracto que debe ser implementado por las subclases para realizar el mantenimiento del vehículo.

    /**
     * Método abstracto para calcular el alquiler del vehículo.
     * Las subclases deben proporcionar una implementación para este método.
     *
     * @param days el número de días por los cuales el vehículo es alquilado.
     * @return el valor calculado del alquiler.
     */
    public abstract double calculateRent(int days);
// Método abstracto que debe ser implementado por las subclases para calcular el alquiler del vehículo en función de los días alquilados.

    /**
     * Devuelve una representación en cadena del AbstractVehicle.
     *
     * @return una cadena que contiene los detalles del vehículo.
     */
    @Override
    public String toString() {
        // Sobrescribe el método toString para devolver una representación en cadena del objeto AbstractVehicle.
        return "AbstractVehicle{" +
                "licensePlate='" + licensePlate + '\'' + // Devuelve la matrícula del vehículo.
                ", kilometers=" + kilometers + // Devuelve el número de kilómetros recorridos por el vehículo.
                ", rentalValue=" + rentalValue +// Devuelve el valor del alquiler del vehículo.
                ", isElectric=" + isElectric + // Devuelve si el vehículo es eléctrico o no.
                ", isAvailable=" + isAvailable +  // Devuelve si el vehículo está disponible o no.
                '}';
    }
    // Atributos
    private String Registration;
    private int km;
    private boolean State; // true = disponible, false = alquilado
    private double RentalValue;
    private String color;

    // Constructor
    public AbstractVehicle(String Registration, int km, boolean State, double RentalValue, String color) {
        this.Registration = Registration;
        this.km = km;
        this.State = State;
        this.RentalValue = RentalValue;
        this.color=color;
    }

    //Getter y Setters del color
    public String getColor(){
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
