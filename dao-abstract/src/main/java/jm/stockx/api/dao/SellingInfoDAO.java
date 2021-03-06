package jm.stockx.api.dao;

import jm.stockx.dto.sellingInfo.ItemTopInfoDto;
import jm.stockx.dto.sellingInfo.SellerTopInfoDto;
import jm.stockx.dto.sellingInfo.SellingInfoDto;
import jm.stockx.dto.sellingInfo.SellingItemDto;
import jm.stockx.entity.Item;
import jm.stockx.entity.SellingInfo;
import jm.stockx.enums.ItemCategory;

import java.time.LocalDateTime;
import java.util.List;

public interface SellingInfoDAO extends GenericDao<SellingInfo, Long> {
    Double getAverageSalesValue();

    SellingInfoDto getSellingInfoDtoBySellingInfoId(Long id);

    List<SellerTopInfoDto> getSellerTopInfoDto();

    int getCountSalesForPeriod(LocalDateTime beginningPeriod, LocalDateTime endPeriod);

    List<ItemTopInfoDto> getItemTopInfoDto(int maxResult);

    List<SellingItemDto> getAllSellingItemDtoToCurrentDate(Long itemId);

    List<Item> getTopItemByPeriodAndCategory(LocalDateTime beginningPeriod,
                                            LocalDateTime endPeriod,
                                            ItemCategory itemCategory,
                                            int limit);

    List<SellingItemDto> getSellingItemDtoByPeriodAndItemId(LocalDateTime begin, LocalDateTime end, Long itemId);
}
