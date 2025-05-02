package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public AuthorModel create(AuthorModel entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        AuthorModel existingAuthor = entityManager.find(AuthorModel.class, entity.getId());
        if (existingAuthor != null) {
            existingAuthor.setName(entity.getName());
            return entityManager.merge(existingAuthor);
        }
        return entity;
    }

    //@DeleteNews we do not need it news will be deleted automatic
    @Override
    public boolean deleteById(Long id) {
        AuthorModel author = entityManager.find(AuthorModel.class, id);
        if (author != null) {
            entityManager.remove(author);
            return true;
        }
        return false;
    }

    //@Transactional
    @Override
    public boolean existById(Long id) {
        return entityManager.find(AuthorModel.class, id) != null;
    }

    //@Transactional
    public Optional<AuthorModel> findAuthorByNewsId(Long id) {
            TypedQuery<AuthorModel> typedQuery = entityManager.createQuery(
                    "SELECT n.author FROM NewsModel n WHERE n.id like :id", AuthorModel.class);
            typedQuery.setParameter("id", id);
            return Optional.ofNullable(typedQuery.getSingleResult());
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
