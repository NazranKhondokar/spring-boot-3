package com.nazran.todo.utils;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static com.nazran.todo.utils.ResponseBuilder.error;
import static org.springframework.http.ResponseEntity.badRequest;

public class PaginationParameters {

    public static void getdata(Map<String, Object> map, Integer page, Long total, Integer size, List<?> lists) {
        Integer nextPage = ((page + 1) < total) ? page + 1 : 0;
        Integer previousPage = ((page - 1) >= 0) ? page - 1 : 0;
        map.put("currentPage", page);
        map.put("nextPage", nextPage);
        map.put("previousPage", previousPage);
        map.put("size", size);
        map.put("total", total);
        map.put("lists", lists);
    }

    public static ResponseEntity<JSONObject> getNotAcceptable(Integer page, Integer size) {
        if (size != null) {
            if (size < 0 | size > 50) {
                return badRequest().body(error(HttpStatus.NOT_ACCEPTABLE).getJson());
            }
        }

        if (page != null) {
            if (page < 0) {
                return badRequest().body(error(HttpStatus.NOT_ACCEPTABLE).getJson());
            }
        }
        return null;
    }
}