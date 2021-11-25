package ua.alevel.commons.util;

import ua.alevel.commons.entity.BaseEntity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class BaseEntityParser {

    private BaseEntityParser() {
    }

    public static <E extends BaseEntity> List<String> convertToString(E entity) {
        try {
            Field[] entityFields = entity.getClass().getDeclaredFields();
            Field[] baseEntityFields = entity.getClass().getSuperclass().getDeclaredFields();
            List<String> fieldValues = new ArrayList<>();
            for (Field field : baseEntityFields) {
                field.setAccessible(true);
                fieldValues.add(String.valueOf(field.get(entity)));
            }
            for (Field field : entityFields) {
                field.setAccessible(true);
                fieldValues.add(String.valueOf(field.get(entity)));
            }
            return fieldValues;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static <E extends BaseEntity> List<String> getFieldNames(Class<E> clazz) {
        Field[] entityFields = clazz.getDeclaredFields();
        Field[] baseEntityFields = clazz.getSuperclass().getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();
        for (Field field : baseEntityFields) {
            fieldNames.add(field.getName());
        }
        for (Field field : entityFields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    public static <E extends BaseEntity> List<E> convertToEntities(List<List<String>> strings, Class<E> entityClass) {
        try {
            Field[] baseEntityFields = entityClass.getSuperclass().getDeclaredFields();
            Field[] entityFields = entityClass.getDeclaredFields();
            Field[] fields = Arrays.copyOf(baseEntityFields, baseEntityFields.length + entityFields.length);
            System.arraycopy(entityFields, 0, fields, baseEntityFields.length, entityFields.length);
            Constructor<?> constructor = entityClass.getDeclaredConstructor();
            List<E> entities = new ArrayList<>();
            for (List<String> string : strings) {
                E entity = (E) constructor.newInstance();
                for (int fieldIndex = 0; fieldIndex < fields.length; fieldIndex++) {
                    fields[fieldIndex].setAccessible(true);
                    String currentField = string.get(fieldIndex);
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    if (isUUID(currentField)) {
                        UUID uuid = UUID.fromString(currentField);
                        fields[fieldIndex].set(entity, uuid);
                    } else if (isLocalDate(currentField, dateFormatter)) {
                        LocalDate localDate = LocalDate.parse(currentField, dateFormatter);
                        fields[fieldIndex].set(entity, localDate);
                    } else {
                        fields[fieldIndex].set(entity, string.get(fieldIndex));
                    }
                }
                entities.add(entity);
            }
            return entities;
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private static boolean isUUID(String maybeUuid) {
        try {
            UUID.fromString(maybeUuid);
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    private static boolean isLocalDate(String maybeLocalDate, DateTimeFormatter dateFormatter) {
        try {
            dateFormatter.parse(maybeLocalDate);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
