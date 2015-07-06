package com.answerofgod.weather;

public class Weather {
	private String tmp;
	private String hu;
	private String wind;
	private String category;
	
	public void setTmp(String tmp)
	{
		this.tmp=tmp;
	}
	public void setHu(String hu)
	{
		this.hu=hu;
	}
	public void setWind(String wind)
	{
		this.wind=wind;
	}
	public void setCategory(String ca)
	{
		this.category=ca;
	}
	public String getTmp()
	{
		return this.tmp;
	}
	public String getCategory()
	{
		return this.category;
	}
	public String getHu()
	{
		return this.hu;
	}
	public String getWind()
	{
		return this.wind;
	}
	

}
