package exercise.android.reemh.todo_items;

import junit.framework.TestCase;

import org.junit.Test;

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

  public void test_when_markDoneItem_then_itShouldMoveToTheEndOfTheList() {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("shitting");
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(1));

    // verify
    assertEquals(2, holderUnderTest.getCurrentItems().size());
    assertTrue(holderUnderTest.getCurrentItems().get(1).getStatus());
  }

  public void test_when_markInProgressItem_then_itShouldMoveToTheTopOfTheList() {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("shitting");
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
    holderUnderTest.markItemInProgress(holderUnderTest.getCurrentItems().get(1));

    // verify
    assertEquals(2, holderUnderTest.getCurrentItems().size());
    assertEquals("shitting", holderUnderTest.getCurrentItems().get(0).getDescription());
    assertTrue(holderUnderTest.getCurrentItems().get(0).getStatus());
  }

  public void test_when_markInProgressItem_then_itShouldMoveToTheTopOfTheList2() {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("shitting");
    holderUnderTest.addNewInProgressItem("studying");
//    holderUnderTest.markItemDone(new TodoItem("shitting", false));
//    holderUnderTest.markItemInProgress(new TodoItem("shitting", true));

    // verify
    assertEquals(3, holderUnderTest.getCurrentItems().size());
    assertEquals("shitting", holderUnderTest.getCurrentItems().get(0).getDescription());
    assertEquals("studying", holderUnderTest.getCurrentItems().get(1).getDescription());
    assertTrue(holderUnderTest.getCurrentItems().get(0).getStatus());
  }
    // TODO: add at least 10 more tests to verify correct behavior of your implementation of `TodoItemsHolderImpl` class

}