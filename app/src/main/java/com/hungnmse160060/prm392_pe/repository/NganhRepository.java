package com.hungnmse160060.prm392_pe.repository;

import com.hungnmse160060.prm392_pe.services.ApiService;
import com.hungnmse160060.prm392_pe.services.NganhService;

public class NganhRepository {
    public static NganhService getNganhService() {
        return ApiService.getClient().create(NganhService.class);
    }
}
