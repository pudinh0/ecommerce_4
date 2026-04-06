/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import models.Resenia;

/**
 *
 * @author Abraham Coronel
 */
public interface IReseniaDAO {

    void crearResenia(Resenia resenia);
    
    Resenia buscarPorId(Long idResenia);
    
    List<Resenia> obtenerPorProducto(Long idProducto);
    
    List<Resenia> obtenerTodas();
    
    void eliminarResenia(Long idResenia);
}
