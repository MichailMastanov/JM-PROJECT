package jm.stockx;

import jm.stockx.dto.bid.BidDto;
import jm.stockx.dto.bid.BidPostDto;
import jm.stockx.entity.Bid;

import java.util.List;

public interface BidService {

    List<Bid> getAll();

    BidDto getBidDtoByBidId(Long id);

    BidDto getBidDtoByItemNameAndUserName(String itemName, String userName);

    void create(Bid bid);

    void create(BidPostDto bidPostDto);

    void update(Bid bid);

    void delete(Long id);

    Boolean isBidExist(Long id);
}
