/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import models.Pedido;
import util.JPAUtil;

/**
 *
 * @author Abraham Coronel
 */
public class PedidoDAO implements IPedidoDAO {

    @Override
    public void guardar(Pedido pedido) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al guardar el pedido", e);

        } finally {
            em.close();
        }
    }

    @Override
    public Pedido buscarPorId(Long idPedido) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();

        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p LEFT JOIN FETCH p.detalles WHERE p.idPedido = :id",
                    Pedido.class
            );
            query.setParameter("id", idPedido);

            return query.getResultStream().findFirst().orElse(null);

        } finally {
            em.close();
        }
    }

    @Override
    public List<Pedido> buscarPorCorreoUsuario(String correoUsuario) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();

        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.usuario.correo = :correo ORDER BY p.idPedido DESC",
                    Pedido.class
            );
            query.setParameter("correo", correoUsuario);

            return query.getResultList();

        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Pedido pedido) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(pedido);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar el estado del pedido", e);

        } finally {
            em.close();
        }
    }

}
