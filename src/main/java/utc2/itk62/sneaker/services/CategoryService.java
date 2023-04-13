package utc2.itk62.sneaker.services;

import utc2.itk62.sneaker.common.Paging;
import utc2.itk62.sneaker.models.Category;
import utc2.itk62.sneaker.models.Staff;
import utc2.itk62.sneaker.repositories.CategoryRepo;

import java.util.List;

public class CategoryService {
    private static final CategoryRepo categoryRepo = new CategoryRepo();

    public List<Category> getAllCategory()    {
        return categoryRepo.getAllCategories(new Paging(0,0));
    }
}
