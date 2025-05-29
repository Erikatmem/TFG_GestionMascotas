package BBDD;

import Clases.HistorialMedico;
import Clases.Mascotas;
import Clases.Propietarios;
import java.sql.*;
import java.util.ArrayList;

/*
* Clase consultas.
* Esta clase proporciona métodos para realizar operaciones CRUD sobre las entidades:
* Propietarios, Mascotas e HistorialMedico.
* Gestiona la conexión a la base de datos MySQL.
*/
public class Consultas {
    private static Connection conexion;

/*
* Metodo que establece y devuelve la conexión a la base de datos.
* Conecta a la base de datos "pruebas" con usuario y contraseña "alumna"
*/
    public static Connection getConexion() {
        if (conexion == null){
            try {
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "alumna",
                        "alumna");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return conexion;
    }


// Consulta todos los propietarios registrados en la base de datos.
    public static ArrayList<Propietarios> consultarPropietarios()  {
        ArrayList<Propietarios> propietarios = new ArrayList<>();
        String consulta = "SELECT * FROM propietarios";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(consulta);

        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            System.out.println();
            propietarios.add(new Propietarios(rs.getInt("id_propietario"),rs.getString("direccion"),rs.getString("email"),
                    rs.getString("telefono"),rs.getString("apellidos"),rs.getString("nombre"))  );
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return propietarios;
    }

// Consulta un propietario por su ID.
// Creamos Get con cada columna de la base de datos.
    public static Propietarios consultarPropietarios(int id)  {
        Propietarios propietario = null;
        String consulta = "SELECT * FROM propietarios WHERE id_propietario= "+id;
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            rs.next();
                System.out.println();
                propietario = new Propietarios(
                        rs.getInt("id_propietario"),
                        rs.getString("direccion"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("apellidos"),
                        rs.getString("nombre")) ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return propietario;
    }

// Consulta una mascota por su ID.
// Creamos el Get con todas las columnas de la base de datos.
    public static Mascotas consultarMascota(int id)  {
        Mascotas mascota = null;
        String consulta = "SELECT * FROM mascotas WHERE id= "+id;
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(consulta);

            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println();
            mascota = new Mascotas(
                    rs.getInt("id"),
                    rs.getInt("idPropietario"),
                    rs.getString("nombre"),
                    rs.getString("raza"),
                    rs.getDouble("peso"),
                    rs.getDate("fecha_nacimiento"),
                    rs.getString("genero"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return mascota;
    }

//Consulta todas las mascotas registradas.
//Creamos el Get con todas las columnas de la base de datos.
    public static ArrayList<Mascotas> consultarMascotas()  {
        ArrayList<Mascotas> mascotas = new ArrayList<>();
        String consulta = "SELECT * FROM mascotas";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(consulta);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println();
                mascotas.add(new Mascotas(
                        rs.getInt("id"),
                        rs.getInt("idPropietario"),
                        rs.getString("nombre"),
                        rs.getString("raza"),
                        rs.getDouble("peso"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("genero")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return mascotas;
    }
// Inserta un nuevo propietario en la base de datos.

    public static void copiarPropietario(Propietarios p){
        String consulta = "INSERT INTO PROPIETARIOS (nombre,apellidos,telefono,email,direccion) VALUES (?,?,?,?,?)";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try{
            // Creamos PreparedStatment para que cumpla con los estandares de seguridad.
            ps = conn.prepareStatement(consulta);
            //Asociamos cada valor mediante la parametrización de la consulta.
            ps.setString(1,p.getNombre());
            ps.setString(2,p.getApellidos());
            ps.setString(3,p.getTelefono());
            ps.setString(4,p.getEmail());
            ps.setString(5,p.getDireccion());
            //Una vez hemos terminado la parametrización, ejecutamos la consulta.
            ps.executeUpdate();
            System.out.println("Fila insertada correctamente en base de datos");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
// Añade una nueva mascota en la base de datos.
    public static void copiarMascotas(Mascotas m){
        String consulta = "INSERT INTO MASCOTAS (idPropietario,nombre,raza,fecha_nacimiento,peso,genero) VALUES (?,?,?,?,?,?)";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(consulta);
            //Asociamos cada valor mediante la parametrización de la consula.
            ps.setInt(1,m.getIdPropietario());
            ps.setString(2,m.getNombre());
            ps.setString(3,m.getRaza());
            ps.setDate(4,new Date(m.getFechaNacimiento().getTime()));
            ps.setDouble(5,m.getPeso());
            ps.setString(6,m.getGenero());
            //Una vez hemos terminado la parametrización, ejecutamos la consulta.
            ps.executeUpdate();
            System.out.println("Fila insertada correctamente en base de datos");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

// Actualiza los datos de una mascota existente.
    public static void modificarMascotas(Mascotas m){
        String consulta = "UPDATE MASCOTAS SET idPropietario=?,nombre=?,raza=?,fecha_nacimiento=?,peso=?,genero=? WHERE id=?";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(consulta);
            //Asociamos cada valor mediante la parametrización de la consula.
            ps.setInt(1,m.getIdPropietario());
            ps.setString(2,m.getNombre());
            ps.setString(3,m.getRaza());
            ps.setDate(4,new Date(m.getFechaNacimiento().getTime()));
            ps.setDouble(5,m.getPeso());
            ps.setString(6,m.getGenero());
            ps.setInt(7,m.getId());
            ps.executeUpdate();
            //Una vez hemos terminado la parametrización, ejecutamos la consulta.
            System.out.println("Fila actualizada correctamente en base de datos");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

// Actualiza los datos de un propietario existente.
    public static void modificarPropietario(Propietarios p){
        String consulta = "UPDATE PROPIETARIOS SET nombre=?,apellidos=?,telefono=?,email=?,direccion=? WHERE id_propietario=?";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(consulta);
            //Asociamos cada valor mediante la parametrización de la consula.
            ps.setString(1,p.getNombre());
            ps.setString(2,p.getApellidos());
            ps.setString(3,p.getTelefono());
            ps.setString(4,p.getEmail());
            ps.setString(5,p.getDireccion());
            ps.setInt(6,p.getId());
            ps.executeUpdate();
            //Una vez hemos terminado la parametrización, ejecutamos la consulta.
            System.out.println("Fila actualizada correctamente en base de datos");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

// Elimina un propietario de la base de datos.
    public static void eliminarPropietario(Propietarios p){
        String consulta = "DELETE FROM PROPIETARIOS WHERE id_propietario=?";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(consulta);
            //Asociamos cada valor mediante la parametrización de la consula.
            ps.setInt(1,p.getId());
            ps.executeUpdate();
            //Una vez hemos terminado la parametrización, ejecutamos la consulta.
            System.out.println("Fila insertada correctamente en base de datos");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

// Elimina una mascota de la base de datos.
    public static void eliminarMascotas(Mascotas m){
        String consulta = "DELETE FROM MASCOTAS WHERE id=?";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(consulta);
            //Asociamos cada valor mediante la parametrización de la consula.
            ps.setInt(1,m.getId());
            ps.executeUpdate();
            //Una vez hemos terminado la parametrización, ejecutamos la consulta.
            System.out.println("Fila insertada correctamente en base de datos");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

// Consulta todos los historiales médicos.
    public static ArrayList<HistorialMedico> consultarHistorialesMedicos()  {
        ArrayList<HistorialMedico> hMedico = new ArrayList<>();
        String consulta = "SELECT * FROM historialmedico";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(consulta);
            //Asociamos cada valor mediante la parametrización de la consula.
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println();
                //Añadamos al arraylist el objeto instanciado en función de los datos de la fila (DataSet / ResultSet)
                hMedico.add(new HistorialMedico(rs.getInt("id"),
                        rs.getInt("id_mascota"),
                        rs.getDate("fecha_consulta"),
                        rs.getDate("proxima_visita"),
                        rs.getString("motivoVisita"),
                        rs.getString("diagnostico"),
                        rs.getString("tratamiento"),
                        rs.getString("observaciones")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return hMedico;
    }

// Elimina un historial médico.
    public static void eliminarHMedico(HistorialMedico h){
        String consulta = "DELETE FROM historialmedico WHERE id=?";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        //Asociamos cada valor mediante la parametrización de la consula.
        try{
            ps = conn.prepareStatement(consulta);
            ps.setInt(1,h.getId());
            ps.executeUpdate();
            System.out.println("Fila insertada correctamente en base de datos");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
// Añade un nuevo historial médico.
    public static void copiarHMedico(HistorialMedico h){
        String consulta = "INSERT INTO historialmedico " +
                "(id_mascota,fecha_consulta,motivoVisita,diagnostico,tratamiento,proxima_visita,observaciones) " +
                "VALUES (?,?,?,?,?,?,?)";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(consulta);
            //Asociamos cada valor mediante la parametrización de la consula.
            ps.setInt(1,h.getIdMascota());
            ps.setDate(2,new Date(h.getFechaConsulta().getTime()));
            ps.setString(3,h.getMotivoVisita());
            ps.setString(4,h.getDiagnostico());
            ps.setString(5,h.getTratamiento());
            ps.setDate(6,new Date(h.getFechaVisita().getTime()));
            ps.setString(7,h.getObservaciones());
            ps.executeUpdate();
            System.out.println("Fila insertada correctamente en base de datos");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

// Modifica un historial médico existente.
    public static void modificarHistMedico(HistorialMedico h){
        String consulta = "UPDATE historialmedico SET id_mascota=?,fecha_consulta=?,motivoVisita=?,diagnostico=?,tratamiento=?, proxima_visita=?, observaciones=? WHERE id=?";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        //Asociamos cada valor mediante la parametrización de la consula.
        try{
            ps = conn.prepareStatement(consulta);
            ps.setInt(1,h.getIdMascota());
            ps.setDate(2,new Date(h.getFechaConsulta().getTime()));
            ps.setString(3,h.getMotivoVisita());
            ps.setString(4,h.getDiagnostico());
            ps.setString(5,h.getTratamiento());
            ps.setDate(6,new Date(h.getFechaVisita().getTime()));
            ps.setString(7,h.getObservaciones());
            ps.setInt(8,h.getId());
            ps.executeUpdate();
            System.out.println("Fila actualizada correctamente en base de datos");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
