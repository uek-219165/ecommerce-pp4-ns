package pl.jkanclerz.productcatalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductStorage {
    Map<String, ProductData> products;

    public ProductStorage() {
        this.products = new HashMap<>();
    }

    public void save(ProductData productData) {
        products.put(productData.getId(),
                productData);
    }

    public ProductData loadById(String productId) {
        return products.get(productId);
    }

    public List<ProductData> allPublishedProducts() {
        return products.values().stream()
                .filter(item -> item.isOnline())
                .collect(Collectors.toList());
    }
}
