package com.carrosalquiler.carrosalquiler.controller;

// Importa lo necesario de clases
import com.carrosalquiler.carrosalquiler.models.*;
import com.carrosalquiler.carrosalquiler.service.GestionVehiculoService;
import com.carrosalquiler.carrosalquiler.service.GestionUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
/**
 * VehicleController es un controlador REST para gestionar operaciones relacionadas con vehículos.
 * Se encarga del registro, listado, actualización y validación de vehículos, así como del alquiler y devolución de vehículos.
 */
@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final GestionVehiculoService vehicleService;
    private final GestionUsuariosService userService;
    private Iterable<? extends AbstractVehicle> vehicleList;

    /**
     * Constructor con inyección de dependencias para los servicios de vehículo y usuario.
     *
     * @param vehicleService el servicio del vehículo que se inyectará
     * @param userService el servicio del usuario que se inyectará
     */

    @Autowired /**es una anotación que se utiliza para habilitar la inyección de dependencias automática,(no crea sus propias dependencias, sino que se las proporciona un contenedor )
     Su propósito es indicar a Spring que debe buscar y proporcionar automáticamente objeto gestionado por Spring para una variable, método o constructor.  */
    public VehicleController(GestionVehiculoService vehicleService, GestionUsuariosService userService) {
        this.vehicleService = vehicleService;
        this.userService = userService;
    }

    /**
     * Punto final para registrar un nuevo automóvil.
     *
     * @param car el objeto del automóvil que contiene los detalles del automóvil
     * @return un mensaje de éxito
     */
    //@requestbody permite que los datos enviados por el cliente  se deserialicen automáticamente en una instancia de una clase Java.
    @PostMapping("/registerCar")
    public String registerCar(@RequestBody Car car) {
        // Define el método que recibe un objeto `Car` como entrada.
        vehicleService.registerVehicle(car);
        // Llama al servicio `vehicleService` para registrar el automóvil.
        // Este servicio probablemente contiene la lógica de negocio para almacenar el automóvil
        // en una base de datos o realizar validaciones.
        return "Car registered successfully!";
        // Devuelve un mensaje de éxito indicando que el automóvil fue registrado correctamente.
    }

    /**
     * Endpoint para registrar una nueva motocicleta.
     *
     * @param motorcycle el objeto de motocicleta que contiene los detalles de la motocicleta
     * @return un mensaje de éxito
     */
    @PostMapping("/registerMotorcycle")
    public String registerMotorcycle(@RequestBody Motorcycle motorcycle) {
        vehicleService.registerVehicle(motorcycle);
        return "Motorcycle registered successfully!";
    }

    /**
     * Endpoint para registrar un nuevo camión.
     *
     * @param truck el objeto de camión que contiene los detalles del camión
     * @return un mensaje de éxito
     */
    @PostMapping("/registerTruck")
    public String registerTruck(@RequestBody Truck truck) {
        vehicleService.registerVehicle(truck);
        return "Truck registered successfully!";
    }

    /**
     * Endpoint para registrar una nueva camioneta.
     *
     * @param van el objeto van que contiene los detalles de la camioneta
     * @return un mensaje de éxito
     */

    @PostMapping("/registerVan")
    public String registerVan(@RequestBody Van van) {
        vehicleService.registerVehicle(van);
        return "Van registered successfully!";
    }

    /**
     * Endpoint para listar todos los vehículos.
     *
     * @return una lista de todos los vehículos
     */
    @GetMapping("/all")
    public List<AbstractVehicle> listAllVehicles() {
        return vehicleService.listAllVehicles();
    }

    /**
     * Endpoint para listar los vehículos disponibles.
     *
     * @return una lista de vehículos disponibles
     */

    @GetMapping("/available")
    public List<AbstractVehicle> listAvailableVehicles() {
        return vehicleService.listAvailableVehicles();
    }

    /**
     * Endpoint para actualizar la disponibilidad de un vehículo.
     * Solo accesible para administradores.
     *
     * @param licensePlate la matrícula del vehículo que se va a actualizar
     * @param isAvailable el nuevo estado de disponibilidad
     * @param login el nombre de usuario del usuario que intenta actualizar la disponibilidad
     * @return un mensaje de éxito si la actualización es exitosa
     */

    @PutMapping("/availability/{licensePlate}")
    // Define un endpoint HTTP PUT que responde a solicitudes en la ruta "/availability/{licensePlate}".
