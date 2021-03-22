package com.shop.repositories;

import java.util.List;

public interface PermissionRepository { // thằng PermissionRepository này khác với bình thường là nó không extends từ JpaRepository
    void save(List<Long> roleIds, long userId);

    void delete(long id);
}
