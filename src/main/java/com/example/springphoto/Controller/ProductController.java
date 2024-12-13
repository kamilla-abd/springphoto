package com.example.springphoto.Controller;

import com.example.springphoto.Model.Product;
import com.example.springphoto.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
@RequestMapping("/products")
public class ProductController {

    private final String uploadDir = "images/";

    @Autowired
    private ProductService productService;

    @GetMapping("/show")
    public String showProductList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }

    @PostMapping("/save")
    public String saveProduct(
            @ModelAttribute("product") Product product,
            @RequestParam("image") MultipartFile image, Model model) throws Exception {

        System.out.println("dfghjikolpserdtfyguhijokpl");

        if (!image.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, image.getBytes());
            product.setImage(fileName);
        }

        productService.saveProduct(product);
        return "redirect:/products/show";
    }


//    @PostMapping("/products/save")
//    public String saveProduct(
//            @RequestParam("name") String name,
//            @RequestParam("price") double price,
//            @RequestParam("date") String date,
//            @RequestParam("image") String image) throws Exception {
//
//        Product product = new Product();
//        product.setName(name);
//        product.setPrice(price);
//        product.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
//        product.setImage(image);
//
//        productService.saveProduct(product);
//        return "redirect:/products";
//    }

//    @PostMapping("/create")
//    public String createProduct(
//            @Valid @ModelAttribute Product product,
//            BindingResult bindingResult) {
//        if (product.getImage().isEmpty()) {
//            bindingResult.addError(new FieldError("product", "image", "Image is required"));
//        }
//
//        if (bindingResult.hasErrors()) {
//            return "products/create";
//        }
//

//        String image = product.getImage();
//        Date date = new Date();
//        String storageFileName = date.getDate() + "_" + image.get
//
//        try {
//            String uploadDir = "public/images/";
//            Path uploadPath = Paths.get(uploadDir);
//
//            if (!Files.exists(uploadPath)) {
//                Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
//                        StandardCopyOption.REPLACE_EXISTING);
//            }
//        } catch (Exception e) {
//            System.out.println("Exception: " + e.getMessage());
//        }
//
//        return "redirect:/products";

}
