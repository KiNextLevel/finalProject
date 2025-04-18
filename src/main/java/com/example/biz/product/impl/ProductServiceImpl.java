package com.example.biz.product.impl;

import com.example.biz.product.ProductSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductSerivce {
    @Autowired
    private ProductDAO productDAO;

    @Override
    public boolean insert(ProductVO vo) {
        return productDAO.insert();
    }

    @Override
    public boolean update(ProductVO vo) {
        return productDAO.update();
    }

    @Override
    public boolean delete(ProductVO vo) {
        return productDAO.delete();
    }

    @Override
    public ProductVO getProduct(ProductVO vo) {
        return productDAO.getProduct();
    }

    @Override
    public List<ProductVO> getProductList(ProductVO vo) {
        return productDAO.getProductList();
    }
}
