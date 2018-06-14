/**
 * 
 */
package com.lastminute.exercise.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import com.google.inject.Inject;
import com.lastminute.exercise.bean.Cart;
import com.lastminute.exercise.bean.CartItem;
import com.lastminute.exercise.bean.Product;
import com.lastminute.exercise.bean.ProductType;
import com.lastminute.exercise.service.CartService;
import com.lastminute.exercise.utility.Constants;
import com.lastminute.exercise.utility.ResourceBundleHelper;
import com.lastminute.exercise.utility.Utility;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

/**
 * @author Gianluca Colaianni -- Fincons Group S.p.A.
 *
 */
public class MainViewController implements Initializable {

    private static final Pattern PATTERN_AMOUNT = Pattern.compile("^\\d*(.\\d{0,2})?$");

    private static final Pattern PATTERN_INTEGER = Pattern.compile("\\d*");

    private static final String FORMAT_AMOUNT = "#0.##";

    private Cart cart;

    @Inject
    private CartService cartService;

    @FXML
    private TableColumn<CartItem, BigDecimal> itemAmountTableColumn;

    @FXML
    private TextField productNameTextField;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private ComboBox<ProductType> productTypeComboBox;

    @FXML
    private Label cartTotalLabel;

    @FXML
    private Button emptyCartButton;

    @FXML
    private Button clearProductButton;

    @FXML
    private Button removeItemButton;

    @FXML
    private TableColumn<CartItem, String> itemProductIdTableColumn;

    @FXML
    private TextField productAmountTextField;

    @FXML
    private TableColumn<CartItem, BigInteger> itemQuantityTableColumn;

    @FXML
    private TextField productIdTextField;

    @FXML
    private TextField productQuantityTextField;

    @FXML
    private TableView<CartItem> cartTableView;

    @FXML
    private Label cartSalesTaxesLabel;

    @FXML
    private TableColumn<CartItem, String> itemProductNameTableColumn;

    @FXML
    private CheckBox productImportedCheckBox;

    @FXML
    private Button addProductButton;

    /**
     * Create a product object from the view input.
     * @return a {@link Product} instance.
     * @author Gianluca Colaianni -- Fincons Group S.p.A.
     */
    private Product getProduct() {
	BigDecimal amount = BigDecimal.ZERO;
	if (this.productAmountTextField.getText() != null && !this.productAmountTextField.getText().isEmpty()) {
	    amount = new BigDecimal(this.productAmountTextField.getText());
	}
	return new Product(this.productIdTextField.getText(), this.productNameTextField.getText(),
		amount,
		this.productTypeComboBox.getSelectionModel().getSelectedItem(),
		this.productImportedCheckBox.isSelected());
    }
    
    /**
     * Update displayed informations.
     * @author Gianluca Colaianni -- Fincons Group S.p.A.
     */
    private void updateView() {
	this.cartTableView.getItems().setAll(this.cart.getItems().values());
	    this.cartTotalLabel.setText(Utility.format(this.cart.getTotal(), FORMAT_AMOUNT));
	    this.cartSalesTaxesLabel.setText(Utility.format(this.cart.getTotalTaxes(), FORMAT_AMOUNT));
    }
    
    private void clearProductDetails() {
	this.productAmountTextField.clear();
	this.productIdTextField.clear();
	this.productNameTextField.clear();
	this.productQuantityTextField.clear();
	this.productTypeComboBox.getSelectionModel().select(0);
	this.productImportedCheckBox.setSelected(false);
    }

    private void initializeButtons() {

	/*
	 * Set addProductButton behavior.
	 */
	this.addProductButton.disableProperty()
		.bind(Bindings.isEmpty(this.productIdTextField.textProperty())
			.or(Bindings.isEmpty(this.productAmountTextField.textProperty()))
			.or(Bindings.isEmpty(this.productQuantityTextField.textProperty())));

	/*
	 * Action to be done on click.
	 */
	this.addProductButton.setOnMouseClicked(event -> {
	    /*
	     * Update cart.
	     */
	    this.cart = this.cartService.addItem(this.cart, getProduct(),
		    new BigInteger(this.productQuantityTextField.getText()));

	    /*
	     * Update displayed information.
	     */
	    updateView();
	    
	    clearProductDetails();

	});

	/*
	 * Set removeItemButton behavior.
	 */
	this.removeItemButton.disableProperty().bind(Bindings.isEmpty(this.productIdTextField.textProperty())
		.or(Bindings.isEmpty(this.productQuantityTextField.textProperty())));

	this.removeItemButton.setOnMouseClicked(event -> {
	    /*
	     * Update cart.
	     */
	    this.cart = this.cartService.removeItem(this.cart, getProduct(),
		    new BigInteger(this.productQuantityTextField.getText()));
	    /*
	     * Update displayed informations.
	     */
	    updateView();
	    
	    clearProductDetails();
	});
	
	/*
	 * Clear product informations.
	 */
	this.clearProductButton.setOnMouseClicked(event -> clearProductDetails());
	
	/*
	 * Empty button behavior.
	 */
	this.emptyCartButton.setOnMouseClicked(event -> {
	    clearProductDetails();
	    this.cart = new Cart();
	    this.cartTableView.getItems().clear();
	    this.cartTotalLabel.setText(Constants.EMPTY);
	    this.cartSalesTaxesLabel.setText(Constants.EMPTY);
	});
	
	/*
	 * About menu action.
	 */
	this.aboutMenuItem.setOnAction(event -> {
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setContentText(ResourceBundleHelper.getString("message.info"));
	    alert.setHeaderText(Constants.EMPTY);
	    alert.showAndWait();
	});
	
    }

    private void initializeTable() {
	this.itemQuantityTableColumn
		.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getQuantity()));
	this.itemProductIdTableColumn
		.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getProduct().getId()));
	this.itemAmountTableColumn
		.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getTotal()));
	this.itemProductNameTableColumn.setCellValueFactory(
		cellData -> new ReadOnlyStringWrapper(cellData.getValue().getProduct().getDescription()));

    }

    /**
     * Configure controller text fields behavior.
     * 
     * @author Gianluca Colaianni
     */
    private void initializeTextFields() {
	/*
	 * Accept only valid amount values.
	 */
	final UnaryOperator<Change> amountFilter = change -> {
	    String newText = change.getControlNewText();
	    if (PATTERN_AMOUNT.matcher(newText).matches()) {
		return change;
	    }
	    return null;
	};
	this.productAmountTextField.setTextFormatter(new TextFormatter<BigDecimal>(amountFilter));

	/*
	 * Accept only valid integers.
	 */
	final UnaryOperator<Change> integerFilter = change -> {
	    String newText = change.getControlNewText();
	    if (PATTERN_INTEGER.matcher(newText).matches()) {
		return change;
	    }
	    return null;
	};
	this.productQuantityTextField.setTextFormatter(new TextFormatter<BigInteger>(integerFilter));

    }

    /*
     * (non-Javadoc)
     * 
     * @see javafx.fxml.Initializable#initialize(java.net.URL,
     * java.util.ResourceBundle)
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
	this.cart = new Cart();

	initializeTextFields();

	initializeButtons();

	initializeTable();

	/*
	 * Initialize combo box.
	 */
	this.productTypeComboBox.getItems().addAll(ProductType.values());
	this.productTypeComboBox.getSelectionModel().select(0);
    }

}
