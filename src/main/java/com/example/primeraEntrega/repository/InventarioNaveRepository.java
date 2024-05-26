package com.example.primeraEntrega.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.primeraEntrega.model.InventarioNave;

public interface InventarioNaveRepository extends JpaRepository<InventarioNave, Long> {

    // @Transactional
    // @Modifying
    /*
     * @Query("UPDATE Persona p SET p.nombre = :firstName WHERE p.id = :id")
     * int updatePersonFirstName(@Param("id") Long id, @Param("firstName") String
     * firstName);
     */

    // Page<InformacionVentaProductoDTO>
    // findAllByNombreStartingWithIgnoreCase(String nombre,
    // org.springframework.data.domain.Pageable pageable);

    /*
     * @Query("SELECT inv FROM InventarioNave inv WHERE inv.nave.id = :id")
     * List<InventarioNave> buscarProductos(@Param("id") Long id);
     */
    @Query("SELECT inv FROM InventarioNave inv WHERE inv.nave.nombre = :nombre")
    List<InventarioNave> buscarProductosPorNombreNave(@Param("nombre") String nombreNave);

    /*
     * @Query("SELECT prod FROM InventarioNave inv JOIN inv.productos prod WHERE inv.nave.nombre = :nombre"
     * )
     * List<Producto> findByNombreNave(@Param("nombre") String nombreNave);
     */

}
