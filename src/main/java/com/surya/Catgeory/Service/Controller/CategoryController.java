package com.surya.Catgeory.Service.Controller;

import com.surya.Catgeory.Service.Model.CategoryDto;
import com.surya.Catgeory.Service.Model.SaloonDto;
import com.surya.Catgeory.Service.Response.ApiResponse;
import com.surya.Catgeory.Service.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService serv;

    //save api
    @PostMapping("/saloon-owner")
    public ResponseEntity<ApiResponse<?>> save(@Valid @RequestBody CategoryDto catDto)
    {
        SaloonDto salDto = new SaloonDto();
        salDto.setId(1L);
        return serv.saveCategory(catDto,salDto);
    }
    @PutMapping("/update/id/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable Long id, @RequestBody CategoryDto dto)
    {
        SaloonDto salDto = new SaloonDto();
        salDto.setId(1L);
        return serv.updateCategory(id,dto,salDto);
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<?>> getAll()
    {
        return serv.getAllCategory();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<?>> getByID(@PathVariable Long id){
        return serv.getBySaloonId(id);
    }


    @DeleteMapping("/saloon-owner/i/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable Long id )
    {
        SaloonDto dto = new SaloonDto();
        dto.setId(1L);
        return serv.deleteCategory(id,dto);
    }





}
