package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Huespedes;
import entity.Pais;
import entity.Reserva;
import entity.TipoPago;
import util.ConexionMySql;

public class HotelAction {

	// Definir los metodos que interactuaran con la BD:
	
	// huespedes
	// insertar, eliminar, listar, actualizar, buscarXId, obtener
	public int grabarHuesped(Huespedes x) {
		int grabar = -1;

		Connection cn = new ConexionMySql().getConexion();

		PreparedStatement pstm = null;

		try {

			cn.setAutoCommit(false);

			String sql = "insert into tb_huespedes values(null,?,?,?,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, x.getNombre());
			pstm.setString(2, x.getApellido());
			pstm.setDate(3, x.getFec_nac());
			pstm.setInt(4, x.getPais());
			pstm.setInt(5, x.getTelefono());
			pstm.setInt(6, x.getReserva());

			grabar = pstm.executeUpdate();

			cn.commit();

		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return grabar;
	}

	public ArrayList<Huespedes> listarHuesped() {
		ArrayList<Huespedes> lista = new ArrayList<Huespedes>();

		Connection cn = new ConexionMySql().getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM tb_huespedes";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Huespedes obj = new Huespedes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4),
						rs.getInt(5), rs.getInt(6), rs.getInt(7));

				lista.add(obj);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return lista;
	}

	public ArrayList<Huespedes> listarHuespedXApellido(String x) {
		ArrayList<Huespedes> lista = new ArrayList<Huespedes>();

		Connection cn = new ConexionMySql().getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			// Definir la sentencia SQL
			String sql = "SELECT * FROM tb_huespedes where apellido = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, x);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Huespedes obj = new Huespedes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4),
						rs.getInt(5), rs.getInt(6), rs.getInt(7));

