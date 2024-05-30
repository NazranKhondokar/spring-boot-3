package com.nazran.todo.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;

/**
 * A common class to manage filter data
 * @author Sujoy Saha
 * @since 1.0
 */
public class ServiceHelper<T> {

    final Class<T> typeParameterClass;

    /**
     * A constructor when declare service helper
     * @param typeParameterClass which entity will use
     */
    public ServiceHelper(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    /**
     * Sort with attribute
     * @param sortBy sort by which attribute of entity
     * @param page traverse in which page
     * @param size number of items per request
     * @return corresponding pageable, or null
     */
    public Pageable getPageable(String sortBy, int page, int size) {
        Pageable pageable;
        if (sortBy.isEmpty()) pageable = PageRequest.of(page - 1, size);
        else {
            String[] parts = sortBy.split(",");
            pageable = PageRequest.of(page - 1, size, Sort.by(parts[0]));
            if (parts[1].equals("desc")) pageable = PageRequest.of(page - 1, size, Sort.by(parts[0]).descending());
        }
        return pageable;
    }

    /**
     * Page data converted to meta data
     * @param result items of page
     * @param page traverse in which page
     * @param size number of items per request
     * @return paginated data
     */
    public Map<String, Object> getList(Page<T> result, Integer page, Integer size) {
        Long total = result.getTotalElements();
        Map<String, Object> maps = new HashMap<>();
        PaginationParameters.getdata(maps, page, total, size, result.getContent());
        return maps;
    }
}
