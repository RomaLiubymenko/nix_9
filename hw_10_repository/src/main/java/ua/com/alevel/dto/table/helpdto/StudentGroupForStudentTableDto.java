package ua.com.alevel.dto.table.helpdto;

import ua.com.alevel.dto.table.AbstractTableDto;

public class StudentGroupForStudentTableDto extends AbstractTableDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentGroupForStudentTableDto{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
