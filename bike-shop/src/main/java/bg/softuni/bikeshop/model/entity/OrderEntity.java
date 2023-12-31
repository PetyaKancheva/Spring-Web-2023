package bg.softuni.bikeshop.model.entity;

import jakarta.persistence.*;
import org.hibernate.type.YesNoConverter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {
    private LocalDateTime created;
    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity buyer;
    @OneToMany()
    @JoinColumn(nullable = false)
    private List<LineItem> items = new ArrayList<>();
    @Convert(converter = YesNoConverter.class)
    private Boolean isSent;
    @Convert(converter = YesNoConverter.class)
    private Boolean isCancelled;
    private BigDecimal totalPrice;

    public LocalDateTime getCreated() {
        return created;
    }

    public OrderEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public UserEntity getBuyer() {
        return buyer;
    }

    public OrderEntity() {
    }

    public OrderEntity setBuyer(UserEntity buyer) {
        this.buyer = buyer;
        return this;
    }

    public List<LineItem> getItems() {
        return items;
    }

    public OrderEntity setItems(List<LineItem> items) {
        this.items = items;
        return this;
    }

    public Boolean getSent() {
        return isSent;
    }

    public OrderEntity setSent(Boolean sent) {
        isSent = sent;
        return this;
    }

    public Boolean getCancelled() {
        return isCancelled;
    }

    public OrderEntity setCancelled(Boolean cancelled) {
        isCancelled = cancelled;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderEntity setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}
