package com.winfo.szrsp.app.widget.wheelview.service;


import com.winfo.szrsp.app.widget.wheelview.model.BureauModel;
import com.winfo.szrsp.app.widget.wheelview.model.OfficeModel;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


public class XmlParserHandler extends DefaultHandler {

	/**
	 * 存储所有的解析对象
	 */
	private List<BureauModel> bureauList = new ArrayList<BureauModel>();
	 	  
	public XmlParserHandler() {
		
	}

	public List<BureauModel> getDataList() {
		return bureauList;
	}

	@Override
	public void startDocument() throws SAXException {
		// 当读到第一个开始标签的时候，会触发这个方法
	}

	BureauModel bureauModel = new BureauModel();
	OfficeModel officeModel = new OfficeModel();
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// 当遇到开始标记的时候，调用这个方法
		if (qName.equals("bureau")) {
			bureauModel = new BureauModel();
			bureauModel.setName(attributes.getValue(0));
			bureauModel.setBureauid(attributes.getValue(1));
			bureauModel.setOfficeList(new ArrayList<OfficeModel>());
		} else if (qName.equals("office")) {
			officeModel = new OfficeModel();
			officeModel.setName(attributes.getValue(0));
			officeModel.setOfficeid(attributes.getValue(1));
		} 
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// 遇到结束标记的时候，会调用这个方法
		if (qName.equals("office")) {
			bureauModel.getOfficeList().add(officeModel);
        } else if (qName.equals("bureau")) {
        	bureauList.add(bureauModel);
        }
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
	}

}