// "{licensePlate}" es un parámetro dinámico que representa la matrícula del vehículo.
    public String updateAvailability(@PathVariable String licensePlate, @RequestParam boolean isAvailable, @RequestParam String login) {
        // Define un método que recibe la matrícula del vehículo como @PathVariable, un parámetro booleano `isAvailable` y el login del usuario como @RequestParam.
        User user = userService.getUserByLogin(login);
        // Llama al servicio `userService` para obtener el usuario asociado al login proporcionado.
        if (user != null && user.getRole() == Role.ADMIN) {
            // Comprueba que el usuario existe y que su rol es ADMIN.
            vehicleService.updateVehicleAvailability(licensePlate, isAvailable);
            // Si el usuario es administrador, llama al servicio `vehicleService` para actualizar la disponibilidad del vehículo.
            return "Vehicle availability updated successfully!";
            // Devuelve un mensaje de éxito indicando que la disponibilidad del vehículo fue actualizada.
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied. Admin role required.");
            // Si el usuario no es administrador, lanza una excepción HTTP 403
        }
    }

    /**
     * Endpoint para validar las condiciones de un vehículo.
     * Solo accesible para administradores.
     *
     * @param licensePlate la matrícula del vehículo a validar
     * @param conditions las condiciones del vehículo
     * @param login el login del usuario que intenta validar las condiciones
     * @return un mensaje de éxito si la validación es exitosa
     */

    // Define un endpoint PUT que valida las condiciones de un vehículo usando su placa.
    @PutMapping("/validateConditions/{licensePlate}")
    public String validateConditions(@PathVariable String licensePlate,
                                     @RequestParam String conditions,
                                     @RequestParam String login) {
        // Obtiene el usuario asociado al login proporcionado utilizando el servicio de usuarios.
        User user = userService.getUserByLogin(login);
        // Verifica si el usuario existe y si el rol del usuario es ADMIN.
        if (user != null && user.getRole() == Role.ADMIN) {
            // Es una clase o enumeración (enum) que define los diferentes roles que un usuario puede tener en el sistema.
            // Verifica si el usuario existe y si el rol del usuario es ADMIN.
            boolean valid = vehicleService.validateVehicleConditions(licensePlate, conditions);
            // Retorna un mensaje de éxito si las condiciones del vehículo fueron validadas correctamente, de lo contrario, retorna un mensaje de fallo.
            return valid ? "Vehicle conditions validated successfully!" : "Vehicle conditions validation failed.";
            //El signo de interrogación en este código se utiliza como parte de un operador ternario. Este operador es una forma compacta de escribir una instrucción
        } else {
            // Si el usuario no se encuentra o no tiene el rol de ADMIN, lanza una excepción de acceso denegado con el mensaje adecuado.
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied. Admin role required.");
        }
    }

    /**
     * Endpoint para listar los autos disponibles.
     * Solo accesible para administradores.
     *
     * @param login el nombre de usuario del usuario que intenta listar los autos
     * @return una lista de los autos disponibles
     */
    // Define un endpoint GET para listar los coches disponibles, accesible solo por administradores.
    @GetMapping("/available/cars")
    public List<Car> listAvailableCars(@RequestParam String login) {
        // Obtiene el usuario asociado al login proporcionado utilizando el servicio de usuarios.
        User user = userService.getUserByLogin(login);

        // Verifica si el usuario existe y si el rol del usuario es ADMIN.
        if (user != null && user.getRole() == Role.ADMIN) {
            // Llama al servicio de vehículos para obtener todos los vehículos de tipo "Car" y los convierte en una lista.
            return vehicleService.listVehiclesByType(Car.class).stream()
                    //stream :Un stream permite procesar los elementos de la colección de manera eficiente y fluida utilizando operaciones como map, filter, reduce, entre otras.
                    //tambien stream es utilizado para convertir una colección (como una lista, conjunto, etc.) en un stream
                    // Mapea cada vehículo a una instancia de tipo "Car" (ya que se obtiene como tipo genérico).
                    .map(vehicle -> (Car) vehicle)
                    // Colecta los vehículos en una lista y la retorna.
                    .collect(Collectors.toList());
        } else {
            // Si el usuario no se encuentra o no tiene el rol de ADMIN, lanza una excepción de acceso denegado con el mensaje adecuado.
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied. Admin role required.");
        }
    }

    /**
     * Endpoint para listar las motocicletas disponibles.
     * Solo accesible para administradores.
     *
     * @param login el nombre de usuario del usuario que intenta listar las motocicletas
     * @return una lista de motocicletas disponibles
     */
    @GetMapping("/available/motorcycles")
    public List<Motorcycle> listAvailableMotorcycles(@RequestParam String login) {
        User user = userService.getUserByLogin(login);
        if (user != null && user.getRole() == Role.ADMIN) {
            return vehicleService.listVehiclesByType(Motorcycle.class).stream()
                    .map(vehicle -> (Motorcycle) vehicle)
                    .collect(Collectors.toList());
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied. Admin role required.");
        }
    }

    /**
     * Endpoint para listar las furgonetas disponibles.
     * Solo accesible para administradores.
     *
     * @param login el nombre de usuario del usuario que intenta listar las furgonetas
     * @return una lista de furgonetas disponibles
     */

    @GetMapping("/available/vans")
    public List<Van> listAvailableVans(@RequestParam String login) {
        User user = userService.getUserByLogin(login);
        if (user != null && user.getRole() == Role.ADMIN) {
            return vehicleService.listVehiclesByType(Van.class).stream()
                    .map(vehicle -> (Van) vehicle)
                    .collect(Collectors.toList());
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied. Admin role required.");
        }
    }

    /**
     * Endpoint para listar los camiones disponibles.
     * Solo accesible para administradores.
     *
     * @param login el nombre de usuario del usuario que intenta listar los camiones
     * @return una lista de camiones disponibles
     */

    @GetMapping("/available/trucks")
    public List<Truck> listAvailableTrucks(@RequestParam String login) {
        User user = userService.getUserByLogin(login);
        if (user != null && user.getRole() == Role.ADMIN) {
            return vehicleService.listVehiclesByType(Truck.class).stream()
                    .map(vehicle -> (Truck) vehicle)
                    .collect(Collectors.toList());
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied. Admin role required.");
        }
    }

    /**
     * Endpoint para actualizar un vehículo.
     * Solo accesible para administradores.
     *
     * @param licensePlate la matrícula del vehículo que se va a actualizar
     * @param updatedVehiculo los detalles actualizados del vehículo
     * @param login el nombre de usuario del usuario que intenta actualizar el vehículo
     * @return un mensaje de éxito si la actualización se realiza correctamente
     */
    // Define un endpoint PUT para actualizar un vehículo con la placa proporcionada, accesible solo para administradores.
    @PutMapping("/update/{licensePlate}")

    public String updateVehicle(@PathVariable String licensePlate,
                                @RequestBody AbstractVehicle updatedVehiculo,
                                @RequestParam String login) {
        // Obtiene el usuario asociado al login proporcionado utilizando el servicio de usuarios.
        User user = userService.getUserByLogin(login);
        // Verifica si el usuario existe y si el rol del usuario es ADMIN.
        if (user != null && user.getRole() == Role.ADMIN) {
            // Llama al servicio de vehículos para actualizar el vehículo con la placa especificada utilizando la nueva información proporcionada.
            vehicleService.updateVehicle(licensePlate, updatedVehiculo);
            // Retorna un mensaje indicando que el vehículo ha sido actualizado con éxito
            return "Vehicle updated successfully!";
        } else {
            // Si el usuario no se encuentra o no tiene el rol de ADMIN, lanza una excepción de acceso denegado con el mensaje adecuado.
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied. Admin role required.");
        }
    }

    /**
     * Endpoint para eliminar un vehículo.
     * Solo accesible para administradores.
     *
     * @param licensePlate la matrícula del vehículo que se eliminará
     * @param login el nombre de usuario del usuario que intenta eliminar el vehículo
     * @return un mensaje de éxito si la eliminación se realiza correctamente
     */