				lista.add(obj);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;
	}

	public int actualizarHuesped(Huespedes x) {
		int salida = 0;

		// 1. Declarar variables
		Connection cn = new ConexionMySql().getConexion();
		PreparedStatement pstm = null;

		// 2. Control de excepciones
		try {

			cn.setAutoCommit(false);

			// 3. Definir la sentencia SQL
			String sql = "UPDATE tb_huespedes SET " + " nombre = ?, apellido  = ?, fec_nac= ?,"
					+ " nacionalidad  = ?, telefono = ?,reservas = ?" + " WHERE id_huespedes = ?";

			// 5. Obtener un PreparedStatement de la conexión
			pstm = cn.prepareStatement(sql);

			pstm.setString(1, x.getNombre());
			pstm.setString(2, x.getApellido());
			pstm.setDate(3, x.getFec_nac());
			pstm.setInt(4, x.getPais());
			pstm.setInt(5, x.getTelefono());
			pstm.setInt(6, x.getReserva());
			pstm.setInt(8, x.getCodigo());

			// 7. Ejecutar
			salida = pstm.executeUpdate();

			cn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Cerrar la conexion y otros objetos
			try {
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return salida;
	}

	public int eliminarHuesped(int id) {
		int salida = -1;

		Connection cn = new ConexionMySql().getConexion();
		PreparedStatement pstm = null;

		try {
			String sql = "DELETE FROM tb_huespedes WHERE id_huespedes = ?";

			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, id);

			salida = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return salida;
	}

	// buscar por un valor
	public Huespedes buscarHuesped(int codigo) {
		Huespedes obj = new Huespedes();

		Connection cn = new ConexionMySql().getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM tb_huespedes WHERE id_huespedes = " + "'" + codigo + "'";

			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next()) {
				obj.setCodigo(rs.getInt("codigo"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return obj;
	}

	// llenar los campos con datos de la bd
	public Huespedes obtenerHuesped(int codigo) {
		Huespedes obj = new Huespedes();

		Connection cn = new ConexionMySql().getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM tb_huespedes WHERE id_huespedes = ?";

			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, codigo);
			rs = pstm.executeQuery();
			while (rs.next()) {
				obj.setCodigo(rs.getInt("codigo"));
				obj.setNombre(rs.getString("nombre"));
				obj.setApellido(rs.getString("apellido"));
				obj.setFec_nac(rs.getDate("fec_nac"));
				obj.setPais(rs.getInt("pais"));
				obj.setTelefono(rs.getInt("telefono"));
				obj.setReserva(rs.getInt("reserva"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	
	
	// reservas
	// insertar, eliminar, listar, actualizar, buscarXId
	public int grabarReserva(Reserva x) {
		int grabar = -1;

		Connection cn = new ConexionMySql().getConexion();

		PreparedStatement pstm = null;

		try {

			cn.setAutoCommit(false);

			String sql = "insert into tb_reservas values(null,?,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setDate(1, x.getFec_ent());
			pstm.setDate(2, x.getFec_sal());
			pstm.setInt(3, x.getValor());
			pstm.setInt(4, x.getPago());

			grabar = pstm.executeUpdate();

			cn.commit();

		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return grabar;
	}

	public ArrayList<Reserva> listarReserva() {
		ArrayList<Reserva> lista = new ArrayList<Reserva>();

		Connection cn = new ConexionMySql().getConexion();

		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM tb_reservas";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Reserva obj = new Reserva(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getInt(4), rs.getInt(5));

				lista.add(obj);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return lista;
	}

	public ArrayList<Reserva> listarReservaXID(int id) {
		ArrayList<Reserva> lista = null;

		Connection cn = new ConexionMySql().getConexion();

		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			// Definir la sentencia SQL
			String sql = "SELECT * FROM tb_reservas where id_reservas = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();

			lista = new ArrayList<Reserva>();
			while (rs.next()) {
				Reserva obj = new Reserva(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getInt(4), rs.getInt(5));

				lista.add(obj);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;
	}

	public int actualizarReserva(Reserva x) {
		int salida = 0;

		// 1. Declarar variables
		Connection cn = new ConexionMySql().getConexion();
		PreparedStatement pstm = null;

		// 2. Control de excepciones
		try {
			// 3. Definir la sentencia SQL
			String sql = "UPDATE tb_reservas SET " + " fec_entrada = ?, fec_salida  = ?, valor = ?," + " tipo_pago = ?"
					+ " WHERE id_reservas  = ?";

			// 4. Obtener un PreparedStatement de la conexión
			pstm = cn.prepareStatement(sql);

			pstm.setDate(1, x.getFec_ent());
			pstm.setDate(2, x.getFec_sal());
			pstm.setInt(3, x.getValor());
			pstm.setInt(4, x.getPago());
			pstm.setInt(5, x.getCodigo());

			// 7. Ejecutar
			salida = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Cerrar la conexion y otros objetos
			try {
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return salida;
	}

	public int eliminarReserva(int id) {
		int salida = -1;

		Connection cn = new ConexionMySql().getConexion();
		;
		PreparedStatement pstm = null;

		try {
			String sql = "DELETE FROM tb_reservas WHERE id_reservas = ?";

			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, id);

			salida = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return salida;
	}

	// buscar por un valor
	public Reserva buscarReserva(int codigo) {
		Reserva obj = new Reserva();

		Connection cn = new ConexionMySql().getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM tb_reservas WHERE id_huespedes = " + "'" + codigo + "'";

			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next()) {
				obj.setCodigo(rs.getInt("codigo"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return obj;
	}

	// llenar los campos con datos de la bd
	public Reserva obtenerReserva(int codigo) {
		Reserva obj = new Reserva();

		Connection cn = new ConexionMySql().getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM tb_reservas WHERE id_reservas = ?";

			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, codigo);
			rs = pstm.executeQuery();
			while (rs.next()) {
				obj.setCodigo(rs.getInt("codigo"));
				obj.setFec_ent(rs.getDate("fec_ent"));
				obj.setFec_sal(rs.getDate("fec_sal"));
				obj.setValor(rs.getInt("valor"));
				obj.setPago(rs.getInt("pago"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	
	
	// listar
	public ArrayList<Pais> listarPais() {
		ArrayList<Pais> lista = new ArrayList<Pais>();

		Connection cn = new ConexionMySql().getConexion();

		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM tb_pais";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Pais obj = new Pais(rs.getInt(1), rs.getString(2));

				lista.add(obj);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return lista;
	}

	public ArrayList<TipoPago> listarTipoPago() {
		ArrayList<TipoPago> lista = new ArrayList<TipoPago>();

		Connection cn = new ConexionMySql().getConexion();

		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM tb_tipo_pago";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				TipoPago obj = new TipoPago(rs.getInt(1), rs.getString(2));

				lista.add(obj);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return lista;
	}

}
