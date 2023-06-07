package com.example.backendsynergyproject.models;

import com.example.backendsynergyproject.models.Integration;
import com.example.backendsynergyproject.models.Version;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {


    @Test
    void testIntegration() {
        Integration integration = new Integration();
        integration.setName("TestIntegration");
        integration.setBudget(1000);
        integration.setNoOfMembers(5);
        integration.addVersion(new Version());

        assertEquals("TestIntegration", integration.getName());
        assertEquals(1000, integration.getBudget());
        assertEquals(5, integration.getNoOfMembers());
        assertEquals(1, integration.getVersionList().size());
    }

}
