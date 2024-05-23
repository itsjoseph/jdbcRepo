package org.jlagp.jdbc;

import org.jlagp.jdbc.models.Categorias;
import org.jlagp.jdbc.models.Productos;
import org.jlagp.jdbc.repository.ProductoRepositorioImp;
import org.jlagp.jdbc.repository.Repository;
import org.jlagp.jdbc.util.ConexionJava;

import java.sql.*;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try (Connection con = ConexionJava.getInstance();
        ) {
            Repository<Productos> repositorio = new ProductoRepositorioImp();
            System.out.println("============= Listar por Id =============");
            repositorio.listar().forEach(System.out::println);


            System.out.println("============= Obtener por Id =============");
            System.out.println(repositorio.porId(2L));

            System.out.println("============= Insertar nuevo =============");
            Productos productos = new Productos();
            productos.setId(8L);
            productos.setNombre("Teclado mecanico Corsair");
            productos.setPrecio(350);
            productos.setFechaRegistro(new Date());
            /*Categorias categorias = new Categorias();
            categorias.setId(3L);
            productos.setCategoria(categorias);
            */
            repositorio.guardar(productos);
            System.out.println("Producto modificado con exito");
            System.out.println("Listado");
            repositorio.listar().forEach(System.out::println);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}