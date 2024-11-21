package com.carrosalquiler.carrosalquiler.controller;

// Importa las clases necesarias
import com.carrosalquiler.carrosalquiler.models.ResponseMessage;
import com.carrosalquiler.carrosalquiler.models.Role;
import com.carrosalquiler.carrosalquiler.models.User;
import com.carrosalquiler.carrosalquiler.service.GestionUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * UserController es un controlador REST para gestionar operaciones relacionadas con los usuarios.
 * Se encarga del registro de usuarios, la validación, la inclusión de listas, las tareas específicas de administración y la creación de restricciones.
 */
@RestController //es un controlador que maneja solicitudes HTTP y devuelve datos directamente al cliente en formato
@RequestMapping("/users")
public class UserController {
    private final GestionUsuariosService userService;
    /**
     * Constructor con inyección de dependencia para el servicio de usuario.
     *
     * @param userService el servicio de usuario que se inyectará
     */
    @Autowired /**es una anotación que se utiliza para habilitar la inyección de dependencias automática,(no crea sus propias dependencias, sino que se las proporciona un contenedor )
     Su propósito es indicar a Spring que debe buscar y proporcionar automáticamente objeto gestionado por Spring para una variable, método o constructor.  */
    public UserController(GestionUsuariosService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint para registrar un nuevo usuario.
     *
     * @param user el objeto de usuario que contiene los detalles del usuario
     * @return un mensaje de respuesta que indica que el registro se realizó correctamente
     */
    /*maneja el registro de usuarios al recibir datos enviados por el cliente, procesarlos a través de un servicio y devolver una respuesta estructurada
    que indica si el registro fue exitoso o no.*/
    @PostMapping("/register")
    public ResponseMessage registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return new ResponseMessage(true, "User registered successfully!");
    }

    /**
     * Endpoint para validar un usuario.
     *
     * @param user el objeto de usuario que contiene los detalles de inicio de sesión
     * @return un mensaje de respuesta que indica si la validación fue exitosa o no
     */
    @PostMapping("/validate") // Maneja solicitudes POST a la URL "/validate".
    public ResponseMessage validateUser(@RequestBody User user) {
        // Se deserializa el cuerpo de la solicitud HTTP (JSON, por ejemplo) en un objeto `User`.
        // Este objeto contiene las credenciales enviadas por el cliente (login y contraseña).
        boolean isValid = userService.validateUser(user.getLogin(), user.getPasswd());
        // Se llama al método `validateUser` del servicio `userService`, que verifica si las
        // credenciales proporcionadas (login y contraseña) son válidas.
        if (isValid) {
            // Si las credenciales son válidas, se devuelve un objeto `ResponseMessage`
            // con éxito = true y un mensaje indicando que el usuario fue validado correctamente.
            return new ResponseMessage(true, "User validated successfully!");
        } else {
            // Si las credenciales no son válidas, se devuelve un objeto `ResponseMessage`
            // con éxito = false y un mensaje indicando que el login o la contraseña son incorrectos.
            return new ResponseMessage(false, "Invalid login or password.");
        }
    }

    /**
     * Punto final para listar todos los usuarios.
     *
     * @return una lista de todos los usuarios
     */
    @GetMapping("/all")
    public List<User> listAllUsers() {
        return userService.listAllUsers();
    }

    /**
     * Endpoint para que los administradores realicen tareas específicas de administración.
     *
     * @param login el nombre de usuario del usuario que intenta realizar tareas de administración
     * @return un mensaje de respuesta que indica si la tarea se realizó correctamente
     */
    @GetMapping("/admin/tasks")
    // Este método maneja solicitudes HTTP GET dirigidas a la URL "/admin/tasks".
// Generalmente se usa para operaciones de lectura o ejecución de tareas específicas.
    public ResponseMessage performAdminTask(@RequestParam String login) {
        // Define un método que recibe un parámetro llamado "login" de la URL.
        // El parámetro se pasa como una cadena (String) al método.
        User user = userService.getUserByLogin(login);
        // Llama al servicio `userService` para obtener un objeto `User` correspondiente al login proporcionado.
        // Esto probablemente busca al usuario en una base de datos.
        if (user != null && user.getRole() == Role.ADMIN) {
            // Lógica de tareas específicas del administrador
            // Solo los usuarios con el rol ADMIN pueden realizar esta tarea.
            // Admin-specific task logic
            // Aquí debería estar la lógica concreta para ejecutar la tarea de administrador.
            return new ResponseMessage(true, "Admin task performed!");
            // Si las validaciones son exitosas, retorna un mensaje indicando que la tarea fue realizada.
        } else {
            return new ResponseMessage(false, "Access denied. Admin role required.");
            // Retorna un mensaje indicando que se requiere el rol de administrador para acceder.
        }
    }

    /**
     * Endpoint para crear restricciones para un usuario.
     * Solo accesible para administradores.
     *
     * @param user el objeto de usuario para el que se crearán restricciones
     * @param login el nombre de usuario del usuario administrador que intenta crear restricciones
     * @return un mensaje de éxito si las restricciones se crean correctamente
     */
    @PostMapping("/createRestrictions")
    // Este método maneja solicitudes HTTP POST dirigidas a la URL "/createRestrictions".
// Se utiliza para crear restricciones asociadas a un usuario.
    public String createRestrictions(@RequestBody User user, @RequestParam String login) {
        // Define un método que recibe dos parámetros:
        // 1. `@RequestBody User user`: El cuerpo de la solicitud  se deserializa
        //    en un objeto `User`. Contiene la información del usuario al que se aplicarán las restricciones.
        // 2. `@RequestParam String login`: Un parámetro de consulta en la URL que representa el login
        //    del administrador que realiza la solicitud.
        User admin = userService.getUserByLogin(login);
        // Busca al usuario administrador en el sistema utilizando su login.
        // Este paso verifica que el usuario que realiza la acción existe.
        if (admin != null && admin.getRole() == Role.ADMIN) {
            // Comprueba si el usuario encontrado no es nulo
            // Solo los usuarios con rol de administrador están autorizados para crear restricciones.
            userService.createRestrictions(user);
            // Llama al método `createRestrictions` del servicio `userService` para aplicar
            // restricciones al usuario proporcionado en el cuerpo de la solicitud.
            return "Restrictions created successfully!";
            // Si la acción se completa correctamente, retorna un mensaje de éxito al cliente.
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied. Admin role required.");
            // Lanza una excepción HTTP con el código 403
            // Esto informa al cliente que la acción no está permitida sin los permisos adecuados
            // throw new ResponseStatusException: Se utiliza para detener la ejecución del método si el usuario no tiene el rol adecuado.
        }
    }

}
