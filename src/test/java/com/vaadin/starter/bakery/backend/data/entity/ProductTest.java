package com.vaadin.starter.bakery.backend.data.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test class for {@link Product}.
 * <p>
 * Verifies that the {@code equals()} method of the {@link Product} entity
 * behaves correctly by comparing objects with different and identical attributes.
 * </p>
 */
public class ProductTest {

    /**
     * Tests the correctness of the {@link Product#equals(Object)} implementation.
     * <p>
     * Ensures that two Product instances are not equal if their names differ,
     * and become equal once their name and price match.
     * </p>
     */
    @Test
    public void equalsTest() {
        Product o1 = new Product();
        o1.setName("name");
        o1.setPrice(123);

        Product o2 = new Product();
        o2.setName("anothername");
        o2.setPrice(123);

        Assertions.assertNotEquals(o1, o2);

        o2.setName("name");
        Assertions.assertEquals(o1, o2);
    }
}