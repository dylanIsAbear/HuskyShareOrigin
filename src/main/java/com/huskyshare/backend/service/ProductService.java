package com.huskyshare.backend.service;

import com.huskyshare.backend.dao.ProductDao;
import com.huskyshare.backend.dao.ProductPictureDao;
import com.huskyshare.backend.entity.Product;
import com.huskyshare.backend.entity.ProductPictures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;
    @Autowired
    ProductPictureDao productPictureDao;

    public List<Product> searchByDescription(String key){return productDao.searchByDescription(key);}

    public List<Product> searchByTitle(String key){return productDao.searchByTitle(key);}

    public List<Product> searchByChildTitle(String key){return productDao.searchByChildTitle(key);}

    public List<Product> searchBySeller(Integer uid){return productDao.findByUid(uid);}

    public List<Product> searchByBuyer(Integer uid){return productDao.findByBuyerId(uid);}

    @Transactional
    public Product save(Product product){ return productDao.save(product);}

    @Transactional
    public ProductPictures savePicture(ProductPictures picture){return productPictureDao.save(picture);}
}
