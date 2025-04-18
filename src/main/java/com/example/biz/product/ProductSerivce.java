package com.example.biz.product;

import java.util.List;

public interface ProductSerivce {
    boolean insert(ProductVO vo);
    boolean update(ProductVO vo);
    boolean delete(ProductVO vo);
    ProductVO getProduct(ProductVO vo);
    List<ProductVO> getProductList(ProductVO vo);
}
