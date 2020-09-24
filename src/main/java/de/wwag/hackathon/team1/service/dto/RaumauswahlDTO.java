package de.wwag.hackathon.team1.service.dto;

import java.util.List;

public class RaumauswahlDTO {

	private List<String> haeuser;
	private List<String> riegel;
	private List<String> stockwerk;	
	
	public RaumauswahlDTO(List<String> haeuser, List<String> riegel, List<String> stockwerk) {
		this.haeuser = haeuser;
		this.riegel = riegel;
		this.stockwerk = stockwerk;
	}

	public List<String> getHaeuser() {
		return haeuser;
	}

	public void setHaeuser(List<String> haeuser) {
		this.haeuser = haeuser;
	}

	public List<String> getRiegel() {
		return riegel;
	}

	public void setRiegel(List<String> riegel) {
		this.riegel = riegel;
	}

	public List<String> getStockwerk() {
		return stockwerk;
	}

	public void setStockwerk(List<String> stockwerk) {
		this.stockwerk = stockwerk;
	}
}
