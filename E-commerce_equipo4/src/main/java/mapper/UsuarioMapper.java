/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import dto.TipoUsuarioDTO;
import dto.UsuarioDTO;
import models.TipoUsuarioEnum;
import models.Usuario;

/**
 *
 * @author Abraham Coronel
 */
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getIdUsuario());
        dto.setNombres(usuario.getNombres());
        dto.setPrimerApellido(usuario.getPrimerApellido());
        dto.setSegundoApellido(usuario.getSegundoApellido());
        dto.setCorreo(usuario.getCorreo());
        dto.setTipoUsuario(TipoUsuarioDTO.valueOf(usuario.getTipoUsuario().name()));
        return dto;
    }

    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setIdUsuario(dto.getId());
        usuario.setNombres(dto.getNombres());
        usuario.setPrimerApellido(dto.getPrimerApellido());
        usuario.setSegundoApellido(dto.getSegundoApellido());
        usuario.setCorreo(dto.getCorreo());

        if (dto.getTipoUsuario() != null) {
            usuario.setTipoUsuario(TipoUsuarioEnum.valueOf(dto.getTipoUsuario().name()));
        }

        return usuario;
    }
}
