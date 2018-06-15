/**
 * 
 */
package com.lastminute.exercise.test.unit.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import com.lastminute.exercise.StartApplication;

import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

/**
 * @author Gianluca Colaianni -- Fincons Group S.p.A.
 *
 */
public class MainViewControllerUnitTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
	StartApplication app = new StartApplication();
	app.start(stage);
    }
    
    @After
    public void tearDown() throws Exception {
	FxToolkit.hideStage();
	release(new KeyCode[] {});
	release(new MouseButton[] {});
    }
    
    @BeforeClass
    public static void setupSpec() throws Exception {
        if (Boolean.getBoolean("headless")) {
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");
            System.setProperty("java.awt.headless", "true");
        }
    }
    
//    @Override
//    public PointQuery point(Node node) {
//        Point2D topLeftPoint = node.localToScreen(0, 0);
//        Point2D pos = new Point2D(topLeftPoint.getX(), topLeftPoint.getY());
//
//        return super.point(node).atOffset(pos);
//    }
    
    @Test
    public void testInitialize() {
	//button status check
	assertThat(lookup("#addProductButton").queryButton().isDisable()).isTrue();
	assertThat(lookup("#removeItemButton").queryButton().isDisable()).isTrue();
	assertThat(lookup("#clearProductButton").queryButton().isDisable()).isFalse();
	assertThat(lookup("#emptyCartButton").queryButton().isDisable()).isFalse();
	
	//combo box status check
	assertThat(lookup("#productTypeComboBox").queryComboBox().getSelectionModel().getSelectedItem()).isNotNull();
	
	assertThat(lookup("#productImportedCheckBox").queryAs(CheckBox.class).isSelected()).isFalse();
    }
    
    @Test
    public void testInputFieldValidation() {
	lookup("#productQuantityTextField").queryTextInputControl().setText("a");
	assertThat(lookup("#productQuantityTextField").queryTextInputControl().getText()).isEmpty();
	
	lookup("#productQuantityTextField").queryTextInputControl().setText("10");
	assertThat(lookup("#productQuantityTextField").queryTextInputControl().getText()).isEqualTo("10");
	
	lookup("#productQuantityTextField").queryTextInputControl().setText("10.");
	assertThat(lookup("#productQuantityTextField").queryTextInputControl().getText()).isEqualTo("10");
	
	lookup("#productAmountTextField").queryTextInputControl().setText("a");
	assertThat(lookup("#productAmountTextField").queryTextInputControl().getText()).isEmpty();
	
	lookup("#productAmountTextField").queryTextInputControl().setText("10");
	assertThat(lookup("#productAmountTextField").queryTextInputControl().getText()).isEqualTo("10");
	
	lookup("#productAmountTextField").queryTextInputControl().setText("10.25");
	assertThat(lookup("#productAmountTextField").queryTextInputControl().getText()).isEqualTo("10.25");
    }
    
    @Test
    public void testAddProductButton() {
	lookup("#productQuantityTextField").queryTextInputControl().setText("10");
	assertThat(lookup("#addProductButton").queryButton().isDisable()).isTrue();
	
	lookup("#productAmountTextField").queryTextInputControl().setText("10.25");
	assertThat(lookup("#addProductButton").queryButton().isDisable()).isTrue();
	
	lookup("#productIdTextField").queryTextInputControl().setText("test");
	assertThat(lookup("#addProductButton").queryButton().isDisable()).isFalse();
	
	assertThat(clickOn("#addProductButton").lookup("#cartTableView").queryTableView().getItems().size()).isEqualTo(1);
	assertThat(lookup("#productQuantityTextField").queryTextInputControl().getText()).isEmpty();
	assertThat(lookup("#cartSalesTaxesLabel").queryLabeled().getText()).isNotEmpty();
	assertThat(lookup("#cartTotalLabel").queryLabeled().getText()).isNotEmpty();
    }
    
    @Test
    public void testRemoveItemButton() {
	lookup("#productQuantityTextField").queryTextInputControl().setText("10");
	assertThat(lookup("#removeItemButton").queryButton().isDisable()).isTrue();
	
	lookup("#productIdTextField").queryTextInputControl().setText("test");
	assertThat(lookup("#removeItemButton").queryButton().isDisable()).isFalse();
    }
    
    @Test
    public void testClearProductButton() {
	lookup("#productQuantityTextField").queryTextInputControl().setText("10");
	lookup("#productAmountTextField").queryTextInputControl().setText("10.25");
	lookup("#productIdTextField").queryTextInputControl().setText("test");
	lookup("#productNameTextField").queryTextInputControl().setText("test");
	lookup("#productImportedCheckBox").queryAs(CheckBox.class).setSelected(true);
	
	clickOn("#clearProductButton");
	
	assertThat(lookup("#productQuantityTextField").queryTextInputControl().getText()).isEmpty();
	assertThat(lookup("#productAmountTextField").queryTextInputControl().getText()).isEmpty();
	assertThat(lookup("#productIdTextField").queryTextInputControl().getText()).isEmpty();
	assertThat(lookup("#productNameTextField").queryTextInputControl().getText()).isEmpty();
	assertThat(lookup("#productImportedCheckBox").queryAs(CheckBox.class).isSelected()).isFalse();
    }

}
