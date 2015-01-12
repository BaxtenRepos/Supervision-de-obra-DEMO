package com.csgit.cao.model;

public class Mod_Item_Drawer {

	private String itemName;
	private int idResource;
	
	public Mod_Item_Drawer(String itemName, int idResorce){
		this.itemName = itemName;
		this.idResource = idResorce;
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getIdResource() {
		return idResource;
	}
	public void setIdResource(int idResource) {
		this.idResource = idResource;
	}
}
