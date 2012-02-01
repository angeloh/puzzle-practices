package com.sumware.quiz;

import java.util.*;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Vehicle {

    private String dealerCode;
    private String vinPrefix;
    private String vinSuffix;
    private String stockNumber;
    private String modelYear;
    private String makeCode;
    private String modelLine;
    private String modelCode;
    private String transmissionType;
    private String colorCode;
    private String trimCode;
    private List<Option> options;
    private Integer mileage;
    private Integer price;
    private Date transmissionDate;
    

    /**
     * @return Returns the colorCode.
     */
    public String getColorCode() {
        return colorCode;
    }
    /**
     * @param colorCode The colorCode to set.
     */
    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
    /**
     * @return Returns the dealerCode.
     */
    public String getDealerCode() {
        return dealerCode;
    }
    /**
     * @param dealerCode The dealerCode to set.
     */
    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }
    /**
     * @return Returns the makeCode.
     */
    public String getMakeCode() {
        return makeCode;
    }
    /**
     * @param makeCode The makeCode to set.
     */
    public void setMakeCode(String makeCode) {
        this.makeCode = makeCode;
    }
    /**
     * @return Returns the mileage.
     */
    public Integer getMileage() {
        return mileage;
    }
    /**
     * @param mileage The mileage to set.
     */
    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }
    /**
     * @return Returns the modelCode.
     */
    public String getModelCode() {
        return modelCode;
    }
    /**
     * @param modelCode The modelCode to set.
     */
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }
    /**
     * @return Returns the modelLine.
     */
    public String getModelLine() {
        return modelLine;
    }
    /**
     * @param modelLine The modelLine to set.
     */
    public void setModelLine(String modelLine) {
        this.modelLine = modelLine;
    }
    /**
     * @return Returns the modelYear.
     */
    public String getModelYear() {
        return modelYear;
    }
    /**
     * @param modelYear The modelYear to set.
     */
    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }
    /**
     * @return Returns the options.
     */
    public List<Option> getOptions() {
        return options;
    }
    /**
     * @param options The options to set.
     */
    public void setOptions(List<Option> options) {
        this.options = options;
    }
    /**
     * @return Returns the price.
     */
    public Integer getPrice() {
        return price;
    }
    /**
     * @param price The price to set.
     */
    public void setPrice(Integer price) {
        this.price = price;
    }
    /**
     * @return Returns the stockNumber.
     */
    public String getStockNumber() {
        return stockNumber;
    }
    /**
     * @param stockNumber The stockNumber to set.
     */
    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }
    /**
     * @return Returns the transmissionDate.
     */
    public Date getTransmissionDate() {
        return transmissionDate;
    }
    /**
     * @param transmissionDate The transmissionDate to set.
     */
    public void setTransmissionDate(Date transmissionDate) {
        this.transmissionDate = transmissionDate;
    }
    /**
     * @return Returns the transmissionType.
     */
    public String getTransmissionType() {
        return transmissionType;
    }
    /**
     * @param transmissionType The transmissionType to set.
     */
    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }
    /**
     * @return Returns the trimCode.
     */
    public String getTrimCode() {
        return trimCode;
    }
    /**
     * @param trimCode The trimCode to set.
     */
    public void setTrimCode(String trimCode) {
        this.trimCode = trimCode;
    }
    /**
     * @return Returns the vinPrefix.
     */
    public String getVinPrefix() {
        return vinPrefix;
    }
    /**
     * @param vinPrefix The vinPrefix to set.
     */
    public void setVinPrefix(String vinPrefix) {
        this.vinPrefix = vinPrefix;
    }
    /**
     * @return Returns the vinSuffix.
     */
    public String getVinSuffix() {
        return vinSuffix;
    }
    /**
     * @param vinSuffix The vinSuffix to set.
     */
    public void setVinSuffix(String vinSuffix) {
        this.vinSuffix = vinSuffix;
    }
    
    @Override
    public String toString(){
//    	Dealer  VIN                 Stock#  Year  Make  Model–Code  Trans  Color  Trim  Mileage  Price   Date
//    	319     JNRAS08U85X-102361  I2608   05    IN    FX35-87215  A      KH3    J     0000000  038440  04-05-2005
//   	Options: B95, C03, G02, G46, L60, P93
    	StringBuffer sb = new StringBuffer();
    	String vin = vinPrefix + "-" + vinSuffix;
    	String modelCode = modelLine + "-" + this.modelCode;
    	String tranDate = "";
    	if (this.transmissionDate != null){
    		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
    		tranDate = df.format(this.transmissionDate);
    	}
    	String optsStr = "";
    	if (options.size() > 0) {
    		StringBuffer sbuilder = new StringBuffer();
    		int count = 0;    		 
    		for (Option o : options) {
    			sbuilder.append(new String(o.getOptionCode()));
    		     if (++count != options.size()) {
    		    	 sbuilder.append(", ");
    		     }
    		}    		 
    		optsStr = sbuilder.toString();
    	}
    	sb.append(dealerCode).append("\t");
    	sb.append(vin).append("\t");
    	sb.append(stockNumber).append("\t");
    	sb.append(modelYear).append("\t");
    	sb.append(makeCode).append("\t");
    	sb.append(modelCode).append("\t");
    	sb.append(transmissionType).append("\t");
    	sb.append(colorCode).append("\t");
    	sb.append(trimCode).append("\t");
    	NumberFormat formatter = new DecimalFormat("000000");
    	String mileStr = formatter.format(mileage);
    	sb.append(mileStr).append("\t");
    	String priceStr = formatter.format(price);
    	sb.append(priceStr).append("\t");
    	sb.append(tranDate).append("\n");
    	sb.append("Options:").append(optsStr);
    	return sb.toString();

    }
}