// Define un endpoint DELETE para eliminar un vehículo con la placa proporcionada, accesible solo para administradores.
    @DeleteMapping("/delete/{licensePlate}")
    public String deleteVehicle(@PathVariable String licensePlate, @RequestParam String login) {
        // Obtiene el usuario asociado al login proporcionado utilizando el servicio de usuarios.
        User user = userService.getUserByLogin(login);
        // Obtiene el usuario asociado al login proporcionado utilizando el servicio de usuarios.
        if (user != null && user.getRole() == Role.ADMIN) {
            // Llama al servicio de vehículos para eliminar el vehículo con la placa especificada.
            vehicleService.deleteVehicle(licensePlate);
            // Retorna un mensaje indicando que el vehículo ha sido eliminado con éxito.
            return "Vehicle deleted successfully!";
        } else {
            // Si el usuario no se encuentra o no tiene el rol de ADMIN, lanza una excepción de acceso denegado con el mensaje adecuado.
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied. Admin role required.");
        }
    }

    /**
     * Endpoint para alquilar un vehículo.
     *
     * @param licensePlate la matrícula del vehículo que se va a alquilar
     * @param user el usuario que alquila el vehículo
     * @return un mensaje de éxito si el alquiler se realiza correctamente
     */
    // Define un endpoint POST para alquilar un vehículo, especificando la placa del vehículo y el usuario que realiza el alquiler.
    @PostMapping("/rent/{licensePlate}")
    public String rentVehicle(@PathVariable String licensePlate, @RequestBody User user) {
        // Llama al servicio de vehículos para realizar el alquiler del vehículo con la placa proporcionada y el usuario especificado.
        boolean success = vehicleService.rentVehicle(licensePlate, user);
        // Retorna un mensaje dependiendo del éxito o fracaso del alquiler.
        return success ? "Vehicle rented successfully!" : "Vehicle could not be rented.";
        // ?: if else
    }

    /**
     * Endpoint para devolver un vehículo alquilado.
     *
     * @param licensePlate la matrícula del vehículo que se devolverá
     * @return un mensaje de éxito si la devolución es exitosa
     */

    @PostMapping("/return/{licensePlate}")
    public String returnVehicle(@PathVariable String licensePlate) {
        boolean success = vehicleService.returnVehicle(licensePlate);
        return success ? "Vehicle returned successfully!" : "Vehicle could not be returned.";
    }
    /**
     * Endpoint para generar un informe de uso de los vehículos.
     * Solo accesible para administradores.
     *
     * @param startDate la fecha de inicio del informe
     * @param endDate la fecha de finalización del informe
     * @param login el inicio de sesión del usuario que solicita el informe
     * @return el informe de uso como una cadena
     */
    // Define un endpoint GET para generar un reporte de uso de vehículos entre dos fechas, accesible solo para administradores.
    @GetMapping("/usageReport")
    public String generateUsageReport(@RequestParam String startDate, @RequestParam String endDate, @RequestParam String login) {
        // Obtiene el usuario asociado al login proporcionado utilizando el servicio de usuarios.
        User user = userService.getUserByLogin(login);
        // Verifica si el usuario existe y si el rol del usuario es ADMIN.
        if (user != null && user.getRole() == Role.ADMIN) {
            // Llama al servicio de vehículos para generar el reporte de uso entre las fechas especificadas.
            return vehicleService.generateUsageReport(startDate, endDate);
        } else {
            // Si el usuario no se encuentra o no tiene el rol de ADMIN, lanza una excepción de acceso denegado con el mensaje adecuado.
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied. Admin role required.");
        }
    }

    /**
     * Endpoint para cargar vehículos en masa desde un archivo CSV.
     * Solo accesible para administradores.
     *
     * @param file el archivo CSV que contiene los datos del vehículo
     * @param login el nombre de usuario del usuario que intenta realizar la carga en masa
     * @return un mensaje de éxito si la carga en masa se realiza correctamente
     */

    // Define un endpoint POST para cargar un archivo CSV con vehículos en masa, accesible solo para administradores.
    @PostMapping("/bulkUpload")
    public String bulkUploadVehicles(@RequestParam("file") MultipartFile file, @RequestParam String login) {
        // Obtiene el usuario asociado al login proporcionado utilizando el servicio de usuarios.
        User user = userService.getUserByLogin(login);
        // Verifica si el usuario existe y si el rol del usuario es ADMIN.
        if (user != null && user.getRole() == Role.ADMIN) {
            try {
                // Convierte el archivo MultipartFile en un archivo File en el sistema local.
                File csvFile = convertMultiPartToFile(file);
                //file:representa un archivo físico en el sistema de archivos
                //csvfile:es el archivo CSV(valores separados por comas) cargado por el usuario, pero convertido a un archivo físico que el servidor puede manipular y procesar para realizar la carga masiva de vehículos en el sistema.
                // Llama al servicio de vehículos para cargar los vehículos desde el archivo CSV.
                vehicleService.bulkUploadVehicles(csvFile);
                // Retorna un mensaje de éxito si la carga masiva fue exitosa.
                return "Bulk upload successful!";
            } catch (Exception e) {
                // Si ocurre algún error durante el proceso, retorna un mensaje de error con la descripción del problema.
                return "Bulk upload failed: " + e.getMessage();
            }
        } else {
            // Si el usuario no tiene el rol de ADMIN o no existe, lanza una excepción de acceso denegado con el mensaje adecuado.
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied. Admin role required.");
        }
    }

    /**
     * Método de utilidad para convertir un MultipartFile en un archivo.
     *
     * @param file el MultipartFile que se va a convertir
     * @return el archivo convertido
     * @throws IOException si ocurre un error durante la conversión del archivo
     */
