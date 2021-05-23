package exercise.android.reemh.todo_items;

import junit.framework.TestCase;

import org.junit.Test;

import static android.os.SystemClock.sleep;

public class TodoItemsHolderImplTest extends TestCase {
    @Test
    public void test_when_addingTodoItem_then_callingListShouldHaveThisItem() {
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");

        // verify
        assertEquals(1, holderUnderTest.getCurrentItems().size());
    }

    // TODO: add at least 10 more tests to verify correct behavior of your implementation of `TodoItemsHolderImpl` class
    @Test
    public void test_when_addingTodoItem_then_deletingIt_listShouldBeEmpty() {
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");
        holderUnderTest.deleteItem(holderUnderTest.getCurrentItems().get(0));

        // verify
        assertEquals(0, holderUnderTest.getCurrentItems().size());
    }
    @Test
    public void test_when_maekItemDone_then_isDone_shouldBeTrue() {
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");
        holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));

        // verify
        assertTrue(holderUnderTest.getCurrentItems().get(0).getStatus());
    }

    @Test
    public void test_when_maekItemDone_then_markInProgress_statusShouldBeTrue() {
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");
        holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
        holderUnderTest.markItemInProgress(holderUnderTest.getCurrentItems().get(0));

        // verify
        assertFalse(holderUnderTest.getCurrentItems().get(0).getStatus());
    }
    @Test
    public void test_when_markDoneTopItem_then_itShouldMoveToTheEndOfTheList() {
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");
        holderUnderTest.addNewInProgressItem("sleeping");
        holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(1));

        // verify
        assertEquals(2, holderUnderTest.getCurrentItems().size());
        assertTrue(holderUnderTest.getCurrentItems().get(1).getStatus());
    }

    @Test
    public void test_when_markInProgressDoneItem_then_itShouldMoveToItsPlaceOrderedByCreationTime() {
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");
        //because of system.currentNanoseconds it might get problem in creating them in different creation time
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        holderUnderTest.addNewInProgressItem("sleeping");
        holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
        holderUnderTest.markItemInProgress(holderUnderTest.getCurrentItems().get(1));

        // verify
        assertEquals(2, holderUnderTest.getCurrentItems().size());
        assertEquals("sleeping", holderUnderTest.getCurrentItems().get(0).getDescription());
        assertFalse(holderUnderTest.getCurrentItems().get(0).getStatus());
    }

    @Test
    public void test_when_markInProgressDoneItem_then_itShouldMoveToItsPlaceOrderedByCreationTime2() {
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        try {
            Thread.sleep(200);

        holderUnderTest.addNewInProgressItem("do shopping");
            Thread.sleep(200);
        holderUnderTest.addNewInProgressItem("sleeping");
            Thread.sleep(200);
        holderUnderTest.addNewInProgressItem("studying");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(1));
        assertTrue(holderUnderTest.getCurrentItems().get(2).getStatus());
        holderUnderTest.markItemInProgress(holderUnderTest.getCurrentItems().get(2));

        // verify
        assertEquals(3, holderUnderTest.getCurrentItems().size());
        assertEquals("sleeping", holderUnderTest.getCurrentItems().get(1).getDescription());
        assertEquals("studying", holderUnderTest.getCurrentItems().get(0).getDescription());
        assertFalse(holderUnderTest.getCurrentItems().get(1).getStatus());
    }

    @Test
    public void test_itemsInsertedByCreationTime() {
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("a");
        holderUnderTest.addNewInProgressItem("b");
        holderUnderTest.addNewInProgressItem("c");

        // verify
        assertEquals(3, holderUnderTest.getCurrentItems().size());
        assertEquals("a", holderUnderTest.getCurrentItems().get(2).getDescription());
        assertEquals("b",  holderUnderTest.getCurrentItems().get(1).getDescription());
        assertEquals("c",  holderUnderTest.getCurrentItems().get(0).getDescription());
    }

    @Test
    public void test_multipleDone() {
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("a");
        holderUnderTest.addNewInProgressItem("b");
        holderUnderTest.addNewInProgressItem("c");
        holderUnderTest.addNewInProgressItem("d");
        holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
        holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(2));

        // verify
        assertTrue(holderUnderTest.getCurrentItems().get(2).getStatus());
        assertTrue(holderUnderTest.getCurrentItems().get(3).getStatus());
    }

    @Test
    public void test_orderAfterDelete() {
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("a");
        holderUnderTest.addNewInProgressItem("b");
        holderUnderTest.addNewInProgressItem("c");
        holderUnderTest.deleteItem(holderUnderTest.getCurrentItems().get(1));

        // verify
        assertEquals(2,holderUnderTest.getCurrentItems().size());
        assertEquals("c",holderUnderTest.getCurrentItems().get(0).getDescription());
        assertEquals("a",holderUnderTest.getCurrentItems().get(1).getDescription());
    }

    @Test
    public void test_AddAfterDelete() {
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("a");
        holderUnderTest.addNewInProgressItem("b");
        holderUnderTest.addNewInProgressItem("c");
        holderUnderTest.deleteItem(holderUnderTest.getCurrentItems().get(1));
        holderUnderTest.addNewInProgressItem("b");

        // verify
        assertEquals(3,holderUnderTest.getCurrentItems().size());
        assertEquals("b",holderUnderTest.getCurrentItems().get(0).getDescription());
        assertEquals("c",holderUnderTest.getCurrentItems().get(1).getDescription());
        assertEquals("a",holderUnderTest.getCurrentItems().get(2).getDescription());
    }



}