/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import models.Carrito;
import util.JPAUtil;

/**
 *
 * @author Abraham Coronel
 */
public class CarritoDAO implements ICarritoDAO {

    @Override
    public void guardar(Carrito carrito) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(carrito);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al guardar el carrito", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Carrito carrito) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(carrito);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar el carrito", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Carrito buscarPorCorreoUsuario(String correoUsuario) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Carrito> query = em.createQuery(
                    "SELECT c FROM Carrito c LEFT JOIN FETCH c.items i "
                    + "WHERE c.usuario.correo = :correo",
                    Carrito.class
            );
            query.setParameter("correo", correoUsuario);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public void vaciarCarrito(Long idCarrito) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();

            em.createQuery("DELETE FROM ItemCarrito i WHERE i.carrito.idCarrito = :idCarrito")
                    .setParameter("idCarrito", idCarrito)
                    .executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al vaciar el carrito", e);
        } finally {
            em.close();
        }
    }

}
