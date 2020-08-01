package jm.stockx.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SellingInfoPutDto {

    @NonNull
    private Long id;
    private LocalDateTime orderDate;
    private Double price;
}