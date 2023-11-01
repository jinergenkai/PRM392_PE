package com.hungnmse160060.prm392_pe.repository;

import com.hungnmse160060.prm392_pe.service.ApiService;
import com.hungnmse160060.prm392_pe.service.NganhService;

public class NganhRepository {
    public static NganhService getNganhService() {
        return ApiService.getClient().create(NganhService.class);
    }
}
