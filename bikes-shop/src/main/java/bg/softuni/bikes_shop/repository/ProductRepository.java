package bg.softuni.bikes_shop.repository;

import bg.softuni.bikes_shop.model.entity.ProductEntity;

import bg.softuni.bikes_shop.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Override
    Optional<ProductEntity> findById(Long aLong);

    @Query(value = "SELECT DISTINCT category FROM products "
            , nativeQuery = true)
    List<String> getDistinctCategories();

    @Query(value = "SELECT * FROM shop.products WHERE  composite_name IS NOT NULL"
            , nativeQuery = true)
    Page<ProductEntity> findAllProductsWithCompositeNameNotNull(Pageable pageable);
        List<ProductEntity> findAllByCompositeNameNull();
    Page<ProductEntity> findByCategory(Pageable pageable, String name);

    Optional<ProductEntity> findByCompositeName(String compositeName);

    Optional<ProductEntity> findByName(String name);

    @Query(value="SELECT *  FROM shop.products WHERE MATCH (category, name, description) against( ? ) AND composite_name IS NOT NULL ",
            nativeQuery = true)
    Page<ProductEntity> findAllByKeyword(String keyword, Pageable pageable);
}
