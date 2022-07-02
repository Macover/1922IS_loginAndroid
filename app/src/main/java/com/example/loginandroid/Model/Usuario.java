package com.example.loginandroid.Model;

public class Usuario {
    private int ID;
    private String name;
    private String lastname_pat;
    private String lastname_mat;
    private String email;
    private String password;
    private String contrasenia2;
    private String fechaNacimiento;
    private String codigoConfirmacion;
    private String tokenRecovery;
    private String create_at;
    private String update_at;
    private String estatus;

    private String status;
    private String mensaje;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public Usuario() {
    }

    public Usuario(int ID, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String contrasenia, String contrasenia2, String fechaNacimiento, String status, String codigoConfirmacion, String tokenRecovery, String create_at, String update_at, String estatus, String mensaje) {
        this.ID = ID;
        this.name = nombre;
        this.lastname_pat = apellidoPaterno;
        this.lastname_mat = apellidoMaterno;
        this.email = correo;
        this.password = contrasenia;
        this.contrasenia2 = contrasenia2;
        this.fechaNacimiento = fechaNacimiento;
        this.status = status;
        this.codigoConfirmacion = codigoConfirmacion;
        this.tokenRecovery = tokenRecovery;
        this.create_at = create_at;
        this.update_at = update_at;
        this.estatus = estatus;
        this.mensaje = mensaje;
    }
    public Usuario(String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String contrasenia, String contrasenia2, String fechaNacimiento) {
        this.name = nombre;
        this.lastname_pat = apellidoPaterno;
        this.lastname_mat = apellidoMaterno;
        this.email = correo;
        this.password = contrasenia;
        this.contrasenia2 = contrasenia2;
        this.fechaNacimiento = fechaNacimiento;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname_pat() {
        return lastname_pat;
    }

    public void setLastname_pat(String lastname_pat) {
        this.lastname_pat = lastname_pat;
    }

    public String getLastname_mat() {
        return lastname_mat;
    }

    public void setLastname_mat(String lastname_mat) {
        this.lastname_mat = lastname_mat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContrasenia2() {
        return contrasenia2;
    }

    public void setContrasenia2(String contrasenia2) {
        this.contrasenia2 = contrasenia2;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCodigoConfirmacion() {
        return codigoConfirmacion;
    }

    public void setCodigoConfirmacion(String codigoConfirmacion) {
        this.codigoConfirmacion = codigoConfirmacion;
    }

    public String getTokenRecovery() {
        return tokenRecovery;
    }

    public void setTokenRecovery(String tokenRecovery) {
        this.tokenRecovery = tokenRecovery;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
