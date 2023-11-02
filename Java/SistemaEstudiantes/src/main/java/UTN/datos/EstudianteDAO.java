package UTN.datos;

import UTN.dominio.Estudiante;
import static UTN.conexion.Conexion.getConnection;

import java.security.spec.RSAOtherPrimeInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
    //Metodo Listar
    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        //Creamos algunos objetos que son necesariois para comunicarnos con la base de datos
        PreparedStatement ps; //Envia la sentencia a la DDBB
        ResultSet rs; //Obtenemos el resultado de la DDBB
        //Creamos un objeto de tipo conexion
        Connection con = getConnection();
        String sql = "SELECT * FROM estudiantes2022 ORDER BY idestudiantes2022";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("idestudiantes2022"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                //Falta agregarlo a la lista
                estudiantes.add(estudiante);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un problema al seleccionar datos: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrió un error al cerrar la conexion: " + e.getMessage());
            }
        }//Fin finally
        return estudiantes;
    }//Fin metodo listar

    //Metodo por ID -> findById
    public boolean buscarEstudiantePorId(Estudiante estudiante) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        String sql = "SELECT * FROM estudiantes2022 WHERE idestudiantes2022=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if (rs.next()) {
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setNombre(rs.getString("apellido"));
                estudiante.setNombre(rs.getString("telefono"));
                estudiante.setNombre(rs.getString("email"));
                return true; //Se encontró un registro
            } //Fin del IF
        } catch (Exception e) {
            System.out.println("Ocurrió un error al buscar estudiante: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrió un error al cerrar la conexión: " + e.getMessage());
            } //Fin catch
        } //Fin finally
        return false;
    }

    public static void main(String[] args) {
        //Listar los estudiantes
        var estudianteDao = new EstudianteDAO();
        System.out.println("Listado de estudiantes: ");
        List<Estudiante> estudiantes = estudianteDao.listarEstudiantes();
        estudiantes.forEach(System.out::println); //Funcion lambda para imprimir

        //Buscar por id
        var estudiante1 = new Estudiante(1);
        System.out.println("Estudiantes antes de la busqueda: " + estudiante1);
        var encontrado = estudianteDao.buscarEstudiantePorId(estudiante1);
        if (encontrado)
            System.out.println("Estudiante encontrado: " + estudiante1);
        else
            System.out.println("No se encontró el estudiante: " + estudiante1.getIdEstudiante());
    }
}