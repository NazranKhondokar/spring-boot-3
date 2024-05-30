package com.nazran.todo.helpers;

import com.nazran.todo.utils.PaginatedResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommonDataHelper {

    public void getCommonData(Integer page, Integer size, Map<String, ?> searchResult, PaginatedResponse response, List<?> list) {
        Integer currentPage = (Integer) searchResult.get("currentPage")/* + 1*/;
        Integer nextPage = (Integer) searchResult.get("nextPage")/* + 1*/;
        Integer previousPage = (Integer) searchResult.get("previousPage")/* + 1*/;
        Map<String, Object> meta = new HashMap<>();
        meta.put("currentPage", currentPage);
        meta.put("nextPage", nextPage);
        meta.put("previousPage", previousPage);
        meta.put("size", searchResult.get("size"));
        meta.put("total", searchResult.get("total"));
        response.setList(list);
        response.setMeta(meta);
    }

    public void setPageSize(Integer page, Integer size) {

        if (page <= 0 || size < 0) {
            page = 1;
            size = 10;
        } else if (size > 50) {
            size = 50;
        }

    }
}

