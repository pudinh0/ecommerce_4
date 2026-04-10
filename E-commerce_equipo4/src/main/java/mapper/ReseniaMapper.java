/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import dto.ReseniaDTO;
import java.time.LocalDate;
import models.Resenia;

/**
 *
 * @author Abraham Coronel
 */
public class ReseniaMapper {

    private final UsuarioMapper usuarioMapper = new UsuarioMapper();
    private final ProductoMapper productoMapper = new ProductoMapper();

    public ReseniaDTO toDTO(Resenia entity) {
        if (entity == null) return null;
        
        ReseniaDTO dto = new ReseniaDTO();
        dto.setId(entity.getIdResenia());
        dto.setComentario(entity.getComentario());
        dto.setCalificacion(entity.getCalificacion());
        
        if (entity.getFechaPublicacion()!= null) {
            dto.setFecha(entity.getFechaPublicacion());
        }
        
        if (entity.getProducto() != null) {
            dto.setProducto(productoMapper.toDTO(entity.getProducto()));
        }
        
        if (entity.getUsuario() != null) {
            dto.setUsuario(usuarioMapper.toDTO(entity.getUsuario()));
        }
        
        return dto;
    }

    public Resenia toEntity(ReseniaDTO dto) {
        if (dto == null) return null;
        
        Resenia entity = new Resenia();
        entity.setIdResenia(dto.getId());
        entity.setComentario(dto.getComentario());
        entity.setCalificacion(dto.getCalificacion());
        
        if (dto.getFecha() != null) {
            entity.setFechaPublicacion(dto.getFecha());
        } else {
            entity.setFechaPublicacion(LocalDate.now());
        }
        
        return entity;
    }
}
