/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import models.Resenia;
import util.JPAUtil;

/**
 *
 * @author Abraham Coronel
 */
public class ReseniaDAO implements IReseniaDAO {

    @Override
    public void crearResenia(Resenia resenia) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(resenia);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al guardar reseña", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Resenia buscarPorId(Long idResenia) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.find(Resenia.class, idResenia);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Resenia> obtenerPorProducto(Long idProducto) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Resenia> query = em.createQuery(
                    "SELECT r FROM Resenia r WHERE r.producto.idProducto = :idProducto",
                    Resenia.class
            );
            query.setParameter("idProducto", idProducto);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Resenia> obtenerTodas() {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Resenia> query = em.createQuery(
                    "SELECT r FROM Resenia r",
                    Resenia.class
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminarResenia(Long idResenia) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            Resenia resenia = em.find(Resenia.class, idResenia);
            if (resenia != null) {
                em.remove(resenia);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al eliminar reseña", e);
        } finally {
            em.close();
        }
    }

}
