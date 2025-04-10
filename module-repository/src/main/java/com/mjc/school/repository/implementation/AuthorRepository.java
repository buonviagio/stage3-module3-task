package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AuthorModel> readAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        System.out.println("Create Builder from Author Repository " + cb);
        CriteriaQuery<AuthorModel> cq = cb.createQuery(AuthorModel.class);
        Root<AuthorModel> root = cq.from(AuthorModel.class);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }


    @Override
    public Optional<AuthorModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(AuthorModel.class, id));
    }

    @Override
    @Transactional
    public AuthorModel create(AuthorModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public AuthorModel update(AuthorModel entity) {
        AuthorModel existingAuthor = entityManager.find(AuthorModel.class, entity.getId());
        if (existingAuthor != null) {
            existingAuthor.setName(entity.getName());
            return entityManager.merge(existingAuthor);
        }
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        AuthorModel author = entityManager.find(AuthorModel.class, id);
        if (author != null) {
            entityManager.remove(author);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean existById(Long id) {
        return entityManager.find(AuthorModel.class, id) != null;
    }

    // Additional method find by name
    public Optional<AuthorModel> findByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuthorModel> cq = cb.createQuery(AuthorModel.class);
        Root<AuthorModel> root = cq.from(AuthorModel.class);
        cq.where(cb.equal(root.get("name"), name));
        TypedQuery<AuthorModel> query = entityManager.createQuery(cq);
        return query.getResultStream().findFirst();
    }
}
