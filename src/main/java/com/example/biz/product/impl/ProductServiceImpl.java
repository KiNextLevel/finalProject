package com.example.biz.product.impl;

import com.example.biz.product.ProductSerivce;
import com.example.biz.product.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductSerivce {
    @Autowired
    private ProductDAO productDAO;

    @Override
    public boolean insert(ProductVO vo) {
        return productDAO.insert(vo);
    }

    @Override
    public boolean update(ProductVO vo) {
        return productDAO.update(vo);
    }

    @Override
    public boolean delete(ProductVO vo) {
        return productDAO.delete(vo);
    }

    @Override
    public ProductVO getProduct(ProductVO vo) {
        return productDAO.getProduct(vo);
    }

    @Override
    public List<ProductVO> getProductList(ProductVO vo) {
        return productDAO.getProductList(vo);
    }
}
