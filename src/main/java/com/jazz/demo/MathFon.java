package com.jazz.demo;

import java.util.LinkedList;

public class MathFon {
	
	private String mata1 = null;
	private String mata2 = null;
	private String mata3 = null;
	private String dms = null; 
	private String num = null;
	private String ela = null; // elementi teorije algoritama
	private String mim = null; // matematika i muzika
	private String mlip= null; // matematicka logika i primene
	private String msp= null; // matematicki softverski paketi
	private String okg= null; // osnovi kompjuterske geometrije
	private String ump= null; //uvod u matematicko programiranje
	
	
	
	
	public String getMata1() {
		return mata1;
	}
	public void setMata1(String mata1) {
		this.mata1 = mata1;
	}
	public String getMata2() {
		return mata2;
	}
	public void setMata2(String mata2) {
		this.mata2 = mata2;
	}
	public String getMata3() {
		return mata3;
	}
	public void setMata3(String mata3) {
		this.mata3 = mata3;
	}
	public String getDms() {
		return dms;
	}
	public void setDms(String dms) {
		this.dms = dms;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getEla() {
		return ela;
	}
	public void setEla(String ela) {
		this.ela = ela;
	}
	public String getMim() {
		return mim;
	}
	public void setMim(String mim) {
		this.mim = mim;
	}
	public String getMlip() {
		return mlip;
	}
	public void setMlip(String mlip) {
		this.mlip = mlip;
	}
	public String getMsp() {
		return msp;
	}
	public void setMsp(String msp) {
		this.msp = msp;
	}
	public String getOkg() {
		return okg;
	}
	public void setOkg(String okg) {
		this.okg = okg;
	}
	public String getUmp() {
		return ump;
	}
	public void setUmp(String ump) {
		this.ump = ump;
	}
	
	
	//vraca listu samo sa elementima u kojima se nalazi deo linka
	public LinkedList<String> returnList (){
		
		LinkedList<String> list = new LinkedList<>();
		
		
		if(mata1!=null) {
			list.add(mata1);	}
		if(mata2!=null) {
			list.add(mata2);	}
		if(mata3!=null) {
			list.add(mata3);	}
		if(dms!=null) {
			list.add(dms);		}
		if(num!=null) {
			list.add(num);		}
		if(ela!=null) {
			list.add(ela);		}
		if(mim!=null) {
			list.add(mim);		}
		if(mlip!=null) {
			list.add(mlip);		}
		if(msp!=null) {
			list.add(msp);		}
		if(okg!=null) {
			list.add(okg);		}
		if(ump!=null) {
			list.add(ump);		}
		
		return list;
	}
	

}
