package com.carrosalquiler.carrosalquiler.service;

import com.carrosalquiler.carrosalquiler.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * GestionUsuariosService es una clase de servicio que gestiona las operaciones relacionadas con los usuarios.
 * Incluye métodos para el registro de usuarios, validación, creación de restricciones, listar todos los usuarios,
 * recuperar un usuario por su login y verificar los roles de usuario.
 */
@Service
public class GestionUsuariosService {
    private final List<User> userList;  // Lista de usuarios registrados

    /**
     * Constructor para inicializar el GestionUsuariosService.
     * Inicializa la lista de usuarios.
     */
    public GestionUsuariosService() {
        this.userList = new ArrayList<>();  // Inicializa la lista de usuarios como una lista vacía
    }

    /**
     * Registra un nuevo usuario agregándolo a la lista de usuarios.
     *
     * @param user el usuario que se va a registrar
     */
    public void registerUser(User user) {
        userList.add(user);  // Agrega el usuario a la lista de usuarios
        System.out.println("User registered: " + user.getName());  // Imprime un mensaje confirmando que el usuario ha sido registrado
    }

    /**
     * Valida a un usuario verificando su login y contraseña.
     *
     * @param login el nombre de usuario para el login
     * @param passwd la contraseña del usuario
     * @return true si el usuario es validado correctamente, false en caso contrario
     */
    public boolean validateUser(String login, String passwd) {
        for (User user : userList) {
            if (user.getLogin().equals(login) && user.getPasswd().equals(passwd)) {
                return true;  // Si el login y la contraseña coinciden, retorna verdadero
            }
        }
        return false;  // Si no se encuentra un usuario con las credenciales correctas, retorna falso
    }

    /**
     * Crea restricciones para un usuario basado en su edad y condiciones de visión o audición.
     * (La implementación se puede agregar según los requisitos específicos)
     *
     * @param user el usuario para el cual se deben crear restricciones
     */
    public void createRestrictions(User user) {
        // Lógica para crear restricciones basadas en la edad y condiciones de visión o audición
    }

    /**
     * Lista todos los usuarios registrados.
     *
     * @return una lista de todos los usuarios registrados
     */
    public List<User> listAllUsers() {
        return new ArrayList<>(userList);  // Devuelve una nueva lista con todos los usuarios registrados
    }

    /**
     * Recupera un usuario por su nombre de usuario (login).
     *
     * @param login el nombre de usuario para el login
     * @return el usuario con el nombre de usuario especificado, o null si no se encuentra
     */
    public User getUserByLogin(String login) {
        for (User user : userList) {
            if (user.getLogin().equals(login)) {
                return user;  // Retorna el usuario que coincide con el login
            }
        }
        return null;  // Si no se encuentra el usuario, retorna null
    }
}
