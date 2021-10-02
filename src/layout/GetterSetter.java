package layout;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GetterSetter {
	private StringProperty id;
	private StringProperty address;
	private StringProperty name;
	
	public GetterSetter() {
		// X
	}
	
	public GetterSetter(String id, String name, String address) {
		this.name = new SimpleStringProperty(name);
		this.id = new SimpleStringProperty(id);
		this.address = new SimpleStringProperty(address);
	}

	public StringProperty getId() {
		return id;
	}

	public void setId(StringProperty id) {
		this.id = id;
	}

	public StringProperty getAddress() {
		return address;
	}

	public void setAddress(StringProperty address) {
		this.address = address;
	}

	public StringProperty getName() {
		return name;
	}

	public void setName(StringProperty name) {
		this.name = name;
	}

}
