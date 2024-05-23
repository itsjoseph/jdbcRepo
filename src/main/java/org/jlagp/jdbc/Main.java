package org.jlagp.jdbc;

import org.jlagp.jdbc.models.Productos;
import org.jlagp.jdbc.repository.ProductoRepositorioImp;
import org.jlagp.jdbc.repository.Repository;
import org.jlagp.jdbc.util.ConexionJava;

import java.sql.*;
import java.util.Map;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try (Connection con = ConexionJava.getInstance();
        ) {
            Repository<Productos> repositorio = new ProductoRepositorioImp();
            repositorio.listar().forEach(p -> System.out.println(p.getNombre()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}