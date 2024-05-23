package org.jlagp.jdbc.repository;

import org.jlagp.jdbc.models.Productos;
import org.jlagp.jdbc.util.ConexionJava;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioImp implements Repository {

    private Connection getConnection() throws SQLException {
        return ConexionJava.getInstance();
    }

    @Override
    public List listar() throws SQLException {
        List<Productos> listado = new ArrayList<>();

        try (Statement statement = getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM productos");

            while (rs.next()) {
                Productos p = crearProducto(rs);
                listado.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listado;
    }

    @Override
    public Object porId(Long id) throws SQLException {
        Productos producto = null;

        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM productos WHERE idproductos= ?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    producto = crearProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Productos o) {
        String Ssql;
        if (o.getId() != null && o.getId() > 0) {
            Ssql = "UPDATE productos SET nombre = ?, precio = ?  WHERE idproductos = ?";
        } else {
            Ssql = "INSERT INTO productos (nombre, precio, fecha) values (?,?,?)";
        }

        try (PreparedStatement stmt = getConnection().prepareStatement(Ssql)) {
            stmt.setString(1, o.getNombre());
            stmt.setInt(2, o.getPrecio());
            if (o.getId() != null && o.getId() > 0) {
                stmt.setLong(3, o.getId());
            } else {
                stmt.setDate(3, new Date(o.getFechaRegistro().getTime()));
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(long id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM productos WHERE idproductos = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Productos crearProducto(ResultSet rs) throws SQLException {
        Productos p = new Productos();
        p.setId(rs.getLong(1));
        p.setNombre(rs.getString(2));
        p.setPrecio(rs.getInt(3));
        p.setFechaRegistro(rs.getDate(4));
        return p;
    }
}