// Método privado que convierte un archivo de tipo MultipartFile en un archivo físico del sistema (File).
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        // Crea un objeto File con el nombre original del archivo que fue cargado (MultipartFile).
        // Este archivo se almacenará en la ruta predeterminada del proyecto o el directorio en el que se ejecuta.
        File convFile = new File(file.getOriginalFilename());
        //convfile:  es un objeto de tipo File que representa el archivo físico creado en el sistema de archivos del servidor.
        // Crea un flujo de salida de archivo (FileOutputStream) para escribir los datos en el archivo convFile.
        FileOutputStream fos = new FileOutputStream(convFile);
        // FileOutputStream: se utiliza para escribir datos en archivos, ya sea en formato binario o de texto, con la capacidad de sobrescribir el contenido existente.
        // Escribe los bytes del archivo MultipartFile en el archivo físico convFile.
        fos.write(file.getBytes());
        //fos:se usa para abrir un archivo en modo escritura (o crear un nuevo archivo si no existe)
        // Cierra el flujo de salida después de haber escrito los datos, liberando los recursos.
        fos.close();
        // Retorna el archivo físico (File) creado con los datos del archivo MultipartFile
        return convFile;
    }
    @GetMapping("/vehicles/color/{color}/count")
    public long getVehicleCountByColor(@PathVariable String color) {
        return vehicleService.getVehiclesByColor(color, vehicleList).size();
    }

}

