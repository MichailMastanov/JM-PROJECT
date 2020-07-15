package jm.stockx;

import jm.stockx.entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> getAll();

    Item get(Long id);

    void create(Item item);

    void update(Item item);

    void delete(Long id);

    Item getItemByName(String name);

    void addItemImage(Long id, byte[] array);

    byte[] getItemImage(Long id);
}