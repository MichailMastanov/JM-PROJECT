package jm.stockx.dto;

import jm.stockx.entity.TokenRecovery;
import jm.stockx.entity.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TokenRecoveryPutDto {

    private Long id;
    private User user;
    private String hash;
    private String hashEmail;
    private Date startTime;

    public TokenRecoveryPutDto(TokenRecovery tokenRecovery) {
        this.id = tokenRecovery.getId();
        this.user = tokenRecovery.getUser();
        this.hash = tokenRecovery.getHash();
        this.hashEmail = tokenRecovery.getHashEmail();
        this.startTime = tokenRecovery.getStartTime();
    }

}