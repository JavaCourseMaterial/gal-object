package com.blacksteel.deneb.item.controller;

import com.blacksteel.deneb.item.model.Item;
import com.blacksteel.deneb.item.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Item controller - REST endpoints
 */
@RestController
@RequestMapping("/object")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Returns all items in repository.
     *
     * @param response HttpServletResponse
     * @return list of items
     */
    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public List<Item> getAllItems(HttpServletResponse response) {

        List<Item> returnValue = itemService.getAllItems();
        response.setStatus(HttpServletResponse.SC_OK);
        logger.info(new StringBuilder().append("Item returned [").append(returnValue.toString()).append("].").toString());
        return returnValue;

    }

    /**
     * Returns a single item by name.
     *
     * @param response HttpServletResponse
     * @param name     name
     * @return List of items
     */
    @RequestMapping(method = RequestMethod.GET, value = "/get/{name}")
    public List<Item> getItemsByName(HttpServletResponse response,
                                    @PathVariable("name") String name) {

        Item sourceItem = Item.builder().name(name).build();
        List<Item> returnValue = itemService.getAllItemsByName(sourceItem);
        response.setStatus(HttpServletResponse.SC_OK);
        logger.info(new StringBuilder().append("Items returned [").append(returnValue.toString()).append("].").toString());
        return returnValue;

    }

    /**
     * Returns a single item by name and id.
     *
     * @param response HttpServletResponse
     * @param id       id
     * @param name     name
     * @return item
     */
    @RequestMapping(method = RequestMethod.GET, value = "/get/{name}/{id}")
    public Item getItem(HttpServletResponse response,
                        @PathVariable("id") Long id,
                        @PathVariable("name") String name) {

        Item sourceItem = Item.builder().id(id).name(name).build();
        Item returnValue = null;
        if (itemService.existsById(sourceItem)) {
            returnValue = itemService.getItemById(sourceItem);
            response.setStatus(HttpServletResponse.SC_OK);
            logger.info(new StringBuilder().append("Item returned [").append(returnValue.toString()).append("].").toString());
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            logger.error(new StringBuilder().append("Item NOT found [").append(sourceItem.toString()).append("].").toString());
        }
        return returnValue;
    }

    /**
     * Creates an item by name.
     *
     * @param response
     * @param name
     */
    @RequestMapping(method = RequestMethod.POST, value = "/create/{name}")
    public void createItem(HttpServletResponse response,
                           @PathVariable("name") String name) {

        Item sourceItem = Item.builder().name(name).build();
        itemService.createItem(sourceItem);
        response.setStatus(HttpServletResponse.SC_OK);
        logger.info(new StringBuilder().append("Item created [").append(sourceItem.toString()).append("].").toString());

    }

    /**
     * Updates an item by name and id.
     *
     * @param response HttpServletResponse
     * @param name     name
     * @param id       id
     */
    @RequestMapping(method = RequestMethod.POST, value = "/create/{name}/{id}")
    public void updateItem(HttpServletResponse response,
                           @PathVariable("id") Long id,
                           @PathVariable("name") String name) {

        Item sourceItem = Item.builder().id(id).name(name).build();
        itemService.createItem(sourceItem);
        response.setStatus(HttpServletResponse.SC_OK);
        logger.info(new StringBuilder().append("Item created [").append(sourceItem.toString()).append("].").toString());

    }

    /**
     * Deletes item by name and id.
     *
     * @param response HttpServletResponse
     * @param id       id
     * @param name     name
     */
    @RequestMapping(method = RequestMethod.POST, value = "/delete/{name}/{id}")
    public void deleteItem(HttpServletResponse response,
                           @PathVariable("id") Long id,
                           @PathVariable("name") String name) {

        Item sourceItem = Item.builder().id(id).name(name).build();
        if (itemService.existsById(sourceItem)) {
            itemService.deleteItem(sourceItem);
            response.setStatus(HttpServletResponse.SC_OK);
            logger.info(new StringBuilder().append("Item deleted [").append(sourceItem.toString()).append("].").toString());
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            logger.error(new StringBuilder().append("Item NOT deleted [").append(sourceItem.toString()).append("].").toString());
        }

    }

}
