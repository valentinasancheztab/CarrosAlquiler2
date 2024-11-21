package com.carrosalquiler.carrosalquiler.service;

import com.carrosalquiler.carrosalquiler.models.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * GestionVehiculoService es una clase de servicio que gestiona las operaciones relacionadas con los vehículos.
 * Incluye métodos para registrar, actualizar disponibilidad, validar condiciones, listar por tipo, actualizar, eliminar, alquilar, devolver,
 * generar informes de uso, cargar en masa desde un archivo y listar vehículos disponibles.
 */
@Service
public class GestionVehiculoService {
    private final List<AbstractVehicle> vehiculos = new ArrayList<>();  // Lista de vehículos registrados

    /**
     * Registra un nuevo vehículo agregándolo a la lista.
     *
     * @param vehiculo el vehículo que se va a registrar
     */
    public void registerVehicle(AbstractVehicle vehiculo) {
        vehiculos.add(vehiculo);  // Agrega el vehículo a la lista
    }

    /**
     * Actualiza el estado de disponibilidad de un vehículo.
     *
     * @param licensePlate la matrícula del vehículo
     * @param isAvailable el nuevo estado de disponibilidad
     */
    public void updateVehicleAvailability(String licensePlate, boolean isAvailable) {
        for (AbstractVehicle vehiculo : vehiculos) {
            if (vehiculo.getLicensePlate().equals(licensePlate)) {
                vehiculo.setAvailable(isAvailable);  // Actualiza la disponibilidad del vehículo
                return;
            }
        }
    }

    /**
     * Valida las condiciones de un vehículo.
     *
     * @param licensePlate la matrícula del vehículo
     * @param conditions las condiciones a validar
     * @return true si las condiciones son validadas, false en caso contrario
     */
    public boolean validateVehicleConditions(String licensePlate, String conditions) {
        for (AbstractVehicle vehiculo : vehiculos) {
            if (vehiculo.getLicensePlate().equals(licensePlate)) {
                // Lógica para validar las condiciones
                // Se pueden comparar las condiciones recibidas con las esperadas
                return true; // o false dependiendo de la validación
            }
        }
        return false;  // Retorna false si no se encuentra el vehículo
    }

    /**
     * Lista los vehículos filtrados por tipo y disponibilidad.
     *
     * @param vehicleType el tipo de vehículo para filtrar
     * @return una lista de vehículos filtrados
     */
    public List<AbstractVehicle> listVehiclesByType(Class<? extends AbstractVehicle> vehicleType) {
        List<AbstractVehicle> filteredVehicles = new ArrayList<>();
        for (AbstractVehicle vehiculo : vehiculos) {
            if (vehicleType.isInstance(vehiculo) && vehiculo.isAvailable()) {
                filteredVehicles.add(vehiculo);  // Agrega vehículos disponibles del tipo especificado
            }
        }
        return filteredVehicles;  // Retorna la lista de vehículos filtrados
    }

    /**
     * Actualiza los detalles de un vehículo.
     *
     * @param licensePlate la matrícula del vehículo a actualizar
     * @param updatedVehiculo los nuevos detalles del vehículo
     */
    public void updateVehicle(String licensePlate, AbstractVehicle updatedVehiculo) {
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getLicensePlate().equals(licensePlate)) {
                vehiculos.set(i, updatedVehiculo);  // Reemplaza el vehículo viejo por el actualizado
                return;
            }
        }
    }

    /**
     * Elimina un vehículo por su matrícula.
     *
     * @param licensePlate la matrícula del vehículo a eliminar
     */
    public void deleteVehicle(String licensePlate) {
        vehiculos.removeIf(vehiculo -> vehiculo.getLicensePlate().equals(licensePlate));  // Elimina el vehículo de la lista si coincide la matrícula
    }

    /**
     * Lista todos los vehículos registrados.
     *
     * @return una lista de todos los vehículos
     */
    public List<AbstractVehicle> listAllVehicles() {
        return new ArrayList<>(vehiculos);  // Retorna una nueva lista con todos los vehículos
    }

    /**
     * Alquila un vehículo a un usuario.
     *
     * @param licensePlate la matrícula del vehículo a alquilar
     * @param user el usuario que alquila el vehículo
     * @return true si el vehículo se alquila con éxito, false en caso contrario
     */
    public boolean rentVehicle(String licensePlate, User user) {
        for (AbstractVehicle vehiculo : vehiculos) {
            if (vehiculo.getLicensePlate().equals(licensePlate) && vehiculo.isAvailable()) {
                vehiculo.setAvailable(false);  // Marca el vehículo como no disponible
                // Lógica adicional para registrar el alquiler
                return true;
            }
        }
        return false;  // Retorna false si el vehículo no está disponible
    }

    /**
     * Devuelve un vehículo alquilado.
     *
     * @param licensePlate la matrícula del vehículo a devolver
     * @return true si el vehículo se devuelve con éxito, false en caso contrario
     */
    public boolean returnVehicle(String licensePlate) {
        for (AbstractVehicle vehiculo : vehiculos) {
            if (vehiculo.getLicensePlate().equals(licensePlate) && !vehiculo.isAvailable()) {
                vehiculo.setAvailable(true);  // Marca el vehículo como disponible nuevamente
                // Lógica adicional para registrar la devolución
                return true;
            }
        }
        return false;  // Retorna false si el vehículo no estaba alquilado
    }

    /**
     * Genera un informe de uso de los vehículos.
     *
     * @param startDate la fecha de inicio del informe
     * @param endDate la fecha de fin del informe
     * @return el informe de uso como una cadena de texto
     */
    public String generateUsageReport(String startDate, String endDate) {
        // Lógica para generar el informe de uso
        return "Usage report from " + startDate + " to " + endDate;  // Retorna un informe de uso simple
    }

    /**
     * Carga vehículos en masa desde un archivo CSV.
     *
     * @param csvFile el archivo CSV que contiene los datos de los vehículos
     */
    public void bulkUploadVehicles(File csvFile) {
        // Lógica para leer y procesar el archivo CSV
    }

    /**
     * Lista todos los vehículos disponibles.
     *
     * @return una lista de vehículos disponibles
     */
    public List<AbstractVehicle> listAvailableVehicles() {
        List<AbstractVehicle> availableVehicles = new ArrayList<>();
        for (AbstractVehicle vehiculo : vehiculos) {
            if (vehiculo.isAvailable()) {
                availableVehicles.add(vehiculo);  // Agrega el vehículo a la lista si está disponible
            }
        }
        return availableVehicles;  // Retorna la lista de vehículos disponibles
    }
    // Método para obtener vehículos de un color específico
    public List<AbstractVehicle> getVehiclesByColor(String color, Iterable<? extends AbstractVehicle> vehicleList) {
        List<AbstractVehicle> filteredVehicles = new ArrayList<>();
        for (AbstractVehicle vehicle : vehicleList) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                filteredVehicles.add(vehicle);
            }
        }
        return filteredVehicles;
    }
}
