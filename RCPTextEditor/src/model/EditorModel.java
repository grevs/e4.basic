package model;

public class EditorModel {

	private String oldValue;
	private String newValue;
	
	public EditorModel(String oldValue, String newValue) {
		this.setOldValue(oldValue);
		this.setNewValue(newValue);
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public boolean isChanged() {
		if(this.oldValue.equals(this.newValue)) {
			return false;
		}
		else {
			return true;
		}
	}
	

}
