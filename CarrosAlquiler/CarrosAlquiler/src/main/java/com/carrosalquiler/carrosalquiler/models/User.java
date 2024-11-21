package com.carrosalquiler.carrosalquiler.models;
/**
 * La clase User representa a un usuario del sistema de alquiler de vehículos.
 * Incluye atributos como ID, nombre, edad, rol, discapacidad visual, discapacidad auditiva, nombre de usuario y contraseña.
 */
public class User {
    private String id;  // ID único del usuario
    private String name;  // Nombre del usuario
    private int age;  // Edad del usuario
    private Role role; // Campo para el rol del usuario
    private boolean hasVisionImpairment;  // Indica si el usuario tiene discapacidad visual
    private boolean hasHearingImpairment;  // Indica si el usuario tiene discapacidad auditiva
    private String login;  // Nombre de usuario para iniciar sesión
    private String passwd;  // Contraseña del usuario

    /**
     * Constructor por defecto para User.
     */
    public User() {}

    /**
     * Constructor para inicializar un User con todos los campos.
     *
     * @param id el identificador único del usuario
     * @param name el nombre del usuario
     * @param age la edad del usuario
     * @param role el rol del usuario (USER o ADMIN)
     * @param hasVisionImpairment indica si el usuario tiene discapacidad visual
     * @param hasHearingImpairment indica si el usuario tiene discapacidad auditiva
     * @param login el nombre de usuario para iniciar sesión
     * @param passwd la contraseña del usuario
     */
    public User(String id, String name, int age, Role role, boolean hasVisionImpairment, boolean hasHearingImpairment, String login, String passwd) {
        this.id = id;  // Inicializa el ID del usuario
        this.name = name;  // Inicializa el nombre del usuario
        this.age = age;  // Inicializa la edad del usuario
        this.role = role;  // Inicializa el rol del usuario
        this.hasVisionImpairment = hasVisionImpairment;  // Inicializa el estado de discapacidad visual
        this.hasHearingImpairment = hasHearingImpairment;  // Inicializa el estado de discapacidad auditiva
        this.login = login;  // Inicializa el nombre de usuario para iniciar sesión
        this.passwd = passwd;  // Inicializa la contraseña del usuario
    }

    // Getters y Setters para los atributos

    /**
     * Obtiene el ID del usuario.
     *
     * @return el ID del usuario
     */
    public String getId() {
        return id;  // Devuelve el ID del usuario
    }

    /**
     * Establece el ID del usuario.
     *
     * @param id el nuevo ID para el usuario
     */
    public void setId(String id) {
        this.id = id;  // Establece el nuevo ID para el usuario
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return el nombre del usuario
     */
    public String getName() {
        return name;  // Devuelve el nombre del usuario
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param name el nuevo nombre para el usuario
     */
    public void setName(String name) {
        this.name = name;  // Establece el nuevo nombre para el usuario
    }

    /**
     * Obtiene la edad del usuario.
     *
     * @return la edad del usuario
     */
    public int getAge() {
        return age;  // Devuelve la edad del usuario
    }

    /**
     * Establece la edad del usuario.
     *
     * @param age la nueva edad para el usuario
     */
    public void setAge(int age) {
        this.age = age;  // Establece la nueva edad para el usuario
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return el rol del usuario
     */
    public Role getRole() {
        return role;  // Devuelve el rol del usuario
    }

    /**
     * Establece el rol del usuario.
     *
     * @param role el nuevo rol para el usuario
     */
    public void setRole(Role role) {
        this.role = role;  // Establece el nuevo rol para el usuario
    }

    /**
     * Verifica si el usuario tiene discapacidad visual.
     *
     * @return true si el usuario tiene discapacidad visual, false en caso contrario
     */
    public boolean isHasVisionImpairment() {
        return hasVisionImpairment;  // Devuelve si el usuario tiene discapacidad visual
    }

    /**
     * Establece el estado de discapacidad visual del usuario.
     *
     * @param hasVisionImpairment el nuevo estado de discapacidad visual
     */
    public void setHasVisionImpairment(boolean hasVisionImpairment) {
        this.hasVisionImpairment = hasVisionImpairment;  // Establece el nuevo estado de discapacidad visual
    }

    /**
     * Verifica si el usuario tiene discapacidad auditiva.
     *
     * @return true si el usuario tiene discapacidad auditiva, false en caso contrario
     */
    public boolean isHasHearingImpairment() {
        return hasHearingImpairment;  // Devuelve si el usuario tiene discapacidad auditiva
    }

    /**
     * Establece el estado de discapacidad auditiva del usuario.
     *
     * @param hasHearingImpairment el nuevo estado de discapacidad auditiva
     */
    public void setHasHearingImpairment(boolean hasHearingImpairment) {
        this.hasHearingImpairment = hasHearingImpairment;  // Establece el nuevo estado de discapacidad auditiva
    }

    /**
     * Obtiene el nombre de usuario para iniciar sesión.
     *
     * @return el nombre de usuario
     */
    public String getLogin() {
        return login;  // Devuelve el nombre de usuario
    }

    /**
     * Establece el nombre de usuario para iniciar sesión.
     *
     * @param login el nuevo nombre de usuario
     */
    public void setLogin(String login) {
        this.login = login;  // Establece el nuevo nombre de usuario
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario
     */
    public String getPasswd() {
        return passwd;  // Devuelve la contraseña del usuario
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param passwd la nueva contraseña del usuario
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;  // Establece la nueva contraseña del usuario
    }
}
