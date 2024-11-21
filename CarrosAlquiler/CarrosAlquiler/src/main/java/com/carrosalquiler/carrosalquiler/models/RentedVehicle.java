package com.carrosalquiler.carrosalquiler.models;

import java.time.LocalDateTime;

/**
 * La clase RentedVehicle representa un vehículo alquilado por un usuario.
 * Contiene detalles sobre el usuario, el vehículo y la fecha de alquiler.
 */
public class RentedVehicle {
    private User user;  // Variable de instancia para almacenar el usuario que alquiló el vehículo
    private AbstractVehicle vehicle;  // Variable de instancia para almacenar el vehículo alquilado
    private LocalDateTime rentalDate;  // Variable de instancia para almacenar la fecha y hora del alquiler

    /**
     * Constructor para inicializar un RentedVehicle.
     * Establece la fecha de alquiler con la marca de tiempo actual.
     *
     * @param user el usuario que alquila el vehículo
     * @param vehicle el vehículo que está siendo alquilado
     */
    public RentedVehicle(User user, AbstractVehicle vehicle) {
        this.user = user;  // Inicializa el atributo user con el usuario proporcionado
        this.vehicle = vehicle;  // Inicializa el atributo vehicle con el vehículo proporcionado
        this.rentalDate = LocalDateTime.now(); // Establece la fecha de alquiler con la hora actual
    }

    // Métodos getter para los atributos

    /**
     * Obtiene el usuario que alquiló el vehículo.
     *
     * @return el usuario que alquiló el vehículo
     */
    public User getUser() {
        // Devuelve el usuario que alquiló el vehículo
        return user;
    }

    /**
     * Obtiene el vehículo que fue alquilado.
     *
     * @return el vehículo alquilado
     */
    public AbstractVehicle getVehicle() {
        // Devuelve el vehículo que fue alquilado
        return vehicle;
    }

    /**
     * Obtiene la fecha y hora en que el vehículo fue alquilado.
     *
     * @return la fecha y hora del alquiler
     */
    public LocalDateTime getRentalDate() {
        // Devuelve la fecha y hora del alquiler
        return rentalDate;
    }

    /**
     * Devuelve una representación en cadena de RentedVehicle.
     *
     * @return una cadena que contiene los detalles del vehículo alquilado, los detalles del usuario y la fecha de alquiler
     */
    @Override
    public String toString() {
        // Devuelve una representación en cadena de los detalles del vehículo, los detalles del usuario y la fecha de alquiler
        return "Rented Vehicle: " + vehicle.toString() +
                ", Rented By: " + user.toString() +
                ", Rental Date: " + rentalDate;
    }
}
