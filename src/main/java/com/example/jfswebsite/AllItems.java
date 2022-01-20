package com.example.jfswebsite;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;

import java.util.ArrayList;
import java.util.Arrays;

@ManagedBean
@SessionScoped
public class AllItems {

    public AllItems() {
        initList();

    }



    private ArrayList<Item> items;

    private ItemType itemType;

    public ArrayList<Item> getItems() {
        if(itemType == null || itemType.equals(ItemType.All))
        {
            return items;
        }

        // Otherwise we choose only items of selected type and return them
        ArrayList<Item> newItems = new ArrayList<>();

        for(Item item : items)
        {
            if(item.getType().equals(itemType))
            {
                newItems.add(item);
            }
        }

        return newItems;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ItemType getItemType() {
                return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public ItemType[] getItemTypes()
    {
        return ItemType.values();
    }


    private void initList() {
        Item radio = new Item();
        radio.setType(ItemType.Electronic);
        radio.setName("Radio");
        radio.setPrice(50);

        Item keyboard = new Item();
        keyboard.setType(ItemType.Electronic);
        keyboard.setName("Keyboard");
        keyboard.setPrice(120);

        Item camera = new Item();
        camera.setType(ItemType.Electronic);
        camera.setName("Camera");
        camera.setPrice(336);

        Item bread = new Item();
        bread.setType(ItemType.Food);
        bread.setName("Bread");
        bread.setPrice(7);

        Item apple = new Item();
        apple.setType(ItemType.Food);
        apple.setName("Apple");
        apple.setPrice(1);

        Item cake = new Item();
        cake.setType(ItemType.Food);
        cake.setName("Cake");
        cake.setPrice(20);

        Item trumpet = new Item();
        trumpet.setType(ItemType.Instrument);
        trumpet.setName("Trumpet");
        trumpet.setPrice(500);

        Item kazoo = new Item();
        kazoo.setType(ItemType.Instrument);
        kazoo.setName("Kazoo");
        kazoo.setPrice(15);

        Item piano = new Item();
        piano.setType(ItemType.Instrument);
        piano.setName("Piano");
        piano.setPrice(1300);

        Item spiderman = new Item();
        spiderman.setType(ItemType.Toy);
        spiderman.setName("Spiderman");
        spiderman.setPrice(50);

        Item hotWheels = new Item();
        hotWheels.setType(ItemType.Toy);
        hotWheels.setName("Hot Wheels Car");
        hotWheels.setPrice(67);

        items = new ArrayList<Item>(Arrays.asList(radio, keyboard, camera, bread, apple, cake, trumpet, kazoo, piano, spiderman, hotWheels));
    }


}
