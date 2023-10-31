package com.hungnmse160060.prm392_pe.repository;

import com.hungnmse160060.prm392_pe.service.ApiService;
import com.hungnmse160060.prm392_pe.service.BoMonService;

public class BoMonRepository {
    public static BoMonService getBoMonService() {
        return ApiService.getClient().create(BoMonService.class);
    }
}
