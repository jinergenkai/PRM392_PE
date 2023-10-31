package com.hungnmse160060.prm392_pe.repository;

import com.hungnmse160060.prm392_pe.service.ApiService;
import com.hungnmse160060.prm392_pe.service.SinhVienService;

public class SinhVienRepository {
    public static SinhVienService getSinhVienService() {
        return ApiService.getClient().create(SinhVienService.class);
    }
}
