package models;

import org.junit.After;
import org.junit.Test;

import static models.Item.createNewItem;
import static org.junit.Assert.*;

/**
 * Created by Guest on 8/10/17.
 */


public class ItemTest {

    @After
    public void tearDown() throws Exception {
        Item.clearItemList();
    }

    @Test
    public void Item_InstantiateNewItem_True() throws Exception {
        Item newItem = createNewItem();
        assertTrue(newItem instanceof Item);
    }

    @Test
    public void Item_CanRetrieveItemProperty_2() throws Exception {
        Item newItem = createNewItem();
        assertEquals(2.50, newItem.getItemPrice(),30);
    }

    @Test
    public void Item_CanIncludeMultipleItemsInList_2() throws Exception {
        Item newItem = createNewItem();
        Item newItem2 = new Item("tent",54.50,3.32);
        assertEquals(2,Item.getAllItems().size());
    }


}