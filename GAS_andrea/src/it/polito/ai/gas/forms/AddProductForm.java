package it.polito.ai.gas.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;


public class AddProductForm {
	
	private String command;
	@NotEmpty @Size(min = 1, max = 30)
	private String name;
	@NotNull @Range(min = 0)
	private float cost;
	@NotEmpty @Size(min = 1, max = 20)
	private String units;
	@NotNull @Range(min = 0)
	private float quantity;
	@NotEmpty @Size(min = 1, max = 50)
	private String description;
	@NotEmpty @Size(min = 1, max = 10)
	private String dimensions;
	@NotNull @Range(min = 0)	
	private float transport_cost;
	@NotNull @Range(min = 1)
	private float stock_quantity;
	@NotNull @Range(min = 1)
	private float min_to_buy;
	@NotNull @Range(min = 1)
	private float max_to_buy;
	@NotNull
	private boolean available;
	
	
	// --- Getters & Setters ---
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getTransportCost() {
		return transport_cost;
	}

	public void setTransportCost(float transport_cost) {
		this.transport_cost = transport_cost;
	}

	public float getStockQuantity() {
		return stock_quantity;
	}

	public void setStockQuantity(float stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	public float getMinToBuy() {
		return min_to_buy;
	}

	public void setMinToBuy(float min_to_buy) {
		this.min_to_buy = min_to_buy;
	}

	public float getMaxToBuy() {
		return max_to_buy;
	}

	public void setMaxToBuy(float max_to_buy) {
		this.max_to_buy = max_to_buy;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

}
