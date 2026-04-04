/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import dto.CarritoDTO;
/**
 *
 * @author Abraham Coronel
 */
public interface ICarritoService {

    CarritoDTO obtenerCarrito(String correoUsuario);

    void agregarProducto(String correoUsuario, Long idProducto, int cantidad);

    void eliminarItem(String correoUsuario, Long idItem);

    void vaciarCarrito(String correoUsuario);
}
