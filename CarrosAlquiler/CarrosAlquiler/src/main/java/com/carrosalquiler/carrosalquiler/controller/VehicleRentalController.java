package com.carrosalquiler.carrosalquiler.controller;

// Importa las clases necesarias
import com.carrosalquiler.carrosalquiler.models.RentedVehicle;
import com.carrosalquiler.carrosalquiler.models.*;
import java.util.ArrayList;

/**
 * VehicleRentalController gestiona el alquiler y la devolución de vehículos.
 * Se encarga de las operaciones relacionadas con el alquiler, la devolución, el listado de vehículos alquilados y el registro de vehículos nuevos.
 */
// Clase que gestiona las operaciones relacionadas con el alquiler de vehículos.
public class VehicleRentalController {

    // Attributes
    private final ArrayList<RentedVehicle> rentedVehiclesList; // Lista para almacenar los vehículos alquilados.
    private final VehicleManager vehicleManager;// El objeto encargado de la gestión de vehículos.
// private final UserManager userManager; // Comentado en caso de no ser utilizado.

    /**
     * Constructor de la clase VehicleRentalController.
     * Inicializa la lista de vehículos alquilados y el gestor de vehículos.
     *
     * @param vehicleManager El gestor de vehículos para gestionar las operaciones de vehículos.
     */
    public VehicleRentalController(VehicleManager vehicleManager) { // Elimina UserManager si no es necesario
        this.rentedVehiclesList = new ArrayList<>(); // Inicializa la lista vacía de vehículos alquilados.
        this.vehicleManager = vehicleManager;  // Asigna el gestor de vehículos proporcionado al atributo.
        // this.userManager = userManager; // Comentado en caso de no ser utilizado
    }

    // Métodos

    /**
     * Alquila un vehículo a un usuario.
     *
     * @param vehicle El vehículo que se va a alquilar.
     * @param user    El usuario que alquila el vehículo.
     */

    public void rentVehicle(AbstractVehicle vehicle, User user) {
        // Verifica si el vehículo está disponible para alquilar.
        if (vehicleManager.isVehicleAvailable(vehicle)) {
            // Crea una nueva instancia de RentedVehicle para registrar el alquiler.
            RentedVehicle rental = new RentedVehicle(user, vehicle);
            rentedVehiclesList.add(rental);  // Añade el vehículo alquilado a la lista.
            vehicleManager.markAsRented(vehicle);// Marca el vehículo como alquilado en el gestor de vehículos.
            System.out.println("Vehicle successfully rented: " + vehicle); // Mensaje indicando que el alquiler fue exitoso.
        } else {
            System.out.println("The vehicle is not available for rent.");  // Mensaje indicando que el vehículo no está disponible.
        }
    }

    /**
     * Devuelve un vehículo alquilado.
     *
     * @param vehicle El vehículo que se va a devolver.
     */

    public void returnVehicle(AbstractVehicle vehicle) {
        // Busca el vehículo en la lista de vehículos alquilados.
        RentedVehicle rental = rentedVehiclesList.stream()
                .filter(r -> r.getVehicle().equals(vehicle))// Filtra el vehículo que se desea devolver.
                //r:es simplemente una variable temporal que representa cada elemento del flujo,el filtro verifica si el vehículo asociado a ese alquiler es el mismo que el vehículo proporcionado.
                .findFirst() // Obtiene el primer elemento encontrado.
                //findFirst(): encuentra el primer elemento de un flujo que cumple con los filtros aplicados y lo devuelve envuelto en un Optional.
                .orElse(null);// Devuelve null si no se encuentra el vehículo.

        if (rental != null) {  // Si el vehículo fue encontrado en la lista de alquilados.
            rentedVehiclesList.remove(rental); // Elimina el vehículo de la lista de alquileres.
            vehicleManager.markAsAvailable(vehicle); // Marca el vehículo como disponible en el gestor de vehículos.
            System.out.println("Vehicle successfully returned: " + vehicle); // Mensaje indicando que el vehículo fue devuelto exitosamente.
        } else {
            System.out.println("The vehicle is not registered as rented."); // Mensaje indicando que el vehículo no está registrado como alquilado.
        }
    }

    /**
     * Lista todos los vehículos alquilados.
     *
     * @return Una lista de vehículos alquilados.
     */
    public ArrayList<AbstractVehicle> listRentedVehicles() {
        ArrayList<AbstractVehicle> vehicles = new ArrayList<>();// Crea una nueva lista de vehículos alquilados.
        //<> El compilador infiere automáticamente el tipo String para el tipo genérico,
        for (RentedVehicle rental : rentedVehiclesList) { // Itera sobre la lista de vehículos alquilados.
            vehicles.add(rental.getVehicle()); // Añade el vehículo alquilado a la lista de vehículos.
        }
        return vehicles;// Devuelve la lista de vehículos alquilados.
    }

    /**
     * Registra un nuevo vehículo.
     *
     * @param vehicle El vehículo que se va a registrar.
     */

    public void registerVehicle(AbstractVehicle vehicle) {  // Asegúrate de pasar el argumento necesario.
        vehicleManager.registerVehicle(vehicle); // Registra el vehículo en el gestor de vehículos.
    }
}
