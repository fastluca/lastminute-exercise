/**
 * 
 */
package com.lastminute.exercise.test.unit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
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

}
