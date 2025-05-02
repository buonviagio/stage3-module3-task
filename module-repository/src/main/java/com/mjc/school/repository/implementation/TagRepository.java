package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepository  implements BaseRepository<TagModel, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TagModel> readAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TagModel> cq = cb.createQuery(TagModel.class);
        Root<TagModel> root = cq.from(TagModel.class);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Optional<TagModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(TagModel.class, id));
    }

    @Override
    public TagModel create(TagModel entity) {
        //entityManager.persist(entity);
        return entityManager.merge(entity);
    }

    @Override
    public TagModel update(TagModel entity) {
        return entityManager.merge(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        TagModel tag = entityManager.find(TagModel.class, id);
        if (tag != null) {
            entityManager.remove(tag);
            return true;
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return entityManager.find(TagModel.class, id) != null;
    }

    public List<TagModel> findTagByNewsId(Long id) {
        TypedQuery<TagModel> typedQuery = entityManager.createQuery(
                "SELECT t FROM NewsModel n JOIN n.tags t WHERE n.id = :id", TagModel.class);
        typedQuery.setParameter("id", id);
        List<TagModel> result = typedQuery.getResultList();
        System.out.println("RESULT -> "+ result.size());
        return result;
    }

    public List<TagModel> findByNewsId(Long newsId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TagModel> cq = cb.createQuery(TagModel.class);
        Root<TagModel> root = cq.from(TagModel.class);
        Join<TagModel, NewsModel> newsJoin = root.join("news");
        cq.where(cb.equal(newsJoin.get("id"), newsId));
        return entityManager.createQuery(cq).getResultList();
    }
}
