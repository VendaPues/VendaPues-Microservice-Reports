package org.venda.pues.reportapi.repository;

import models.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<ProductDocument, String> {

    List<ProductDocument> findByStockEquals(int stock);
}
