package com.sda.claudiu.petclinicmanagementsystem.repository;

import com.sda.claudiu.petclinicmanagementsystem.utils.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseRepositoryImpl<T> implements BaseRepository<T> {
    private Class<T> entityClass;       // declaring the entity class

    public BaseRepositoryImpl(Class<T> entityClass) {       // creating the constructor for entity class
        this.entityClass = entityClass;                     // why we have to create here the constructor????????
    }

    @Override
    public Optional<T> findById(Integer id) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            T entity = session.find(entityClass, id);      // here we have to declare in find an entity class and another object, in this case, the second obj is id;
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void create(T entity) {
        Transaction transaction = null;

        try (Session session = SessionManager.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.save(entity);

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(T entity) {
        Transaction transaction = null;

        try (Session session = SessionManager.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.update(entity);

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(T entity) {
        Transaction transaction = null;

        try (Session session = SessionManager.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.delete(entity);

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            return session.createQuery("FROM " + entityClass.getName(), entityClass).list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
