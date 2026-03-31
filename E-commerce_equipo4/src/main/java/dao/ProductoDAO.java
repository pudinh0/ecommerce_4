/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import models.Producto;
import util.JPAUtil;

/**
 *
 * @author Usuario
 */
public class ProductoDAO implements IProductoDAO{

    @Override
    public void crearProducto(Producto producto) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al guardar producto", e);
        } finally {
            em.close();
        }
    }
    
    @Override
    public Producto buscarPorId(Long id) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizarProducto(Producto producto) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar producto", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminarProducto(Long idProducto) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            Producto producto = em.find(Producto.class, idProducto);
            if (producto != null) {
                em.remove(producto);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al eliminar producto", e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Producto> obtenerTodosProductos() {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Producto> query = em.createQuery(
                    "SELECT u FROM Producto u",
                    Producto.class
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Producto> listaTop(int limite) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Producto> query = em.createQuery("SELECT u FROM Producto ORDER BY u.id DESC", Producto.class);
            query.setMaxResults(limite);
            return query.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Producto> listarPaginado(int pagina, int tamañoPag) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            int inicio = (pagina - 1) * tamañoPag;
            TypedQuery<Producto> query = em.createQuery("SELECT u FROM Producto ORDER BY u.id DESC", Producto.class);
            query.setFirstResult(inicio);
            query.setMaxResults(tamañoPag);
            return query.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public long contarProductos() {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Producto> query = em.createQuery("SELECT COUNT (u) FROM Producto", Producto.class);
            return query.getFirstResult();
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }
    
}
