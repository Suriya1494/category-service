package com.surya.Catgeory.Service.Service;

import com.surya.Catgeory.Service.Model.Category;
import com.surya.Catgeory.Service.Model.CategoryDto;
import com.surya.Catgeory.Service.Model.SaloonDto;
import com.surya.Catgeory.Service.Repository.CategoryRepository;
import com.surya.Catgeory.Service.Response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository repo;
    private final ModelMapper mapper;

    public ResponseEntity<ApiResponse<?>> saveCategory(CategoryDto dto, SaloonDto saloondto)
    {
        Category cat=mapper.map(dto,Category.class);
        cat.setSaloonId(saloondto.getId());
        repo.save(cat);
        return ResponseEntity.ok(new ApiResponse<>("Successfully saved",cat));

    }
    public ResponseEntity<ApiResponse<?>> getAllCategory()
    {
       List<Category> res= repo.findAll();
       if(!res.isEmpty())
       {
           return ResponseEntity.ok(new ApiResponse<>("Successfully fetched",res));
       }
       else
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("No Result Found",null));
    }
    public ResponseEntity<ApiResponse<?>> getBySaloonId(Long id)
    {
        List<Category> res= repo.findBySaloonId(id);
        if(!res.isEmpty())
        {
            return ResponseEntity.ok(new ApiResponse<>("Successfully fetched",res));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("No Result Found",null));
    }

    public ResponseEntity<ApiResponse<?>> updateCategory(Long id,CategoryDto dto,SaloonDto saloonDto)
    {
        Optional<Category> res= repo.findById(id);
        if(res.isPresent()) {
            Category cat = mapper.map(res.get(), Category.class);
            cat.setId(id);
            repo.save(cat);
            return ResponseEntity.ok(new ApiResponse<>("Successfully saved", cat));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("No Result Found",null));

    }

    public ResponseEntity<ApiResponse<?>> deleteCategory(Long id,SaloonDto saloonId)
    {
        if(id.equals(saloonId.getId()))
        {
            Optional<Category> res= repo.findById(id);
            if(res.isPresent())
            {
                repo.delete(res.get());
                return ResponseEntity.ok(new ApiResponse<>("Successfully Deleted",res));
            }
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("No Result Found",null));

        }
        else
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ApiResponse<>("You don't have Access",null));
    }

}
