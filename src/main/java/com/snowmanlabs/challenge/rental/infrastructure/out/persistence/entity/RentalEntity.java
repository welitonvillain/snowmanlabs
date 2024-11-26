package com.snowmanlabs.challenge.rental.infrastructure.out.persistence.entity;

import com.snowmanlabs.challenge.rental.domain.entity.Rental;
import com.snowmanlabs.challenge.rental.domain.type.ItemType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
@Table(name = "rentals")
@NoArgsConstructor
@AllArgsConstructor
public class RentalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_type")
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @Column(name = "username")
    private String username;

    @Column(name = "rental_date")
    private LocalDateTime rentalDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    public Rental toDomain() {
        return new Rental(this.id, this.itemId, this.itemType, this.username, this.rentalDate, this.returnDate);
    }

    public static RentalEntity toEntity(final Rental rental) {
        return new RentalEntity(rental.id(), rental.itemId(), rental.itemType(),
                rental.username(), rental.rentalDate(), rental.returnDate());
    }

}
