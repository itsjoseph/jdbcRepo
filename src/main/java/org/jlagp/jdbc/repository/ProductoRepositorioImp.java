package org.jlagp.jdbc.repository;

import org.jlagp.jdbc.models.Productos;
import org.jlagp.jdbc.util.ConexionJava;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                Productos p = new Productos();
                p.setId(rs.getLong(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getInt(3));
                p.setFechaRegistro(rs.getDate(4));
                listado.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listado;
    }

    @Override
    public Object porId(Long id) {
        return null;
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
}
