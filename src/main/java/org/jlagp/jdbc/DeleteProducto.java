package org.jlagp.jdbc;

import org.jlagp.jdbc.models.Productos;
import org.jlagp.jdbc.repository.ProductoRepositorioImp;
import org.jlagp.jdbc.repository.Repository;
import org.jlagp.jdbc.util.ConexionJava;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class DeleteProducto {
    public static void main(String[] args) {
        try (Connection con = ConexionJava.getInstance();
        ) {
            Repository<Productos> repositorio = new ProductoRepositorioImp();
            System.out.println("============= Listar por Id =============");
            repositorio.listar().forEach(System.out::println);


            System.out.println("============= Obtener por Id =============");
            System.out.println(repositorio.porId(2L));

            System.out.println("============= Eliminar =============");
            repositorio.eliminar(1L);
            System.out.println("Producto eliminado");
            repositorio.listar().forEach(System.out::println);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
