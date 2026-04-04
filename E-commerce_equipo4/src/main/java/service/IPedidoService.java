/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import dto.PedidoDTO;
import java.util.List;

/**
 *
 * @author Abraham Coronel
 */
public interface IPedidoService {

    void procesarCompra(String correoUsuario);

    PedidoDTO obtenerPedidoPorId(Long idPedido);

    List<PedidoDTO> obtenerHistorialUsuario(String correoUsuario);
}
