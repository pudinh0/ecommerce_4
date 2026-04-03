/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import dto.TipoUsuarioDTO;
import dto.UsuarioDTO;
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
}
