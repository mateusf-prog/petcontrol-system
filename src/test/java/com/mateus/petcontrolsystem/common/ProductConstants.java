package com.mateus.petcontrolsystem.common;

import com.mateus.petcontrolsystem.dto.ProductDTO;
import com.mateus.petcontrolsystem.models.Product;
import com.mateus.petcontrolsystem.models.enums.Category;

import java.math.BigDecimal;

public class ProductConstants {

    public static Product provideValidProduct() {
        return new Product(null, "Vacina 1", "Labovet", BigDecimal.valueOf(60.0), "descrição do produto ....",
                20, Category.ACCESSORY, null);
    }

    public static Product provideInvalidProduct() {
        return new Product(null, null, null, null, null,
                null, null, null);
    }

    public static ProductDTO provideValidProductDTO() {
        return new ProductDTO(null, "Vacina 1", "Labovet", BigDecimal.valueOf(60.0), "descrição do produto ....",
                20, Category.ACCESSORY);
    }

    public static ProductDTO provideInvalidProductDTO() {
        return new ProductDTO(null, null, null, null, null,
                null, null);
    }
}
