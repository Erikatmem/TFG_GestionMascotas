package BBDD;

import Clases.HistorialMedico;
import Clases.Mascotas;
import Clases.Propietarios;

import java.sql.*;
import java.util.ArrayList;


public class Consultas {
    private static Connection conexion;


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
                propietario = new Propietarios(rs.getInt("id_propietario"),rs.getString("direccion"),rs.getString("email"),
                        rs.getString("telefono"),rs.getString("apellidos"),rs.getString("nombre")) ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return propietario;
    }
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
            mascota = new Mascotas(rs.getInt("id"),rs.getInt("idPropietario"),rs.getString("nombre"),rs.getString("raza"),
                    rs.getDouble("peso"),rs.getDate("fecha_nacimiento"),rs.getString("genero"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return mascota;
    }
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
                mascotas.add(new Mascotas(rs.getInt("id"),rs.getInt("idPropietario"),rs.getString("nombre"),rs.getString("raza"),
                        rs.getDouble("peso"),rs.getDate("fecha_nacimiento"),rs.getString("genero")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return mascotas;
    }
    public static void copiarPropietario(Propietarios p){
        String consulta = "INSERT INTO PROPIETARIOS (nombre,apellidos,telefono,email,direccion) VALUES (?,?,?,?,?)";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(consulta);
            ps.setString(1,p.getNombre());
            ps.setString(2,p.getApellidos());
            ps.setString(3,p.getTelefono());
            ps.setString(4,p.getEmail());
            ps.setString(5,p.getDireccion());
            ps.executeUpdate();
            System.out.println("Fila insertada correctamente en base de datos");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void copiarMascotas(Mascotas m){
        String consulta = "INSERT INTO MASCOTAS (idPropietario,nombre,raza,fecha_nacimiento,peso,genero) VALUES (?,?,?,?,?,?)";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(consulta);
            //ps.setInt(1,m.getId());
            ps.setInt(1,m.getIdPropietario());
            ps.setString(2,m.getNombre());
            ps.setString(3,m.getRaza());
            ps.setDate(4,new Date(m.getFechaNacimiento().getTime()));
            ps.setDouble(5,m.getPeso());
            ps.setString(6,m.getGenero());
            ps.executeUpdate();
            System.out.println("Fila insertada correctamente en base de datos");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void modificarMascotas(Mascotas m){
        String consulta = "UPDATE MASCOTAS SET idPropietario=?,nombre=?,raza=?,fecha_nacimiento=?,peso=?,genero=? WHERE id=?";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(consulta);
            ps.setInt(1,m.getIdPropietario());
            ps.setString(2,m.getNombre());
            ps.setString(3,m.getRaza());
            ps.setDate(4,new Date(m.getFechaNacimiento().getTime()));
            ps.setDouble(5,m.getPeso());
            ps.setString(6,m.getGenero());

            ps.setInt(7,m.getId());
            ps.executeUpdate();
            System.out.println("Fila actualizada correctamente en base de datos");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void modificarPropietario(Propietarios p){
        String consulta = "UPDATE PROPIETARIOS SET nombre=?,apellidos=?,telefono=?,email=?,direccion=? WHERE id_propietario=?";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(consulta);
            ps.setString(1,p.getNombre());
            ps.setString(2,p.getApellidos());
            ps.setString(3,p.getTelefono());
            ps.setString(4,p.getEmail());
            ps.setString(5,p.getDireccion());
            ps.setInt(6,p.getId());
            ps.executeUpdate();
            System.out.println("Fila actualizada correctamente en base de datos");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void eliminarPropietario(Propietarios p){
        String consulta = "DELETE FROM PROPIETARIOS WHERE id_propietario=?";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(consulta);
            ps.setInt(1,p.getId());
            ps.executeUpdate();
            System.out.println("Fila insertada correctamente en base de datos");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void eliminarMascotas(Mascotas m){
        String consulta = "DELETE FROM MASCOTAS WHERE id=?";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(consulta);
            ps.setInt(1,m.getId());
            ps.executeUpdate();
            System.out.println("Fila insertada correctamente en base de datos");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<HistorialMedico> consultarHistorialesMedicos()  {
        ArrayList<HistorialMedico> hMedico = new ArrayList<>();
        String consulta = "SELECT * FROM historialmedico";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(consulta);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println();
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
    public static void eliminarHMedico(HistorialMedico h){
        String consulta = "DELETE FROM historialmedico WHERE id=?";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(consulta);
            ps.setInt(1,h.getId());
            ps.executeUpdate();
            System.out.println("Fila insertada correctamente en base de datos");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void copiarHMedico(HistorialMedico h){
        String consulta = "INSERT INTO historialmedico " +
                "(id_mascota,fecha_consulta,motivoVisita,diagnostico,tratamiento,proxima_visita,observaciones) " +
                "VALUES (?,?,?,?,?,?,?)";
        Connection conn = getConexion();
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(consulta);
            //ps.setInt(1,m.getId());
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
    public static void modificarHistMedico(HistorialMedico h){
        String consulta = "UPDATE historialmedico SET id_mascota=?,fecha_consulta=?,motivoVisita=?,diagnostico=?,tratamiento=?, proxima_visita=?, observaciones=? WHERE id=?";
        Connection conn = getConexion();
        PreparedStatement ps = null;
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
