package productcatalog;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductCatalog {
    Map<String, ProductData> products;

    public ProductCatalog() {
        this.products = new HashMap<>();
    }
    public String addProduct(String id, String name) {
        ProductData productData = new ProductData(id, name);
        products.put(id, productData);
        return id;
    }
    public void publish(String productId) {
        ProductData loaded = products.get(productId);

        if (loaded.getPrice() == null) {
            throw new CantPublishProductException();
        }

        loaded.publish();
    }

    public List<ProductData> allPublishedProducts() {
        return products.values()
                .stream()
                .filter(item -> item.isOnline())
                .collect(Collectors.toList());
    }

    public void changePrice(String productId, BigDecimal newPrice) {
        ProductData loaded = findById(productId);
        loaded.changePrice(newPrice);
    }

    public ProductData findById(String productId) {
        return products.get(productId);
    }

    public void assignImage(String productId, String imageUrl) {
        ProductData loaded = findById(productId);
        loaded.assignImage(imageUrl);
    }
}
