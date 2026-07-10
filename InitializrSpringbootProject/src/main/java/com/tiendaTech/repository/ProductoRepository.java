
package com.tiendaTech.repository;

import com.tiendaTech.domain.Producto;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    public List<Producto> findByActivoTrue();

    //Consulta derivada que recupera los producto de un rango de precio y los ordena por precio ascendentemente
    public List<Producto> findByPrecioBetweenOrderByPrecioAsc(BigDecimal precioInf, BigDecimal precioSup);

    //Consulta JPQL que recupera los producto de un rango de precio y los ordena por precio ascendentemente
    @Query(value = "SELECT p FROM Producto p WHERE p.precio BETWEEN :precioInf AND :precioSup ORDER BY p.precio ASC")
    public List<Producto> consultaJPQL(BigDecimal precioInf, BigDecimal precioSup);

    //Consulta SQL que recupera los producto de un rango de precio y los ordena por precio ascendentemente
    @Query(nativeQuery = true,
            value = "SELECT * FROM producto p WHERE p.precio BETWEEN :precioInf AND :precioSup ORDER BY p.precio ASC")
    public List<Producto> consultaSQL(BigDecimal precioInf, BigDecimal precioSup);
    
    @Query(value = "SELECT p FROM Producto p WHERE p.descripcion =:descripcion ORDER BY p.precio ASC")
    public List<Producto> consultaPorDescripcionJPQL(@Param("descripcion") String descripcion);
    
}
