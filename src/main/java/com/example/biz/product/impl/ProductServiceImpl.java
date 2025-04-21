package com.example.biz.product.impl;

import com.example.biz.product.ProductSerivce;

import java.util.List;

public class ProductServiceImpl implements ProductSerivce {
    @Override
    public boolean insert(ProductVO vo) {
        return false;
    }

    @Override
    public boolean update(ProductVO vo) {
        return false;
    }

    @Override
    public boolean delete(ProductVO vo) {
        return false;
    }

    @Override
    public ProductVO getProduct(ProductVO vo) {
        return null;
    }

    @Override
    public List<ProductVO> getProductList(ProductVO vo) {
        return List.of();
    }
}
