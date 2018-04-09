package introduccionjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import introduccionjdbc.datos.LibroDTO;
import introduccionjdbc.datos.Persona;

/**
 * Created by Usuario on 04/04/2018.
 */

public class LibroDAO {

    private final String SQL_INSERT = "INSERT INTO persona(nombre,apellido)values(?,?)";
    private final String SQL_UPDATE = "UPDATE persona set nombre=?,apellido=? where id_persona=?";
    private final String SQL_DELETE = "DELETE FROM persona where id_persona=?";
    private final String SQL_SELECT = "SELECT id,ISBN,titulo,autor FROM libro ORDER BY id";

    public int insert(String nombre, String apellido) {

        int rows = 0;
        try  {
            Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setString(index++, nombre);
            stmt.setString(index++, apellido);
            System.out.println("Ejecuantado:insert");
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);

        } catch (Exception e) {

            e.printStackTrace();
        }
        return rows;

    }

    public int update(int id_persona, String nombre, String apellido) {

        int rows = 0;
        try  {
            Connection conn = Conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, nombre);
            stmt.setString(index++, apellido);
            stmt.setInt(index++, id_persona);
            System.out.println("Ejecuantado:update");
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return rows;

    }

    public int delete(int id_persona) {

        int rows = 0;
        try  {
            Connection conn = Conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id_persona);
            System.out.println("Ejecuantado:delete");
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return rows;

    }

    public List<LibroDTO> select() {
        List<LibroDTO> libros = new ArrayList<>();
        LibroDTO libro = null;

        try  {
            Connection conn = Conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                libro = new LibroDTO();
                int index = 1;
                libro.setId(rs.getInt(index++));
                libro.setIsbn(rs.getString(index++));
                libro.setTitulo(rs.getString(index++));
                libro.setAutor(rs.getString(index++));
                libros.add(libro);
            }
            System.out.println("Ejecuantado:select");

        } catch (Exception e) {

            e.printStackTrace();
        }
        return libros;

    }

    // etc...
}
