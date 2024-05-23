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
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                producto = crearProducto(rs);
            }
        }
        return producto;
    }

    @Override
    public void guardar(Object o) {

    }

    @Override
    public void modificar(Object o) {

    }

    @Override
    public void eliminar(long id) {

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
