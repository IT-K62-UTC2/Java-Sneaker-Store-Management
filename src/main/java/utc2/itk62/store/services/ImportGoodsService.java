package utc2.itk62.store.services;

import utc2.itk62.store.common.Paging;
import utc2.itk62.store.models.ImportGoods;
import utc2.itk62.store.repositories.ImportGoodsRepo;

import java.util.List;

public class ImportGoodsService {
    private static final ImportGoodsRepo importGoodsRepo = new ImportGoodsRepo();

//    public List<ImportGoods> getAllImportGoods() {
//        return  importGoodsRepo.getAll();;
//    }

    public ImportGoods createImportGoods(ImportGoods importGoods){
        return importGoodsRepo.createImportGoods(importGoods);
    }


    public List<ImportGoods> getAllImportGoods() {
        return importGoodsRepo.getAllImportGoods(new Paging(0, 0));
    }
}
