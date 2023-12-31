package com.yutsuki.stock.service;

import com.yutsuki.stock.entity.St_account;
import com.yutsuki.stock.entity.St_category;
import com.yutsuki.stock.model.request.CategoryCreateRequest;
import com.yutsuki.stock.model.request.DeleteCategoryRequest;
import com.yutsuki.stock.model.request.NotificationsRequest;
import com.yutsuki.stock.repository.AccountRepository;
import com.yutsuki.stock.repository.CategoryRepository;
import com.yutsuki.stock.utils.Comm;
import com.yutsuki.stock.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

import static com.yutsuki.stock.com.HandleResponse.success;
import static com.yutsuki.stock.exception.HandleException.exception;

@Service
@Slf4j
public class CategoryService {
    @Resource
    private CategoryRepository categoryRepository;
    @Resource
    private NotificationService notificationService;
    @Resource
    private AccountRepository accountRepository;


    public ResponseEntity<?> createCategory(CategoryCreateRequest request, Authentication authentication) {
        Long uid = Comm.getUid(authentication);
        St_account account = this.accountRepository.findById(uid).get();
        if (request.getCategoryName().isEmpty()) {
            log.warn("Category::(block). [invalid category name]. req: {}", request);
        }
        St_category entity = new St_category();
        entity.setCategoryName(request.getCategoryName());
        St_category res = categoryRepository.save(entity);
        String msg = String.format("Category created By name:%s CategoryName: %s ", account.getUsername(), res.getCategoryName());
        this.notificationService.sendNotification(NotificationsRequest.builder().notifications(msg).build());
        return ResponseEntity.ok(res);
    }

    public ResponseEntity<?> findCategoryList() {
        return ResponseEntity.ok(MapperUtils.mapList(categoryRepository.findAll(), St_category.class));
    }

    public ResponseEntity<?> deleteCategory(DeleteCategoryRequest request, Authentication authentication) {
        Long uid = Comm.getUid(authentication);
        St_account account = this.accountRepository.findById(uid).get();
        Optional<St_category> optionalCategory = this.categoryRepository.findById(request.getCategoryId());
        if (!optionalCategory.isPresent()) {
            log.warn("Category::(block). invalid categoryId. req: {}", request);
            return exception("invalid categoryId");
        }
        St_category category = optionalCategory.get();
        categoryRepository.deleteById(request.getCategoryId());
        String msg = String.format("Stock deleted By name:%s StockName: %s ", account.getUsername(), category.getCategoryName());
        this.notificationService.sendNotification(NotificationsRequest.builder().notifications(msg).build());
        return success();
    }

}
