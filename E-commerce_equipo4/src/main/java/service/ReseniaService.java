/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package service;

import dao.IProductoDAO;
import dao.IReseniaDAO;
import dao.IUsuarioDAO;
import dao.ProductoDAO;
import dao.ReseniaDAO;
import dao.UsuarioDAO;
import dto.ReseniaDTO;
import java.util.List;
import java.util.stream.Collectors;
import mapper.ReseniaMapper;
import models.Producto;
import models.Resenia;
import models.Usuario;

/**
 *
 * @author Abraham Coronel
 */
public class ReseniaService implements IReseniaService {
    private final IReseniaDAO reseniaDAO = new ReseniaDAO();
    private final IProductoDAO productoDAO = new ProductoDAO();
    private final IUsuarioDAO usuarioDAO = new UsuarioDAO();
    private final ReseniaMapper reseniaMapper = new ReseniaMapper();

    @Override
    public void crearResenia(ReseniaDTO reseniaDTO, String correoUsuario) {
        if (reseniaDTO == null) {
            throw new IllegalArgumentException("La reseña no puede ser nula.");
        }
        if (reseniaDTO.getId() == null) {
            throw new IllegalArgumentException("El ID del producto es obligatorio para dejar una reseña.");
        }
        if (reseniaDTO.getComentario() == null || reseniaDTO.getComentario().trim().isEmpty()) {
            throw new IllegalArgumentException("El comentario de la reseña es obligatorio.");
        }
        if (reseniaDTO.getCalificacion() == null || reseniaDTO.getCalificacion() < 1 || reseniaDTO.getCalificacion() > 5) {
            throw new IllegalArgumentException("La calificación debe ser un número entre 1 y 5.");
        }
        if (correoUsuario == null || correoUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException("Se requiere la sesión de un usuario válido.");
        }

        Producto producto = productoDAO.buscarPorId(reseniaDTO.getId());
        if (producto == null) {
            throw new IllegalArgumentException("El producto que intentas reseñar no existe.");
        }

        Usuario usuario = usuarioDAO.buscarPorCorreo(correoUsuario);
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario referenciado no existe en la base de datos.");
        }

        Resenia reseniaEntity = reseniaMapper.toEntity(reseniaDTO);
        reseniaEntity.setProducto(producto);
        reseniaEntity.setUsuario(usuario);

        reseniaDAO.crearResenia(reseniaEntity);
    }

    @Override
    public List<ReseniaDTO> obtenerReseniasPorProducto(Long idProducto) {
        if (idProducto == null || idProducto <= 0) {
            throw new IllegalArgumentException("ID de producto inválido.");
        }
        
        List<Resenia> resenias = reseniaDAO.obtenerPorProducto(idProducto);
        
        return resenias.stream()
                .map(reseniaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReseniaDTO> obtenerTodas() {
        List<Resenia> todasLasResenias = reseniaDAO.obtenerTodas();
        
        return todasLasResenias.stream()
                .map(reseniaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarResenia(Long idResenia, Long idAdmin) {
        if (idAdmin == null) {
            throw new IllegalArgumentException("Acción denegada: Identificador de administrador no encontrado.");
        }
        if (idResenia == null || idResenia <= 0) {
            throw new IllegalArgumentException("ID de reseña inválido.");
        }
        
        Resenia resenia = reseniaDAO.buscarPorId(idResenia);
        if (resenia == null) {
            throw new IllegalArgumentException("La reseña que intentas eliminar no existe.");
        }
        
        reseniaDAO.eliminarResenia(idResenia);
    }
}
