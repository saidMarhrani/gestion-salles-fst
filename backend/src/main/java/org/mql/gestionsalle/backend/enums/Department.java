package org.mql.gestionsalle.backend.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Department {

    MATH, SCIENCE, PHYSICS, CHEMISTRY, INFORMATIC, GEOLOGY;

    private String description;

    private Department() {
    }

    public static String findDepartment(String department) {

        Department departmentFound = Stream.of(Department.values()).filter(v -> v.toString().toLowerCase().equals(department)).findAny().orElseThrow(() -> new RuntimeException("Department not found !"));
        return departmentFound.toString().toLowerCase();
    }

    public static List<String> departmentList() {

        return Stream.of(Department.values()).map(Department::toString).collect(Collectors.toList());
    }

}
