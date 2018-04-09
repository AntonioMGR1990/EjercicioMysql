package introduccionjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import introduccionjdbc.datos.Usuario;

public class UsuarioJDBC {
	private final String SQL_INSERT = "INSERT INTO usuario(Nick,Password,Descripcion)values(?,?,?)";
	private final String SQL_UPDATE = "UPDATE usuario set Nick=?,Password=?,Descripcion=? where id_usuario=?";
	private final String SQL_DELETE = "DELETE FROM usuario where id_usuario=?";
	private final String SQL_SELECT = "SELECT id_usuario,Nick,Password,Descripcion FROM usuario ORDER BY id_usuario";

	public int insert(Usuario user) {

		int rows = 0;
		try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);) {
			int index = 1;
			stmt.setString(index++, user.getNick());
			stmt.setString(index++, user.getPassword());
			stmt.setString(index++, user.getDescripcion());
			System.out.println("Ejecuantado:insert");
			rows = stmt.executeUpdate();
			System.out.println("Registros afectados: " + rows);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return rows;

	}

	
	public int update(int id_usuario, String Nick, String Password) {

		int rows = 0;
		try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE);) {
			int index = 1;
			stmt.setString(index++, Nick);
			stmt.setString(index++, Password);
			stmt.setInt(index++, id_usuario);
			System.out.println("Ejecuantado:update");
			rows = stmt.executeUpdate();
			System.out.println("Registros afectados: " + rows);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return rows;
		

	}

	public int delete(int id_usuario) {

		int rows = 0;
		try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_DELETE);) {

			stmt.setInt(1, id_usuario);
			System.out.println("Ejecuantado:delete");
			rows = stmt.executeUpdate();
			System.out.println("Registros afectados: " + rows);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return rows;

	}

	public List<Usuario> select() {
		List<Usuario> usuarios = new ArrayList<>();
		Usuario usuario = null;

		try (Connection conn = Conexion.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				usuario = new Usuario();
				int index = 1;
				usuario.setId_usuario(rs.getInt(index++));
				usuario.setNick(rs.getString(index++));
				usuario.setPassword(rs.getString(index++));
				usuarios.add(usuario);
			}
			System.out.println("Ejecuantado:select");

		} catch (Exception e) {

			e.printStackTrace();
		}
		return usuarios;

	}

	// etc...

}
