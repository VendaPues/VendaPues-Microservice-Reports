package org.venda.pues.reportapi.repository;

import models.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductDocument, String> {

    ProductDocument findByIdEqualsAndStockEquals(String id, int stock);
}
