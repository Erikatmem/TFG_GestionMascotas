package Clases;

public class Propietarios {
    String nombre,apellidos,telefono,email,direccion;
    int id;

    /* Clase que representa la entidad de HistorialMedico.
     * Cada entidad representa una fila de una consulta.
     */
    public Propietarios(int id, String direccion, String email, String telefono, String apellidos, String nombre) {
        this.id = id;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.apellidos = apellidos;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString(){
        return "Id: "+this.id+" Nombre: "+this.nombre;
    }
}
