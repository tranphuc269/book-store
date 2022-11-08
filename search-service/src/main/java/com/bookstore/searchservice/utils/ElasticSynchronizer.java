package com.bookstore.searchservice.utils;

import com.bookstore.searchservice.domain.ProductEsModel;
import com.bookstore.searchservice.domain.dao.ProductDAO;
import com.bookstore.searchservice.repository.IProductDAORepository;
import com.bookstore.searchservice.repository.IProductEsRepository;
import com.bookstore.searchservice.repository.IProductEsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ElasticSynchronizer {

    private final IProductDAORepository productDAORepository;
    private final IProductEsRepository productEsRepository;

    private static final Logger LOG = LoggerFactory.getLogger(ElasticSynchronizer.class);

    @Autowired
    public ElasticSynchronizer(IProductDAORepository productDAORepository,
                               IProductEsRepository productEsRepository) {
        this.productDAORepository = productDAORepository;
        this.productEsRepository = productEsRepository;
    }

    @Scheduled(cron = "0 */3 * * * *")
    @Transactional
    public void sync() {
        LOG.info("Start Syncing - {}", LocalDateTime.now());
        this.syncProducts();
        LOG.info(" End Syncing - {}", LocalDateTime.now());
    }

    private void syncProducts() {
//        Specification<ProductDAO> userSpecification = (root, criteriaQuery, criteriaBuilder) ->
//        Specification<ProductDAO> userSpecification = (root, criteriaQuery, criteriaBuilder) ->
//        getModificationDatePredicate(criteriaBuilder, root);
        List<ProductDAO> productDAOList = new ArrayList<>();
        productDAOList = productDAORepository.findAll();
        productEsRepository.deleteAll();
        for (ProductDAO productDAO : productDAOList) {
            LOG.info("Syncing Product - {}", productDAO.getProductId());
            productEsRepository.save(ProductEsModel
                    .builder()
                    .productId(productDAO.getProductId())
                    .producerId(productDAO.getProducerId())
                    .categoryId(productDAO.getCategoryId())
                    .price(productDAO.getPrice())
                    .productName(productDAO.getProductName())
                    .productDescription(productDAO.getDescription())
                    .images(productDAO.getListImages())
                    .availableItemCount(productDAO.getAvailableItemCount())
                    .build());
        }
    }

    private static Predicate getModificationDatePredicate(CriteriaBuilder cb, Root<?> root) {
        Expression<Timestamp> currentTime;
        currentTime = cb.currentTimestamp();
        Expression<Timestamp> currentTimeMinus = cb.literal(new Timestamp(System.currentTimeMillis() -
                (Constants.INTERVAL_IN_MILLISECOND)));
        return cb.between(root.<Date>get(Constants.MODIFICATION_DATE),
                currentTimeMinus,
                currentTime
        );
    }
}