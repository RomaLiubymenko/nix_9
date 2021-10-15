package ua.com.alevel.service.implementation;

import ua.com.alevel.service.TaskService;

public class ThirdTaskServiceImpl  implements TaskService {

    @Override
    public String getSolutionForTaskByString(String stringToSearch) {
        return stringToSearch + "Third";
    }
}
