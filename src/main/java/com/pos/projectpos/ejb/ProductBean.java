package com.pos.projectpos.ejb;

import com.pos.projectpos.common.ProductDto;
import com.pos.projectpos.common.ProductPhotoDto;
import com.pos.projectpos.entities.Product;
import com.pos.projectpos.entities.ProductPhoto;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ProductBean {
    private static final Logger LOG = Logger.getLogger(ProductBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    public List<ProductDto> findAllProducts() {
        LOG.info("findAllProducts");
        try {
            TypedQuery<Product> typedQuery = entityManager.createQuery("SELECT p FROM Product p", Product.class);
            List<Product> products = typedQuery.getResultList();
            return copyProductsToDto(products);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<ProductDto> copyProductsToDto(List<Product> products) {
        List<ProductDto> productdt = new ArrayList<ProductDto>();
        for (Product product : products) {
            productdt.add(new ProductDto(product.getId(), product.getName(), product.getCategory(), product.getPrice(), product.getQuantity()));
        }
        return productdt;
    }


    public void createProduct(String name, String category, String price, String quantity) {
        LOG.info("createProduct");
        Product product = new Product();
        product.setName(name);
        LOG.info(category);
        product.setCategory(category);
        LOG.info(price);
        product.setPrice(Double.parseDouble(price));
        LOG.info(quantity);
        product.setQuantity(Integer.parseInt(quantity));

        entityManager.persist(product);

    }

    public ProductDto findById(Long productId) {
        LOG.info("findById");
        Product product = entityManager.find(Product.class, productId);
        ProductDto productDto = new ProductDto(product.getId(), product.getName(), product.getCategory(), product.getPrice(), product.getQuantity());
        return productDto;
    }

    public void deleteProductsByIds(Collection<Long> productIds) {
        LOG.info("deleteProductsByIds");
        for (Long productId : productIds) {
            Product product = entityManager.find(Product.class, productId);
            entityManager.remove(product);
        }
    }

    public void addPhotoToProduct(Long productId, String filename, String fileType, byte[] fileContent) {
        LOG.info("addPhotoToProduct");
        ProductPhoto photo = new ProductPhoto();
        photo.setFilename(filename);
        photo.setFileType(fileType);
        photo.setFileContent(fileContent);
        Product product = entityManager.find(Product.class, productId);
        if (product.getPhoto() != null) {
            entityManager.remove(product.getPhoto());
        }
        product.setPhoto(photo);
        photo.setProduct(product);
        entityManager.persist(photo);
    }

    public ProductPhotoDto findPhotoByProductId(Integer productId) {
        List<ProductPhoto> photos = entityManager
                .createQuery("SELECT p FROM ProductPhoto p where p.product.id = :id", ProductPhoto.class)
                .setParameter("id", productId)
                .getResultList();
        if (photos.isEmpty()) {
            return null;
        }
        ProductPhoto photo = photos.get(0); // the first element
        return new ProductPhotoDto(photo.getId(), photo.getFilename(), photo.getFileType(),
                photo.getFileContent());
    }

    public void updateProduct(Long productId, String name, String category, Double price, int quantity) {
        LOG.info("updateProduct");

        Product product = entityManager.find(Product.class, productId);
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setQuantity(quantity);

    }
}