package utc2.itk62.store.services;

import utc2.itk62.store.models.ImportGoodsDetail;
import utc2.itk62.store.repositories.ImportGoodsDetailRepo;

import java.util.List;

public class ImportGoodsDetailService {
    private static final ImportGoodsDetailRepo importGoodsDetailRepo = new ImportGoodsDetailRepo();

    public boolean createImportGoodsDetail(ImportGoodsDetail importGoodsDetail) {
        if(importGoodsDetailRepo.createImportGoodsDetail(importGoodsDetail) <=0 ) {
            return  false;
        }
        return true;
    }

    public List<ImportGoodsDetail> getImportDetailByIdImport(int id) {
        return  importGoodsDetailRepo.getAllInvoicesDetailByImportGoodsId(id);
    }
}
