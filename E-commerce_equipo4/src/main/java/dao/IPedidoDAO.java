/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import models.Pedido;

/**
 *
 * @author Abraham Coronel
 */
public interface IPedidoDAO {

    void guardar(Pedido pedido);

    Pedido buscarPorId(Long idPedido);

    List<Pedido> buscarPorCorreoUsuario(String correoUsuario);
    List<Pedido> buscarPorIdUsuario(Long idUsuario);
    void actualizar(Pedido pedido);
    
    List<Pedido> obtenerTodos();
}
