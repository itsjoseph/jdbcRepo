package org.jlagp.jdbc.repository;

import org.jlagp.jdbc.models.Productos;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    List<T> listar() throws SQLException;

    T porId(Long id) throws SQLException;

    void guardar(Productos t);

    void modificar(T t);

    void eliminar(long id);
}
