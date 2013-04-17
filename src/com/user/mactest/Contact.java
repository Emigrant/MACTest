package com.user.mactest;

public class Contact {
	
	//private variables
	int _id;
	String _name;
	String _surname;
	
	// Empty constructor
	public Contact(){
		
	}
	// constructor
	public Contact(int id, String name, String surname){
		this._id = id;
		this._name = name;
		this._surname = surname;
	}
	
	// constructor
	public Contact(String name, String _surname){
		this._name = name;
		this._surname = _surname;
	}
	
	//constructor
	public Contact(int id, String name){
		this._id = id;
		this._name = name;
	}
	
	// getting ID
	public int getID(){
		return this._id;
	}
	
	// setting id
	public void setID(int id){
		this._id = id;
	}
	
	// getting name
	public String getName(){
		return this._name;
	}
	
	// setting name
	public void setName(String name){
		this._name = name;
	}
	
	// getting surname
	public String getSurname(){
		return this._surname;
	}
	
	// setting surname
	public void setSurname(String surname){
		this._surname = surname;
	}
	
	@Override
	public String toString(){
		return _name;
	}
}

