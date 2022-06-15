package com.graphhopper.storage;

import com.thoughtworks.xstream.XStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TestStorablePropertiesRickGen {
    static XStream xStream = new XStream();

    private <T> T deserializeObjectFromString(String serializedObjectString) {
        return (T) xStream.fromXML(serializedObjectString);
    }

    private <T> T deserializeObjectFromFile(String serializedObjectFilePath) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File serializedObjectFile = new File(classLoader.getResource(serializedObjectFilePath).getFile());
        Scanner scanner = new Scanner(serializedObjectFile);
        String serializedObjectString = scanner.useDelimiter("\\A").next();
        return (T) xStream.fromXML(serializedObjectString);
    }

    private DataAccess insertPrivateMockField_da_InStorableProperties(StorableProperties receivingObject) throws Exception {
        DataAccess mockDataAccess = Mockito.mock(DataAccess.class);
        Field fieldToMock = receivingObject.getClass().getDeclaredField("da");
        fieldToMock.setAccessible(true);
        fieldToMock.set(receivingObject, mockDataAccess);
        return mockDataAccess;
    }

    @Test
    @DisplayName("loadExisting with OO - loadExisting(), getCapacity(), getBytes(long,byte[],int)")
    public void test_loadExisting_OO_cb1af87a0d974113b153c916318e45b4() throws Exception {
        // Arrange
        StorableProperties receivingObject = deserializeObjectFromFile("com.graphhopper.storage.StorableProperties.loadExisting1-receiving.xml");
        DataAccess mockDataAccess = insertPrivateMockField_da_InStorableProperties(receivingObject);
        Mockito.when(mockDataAccess.loadExisting()).thenReturn(true);
        Mockito.when(mockDataAccess.getCapacity()).thenReturn(32768L);
        // Act
        boolean actual = receivingObject.loadExisting();
        // Assert
        Assertions.assertEquals(true, actual);
    }

    @Test
    @DisplayName("loadExisting with PO - loadExisting(), getCapacity(), getBytes(long,byte[],int)")
    public void test_loadExisting_PO_cb1af87a0d974113b153c916318e45b4() throws Exception {
        // Arrange
        StorableProperties receivingObject = deserializeObjectFromFile("com.graphhopper.storage.StorableProperties.loadExisting1-receiving.xml");
        DataAccess mockDataAccess = insertPrivateMockField_da_InStorableProperties(receivingObject);
        Mockito.when(mockDataAccess.loadExisting()).thenReturn(true);
        Mockito.when(mockDataAccess.getCapacity()).thenReturn(32768L);
        // Act
        receivingObject.loadExisting();
        // Assert
        Mockito.verify(mockDataAccess, Mockito.atLeastOnce()).loadExisting();
        Mockito.verify(mockDataAccess, Mockito.atLeastOnce()).getCapacity();
        Mockito.verify(mockDataAccess, Mockito.atLeastOnce()).getBytes(eq(0L),   (byte[]) any(),   eq(32768));
    }

    @Test
    @DisplayName("loadExisting with CO - loadExisting(), getCapacity(), getBytes(long,byte[],int)")
    public void test_loadExisting_CO_cb1af87a0d974113b153c916318e45b4() throws Exception {
        // Arrange
        StorableProperties receivingObject = deserializeObjectFromFile("com.graphhopper.storage.StorableProperties.loadExisting1-receiving.xml");
        DataAccess mockDataAccess = insertPrivateMockField_da_InStorableProperties(receivingObject);
        Mockito.when(mockDataAccess.loadExisting()).thenReturn(true);
        Mockito.when(mockDataAccess.getCapacity()).thenReturn(32768L);
        // Act
        receivingObject.loadExisting();
        // Assert
        InOrder orderVerifier = Mockito.inOrder(mockDataAccess, mockDataAccess, mockDataAccess);
        orderVerifier.verify(mockDataAccess, Mockito.times(1)).loadExisting();
        orderVerifier.verify(mockDataAccess, Mockito.times(1)).getCapacity();
        orderVerifier.verify(mockDataAccess, Mockito.times(1)).getBytes(anyLong(), (byte[]) any(), anyInt());
    }
}