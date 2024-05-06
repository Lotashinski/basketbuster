package com.github.lotashinski.basketbuster.productservice.service.jpa.impl;

import com.github.lotashinski.basketbuster.productservice.entity.Category;
import com.github.lotashinski.basketbuster.productservice.entity.Product;
import com.github.lotashinski.basketbuster.productservice.repository.ProductRepository;
import com.github.lotashinski.basketbuster.productservice.service.jpa.JpaProductService;
import com.github.lotashinski.basketbuster.productservice.service.jpa.criteria.JpaProductCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class JpaProductServiceImpl implements JpaProductService {

    private final ProductRepository productRepository;

    private final EntityManager entityManager;


    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product delete(Product product) {
        productRepository.delete(product);

        return product;
    }

    @Override
    public Optional<Product> get(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Slice<Product> getByCriteria(JpaProductCriteria criteria, Pageable pageable) {
        long totalProducts = getCount(criteria);
        List<Product> products = getProducts(criteria, pageable);

        return new PageImpl<>(products, pageable, totalProducts);
    }

    private long getCount(JpaProductCriteria criteria) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);

        Root<Product> product = query.from(Product.class);
        List<Predicate> predicates = getPredicates(criteria, query, builder, product);
        query.select(builder.count(product));
        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getSingleResult();
    }

    private List<Product> getProducts(JpaProductCriteria criteria, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> resultQuery = builder.createQuery(Product.class);
        Root<Product> product = resultQuery.from(Product.class);

        List<Predicate> predicates = getPredicates(criteria, resultQuery, builder, product);
        resultQuery.select(product);
        resultQuery.where(predicates.toArray(new Predicate[0]));

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        return entityManager.createQuery(resultQuery)
                .setMaxResults(pageSize)
                .setFirstResult(pageNumber * pageSize)
                .getResultList();
    }

    private static List<Predicate> getPredicates(JpaProductCriteria criteria,
                                                 CriteriaQuery<?> query,
                                                 CriteriaBuilder builder,
                                                 Root<Product> product) {

        List<Predicate> predicates = new ArrayList<>();

        String title = criteria.getTitle();
        if (title != null && !title.isBlank()) {
            predicates.add(builder.like(product.get("title"), '%' + title + '%'));
        }

        List<Category> categories = criteria.getCategories();
        if (categories != null && !categories.isEmpty()) {
            Subquery<Category> subquery = query.subquery(Category.class);
            Root<Category> category = subquery.from(Category.class);
            Join<Category, Product> categoryProduct = category.join("products");

            subquery.select(category)
                    .where(
                            category.in(categories),
                            builder.equal(categoryProduct.get("id"), product.get("id"))
                    );

            predicates.add(builder.exists(subquery));
        }
        return predicates;
    }

}
