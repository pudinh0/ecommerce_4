/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import dto.ReseniaDTO;
import java.util.List;

/**
 *
 * @author Abraham Coronel
 */
public interface IReseniaService {

    void crearResenia(ReseniaDTO reseniaDTO, String correoUsuario);

    List<ReseniaDTO> obtenerReseniasPorProducto(Long idProducto);

    List<ReseniaDTO> obtenerTodas();

    void eliminarResenia(Long idResenia, Long idAdmin);
}
