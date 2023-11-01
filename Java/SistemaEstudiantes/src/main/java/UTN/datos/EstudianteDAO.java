package UTN.datos;

import UTN.dominio.Estudiante;
import static UTN.conexion.Conexion.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
    //Metodo Listar
    public List<Estudiante> listar(){
        List<Estudiante> estudiantes = new ArrayList<>();
            //Creamos algunos objetos que son necesariois para comunicarnos con la base de datos
        PreparedStatement ps; //Envia la sentencia a la DDBB
        ResultSet rs; //Obtenemos el resultado de la DDBB
        //Creamos un objeto de tipo conexion
        Connection con = getConnection();
        String sql = "SELECT * FROM estudiantes2022";
    }
}
