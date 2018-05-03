package com.winfo.szrsp.app.sdk.entity.table;

import java.io.Serializable;
import java.util.List;

public class CtPscFromObjectDetail implements Serializable{

	
	private CtPscFrom ctpscfrom;
	
	private List<CtPscFromA> lista;
	
	private List<CtPscFromB> listb;

	public CtPscFrom getCtpscfrom() {
		return ctpscfrom;
	}

	public void setCtpscfrom(CtPscFrom ctpscfrom) {
		this.ctpscfrom = ctpscfrom;
	}

	public List<CtPscFromA> getLista() {
		return lista;
	}

	public void setLista(List<CtPscFromA> lista) {
		this.lista = lista;
	}

	public List<CtPscFromB> getListb() {
		return listb;
	}

	public void setListb(List<CtPscFromB> listb) {
		this.listb = listb;
	}
	
}
