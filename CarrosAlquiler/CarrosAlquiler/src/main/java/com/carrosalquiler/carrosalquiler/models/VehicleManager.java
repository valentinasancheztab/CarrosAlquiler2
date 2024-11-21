package com.carrosalquiler.carrosalquiler.models;

import java.util.ArrayList;
import java.util.List;

/**
 * VehicleManager gestiona la colección de vehículos en el sistema.
 * Incluye métodos para registrar, verificar disponibilidad, marcar como alquilado o disponible y listar todos los vehículos.
 */
public class VehicleManager {
    private ArrayList<AbstractVehicle> vehicles;  // Lista de vehículos registrados

    /**
     * Constructor para inicializar el VehicleManager.
     * Inicializa la lista de vehículos.
     */
    public VehicleManager() {
        this.vehicles = new ArrayList<>();  // Inicializa la lista de vehículos como una lista vacía
    }

    /**
     * Registra un nuevo vehículo agregándolo a la lista de vehículos.
     *
     * @param vehicle el vehículo que se va a registrar
     */
    public void registerVehicle(AbstractVehicle vehicle) {
        vehicles.add(vehicle);  // Agrega el vehículo a la lista de vehículos
        System.out.println("Vehicle registered successfully: " + vehicle);  // Imprime un mensaje confirmando el registro del vehículo
    }

    /**
     * Verifica si un vehículo está disponible.
     *
     * @param vehicle el vehículo a verificar
     * @return true si el vehículo está disponible, false en caso contrario
     */
    public boolean isVehicleAvailable(AbstractVehicle vehicle) {
        return vehicles.contains(vehicle) && vehicle.isAvailable();  // Verifica si el vehículo está presente en la lista y está disponible
    }

    /**
     * Marca un vehículo como alquilado.
     * Establece la disponibilidad del vehículo a falso si está actualmente disponible.
     *
     * @param vehicle el vehículo que se marcará como alquilado
     */
    public void markAsRented(AbstractVehicle vehicle) {
        if (isVehicleAvailable(vehicle)) {
            vehicle.setAvailable(false);  // Cambia la disponibilidad del vehículo a falso (alquilado)
            System.out.println("Vehicle marked as rented: " + vehicle);  // Imprime un mensaje indicando que el vehículo ha sido marcado como alquilado
        } else {
            System.out.println("Vehicle is not available for rent.");  // Imprime un mensaje de error si el vehículo no está disponible
        }
    }

    /**
     * Marca un vehículo como disponible.
     * Establece la disponibilidad del vehículo a verdadero si actualmente no está disponible.
     *
     * @param vehicle el vehículo que se marcará como disponible
     */
    public void markAsAvailable(AbstractVehicle vehicle) {
        if (!vehicle.isAvailable()) {
            vehicle.setAvailable(true);  // Cambia la disponibilidad del vehículo a verdadero (disponible)
            System.out.println("Vehicle marked as available: " + vehicle);  // Imprime un mensaje confirmando que el vehículo ahora está disponible
        }
    }

    /**
     * Lista todos los vehículos.
     *
     * @return una lista de todos los vehículos registrados
     */
    public ArrayList<AbstractVehicle> listAllVehicles() {
        return new ArrayList<>(vehicles);  // Devuelve una nueva lista con todos los vehículos registrados
    }

    public class VehicleManagementService {
        private List<AbstractVehicle> vehicleList;

        // Constructor
        public VehicleManagementService() {
            this.vehicleList = new ArrayList<>();
        }

        // metodo para rellenar lista de vehiculos
        public void populateVehicles() {
            // añade carros a la lista
            System.out.println("Vehicle list populated.");
        }

        // metodo para registrar un nuevo vehiculo
        public void registerVehicle(AbstractVehicle vehicle) {
            vehicleList.add(vehicle);
            System.out.println("Vehicle registered: " + vehicle);
        }

        // Getter para la lista de vehiculos
        public List<AbstractVehicle> getVehicleList() {
            return vehicleList;
        }
    }
}
