/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import models.Carrito;

/**
 *
 * @author Abraham Coronel
 */
public interface ICarritoDAO {

    void guardar(Carrito carrito);

    void actualizar(Carrito carrito);

    Carrito buscarPorCorreoUsuario(String correoUsuario);

    Carrito buscarPorIdUsuario(Long idUsuario);

    void vaciarCarrito(Long idCarrito);
}
