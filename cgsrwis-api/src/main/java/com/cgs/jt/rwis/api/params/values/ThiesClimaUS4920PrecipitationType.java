/*
 * Copyright (c) 1990, 2019, CGS Labs d.o.o and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER. 
 *
 * Please contact CGS Labs d.o.o., Brnciceva ul. 13, SI-1000 Ljubljana, Slovenia
 * or visit www.cgs-labs.com if you need additional information or have any questions.
 */
package com.cgs.jt.rwis.api.params.values;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.cgs.jt.rwis.api.params.MeasuredParameter;


/**
 * Defines the possible values of the PRECIPITATION_TYPE_THIESCLIMA_US4920_SURFACE measured parameter (i.e. the possible output values
 * for precipitation type of the Thies Clima US 4920 sensor as configured in the CGS road weather stations) and their mapping into 
 * Double number. Used in the checkValue() and getDoubleFromString() method implementations of the 
 * PRECIPITATION_TYPE_THIESCLIMA_US4920_SURFACE enum instance (see {@link MeasuredParameter} enum).
 * 
 * NOTE: if the sensor is configured in some other way then the possible output values can be different so you will need different
 * enumeration!
 * 
 * @author Jernej Trnkoczy
 */
public enum ThiesClimaUS4920PrecipitationType { 
	NO_PRECIPITATION("0", 0.0),
	PRECIPITATION_PRESENT("40", 40.0),
	LIGHT_DRIZZLE("51", 51.0),
	MODERATE_DRIZZLE("52", 52.0),
	HEAVY_DRIZZLE("53", 53.0),
	//NOTE: In the documentation of the ThiesClima US4920 sensor - chapter 3.5.2 Type of precipitation (Synop Code) 
	//the value 57 does not appear (i.e. according to documentation the sensor cannot output value 57). However in a real 
	//world the value 57 is generated by the sensor (i.e. you can find it the LoggerNet files for example). 
	//The value 57 will be therefore added as a valid sensor output (even if this is in conflict with the sensor documentation).
	//According to Synop 4680 WaWa table the value 57 means "Drizzle and rain, slight"
	LIGHT_DRIZZLE_AND_RAIN("57", 57.0),
	LIGHT_RAIN("61", 61.0),
	MODERATE_RAIN("62", 62.0),
	HEAVY_RAIN("63", 63.0),
	LIGHT_RAIN_ANDOR_DRIZZLE_WITH_SNOW("67", 67.0),
	MODERATE_RAIN_ANDOR_DRIZZLE_WITH_SNOW("68", 68.0),
	SNOWFALL("70", 70.0),
	LIGHT_SNOW("71", 71.0),
	MODERATE_SNOW("72", 72.0),
	HEAVY_SNOW("73", 73.0),
	ICE_CRYSTALS("74", 74.0),	
	HEAVY_HAIL("89", 89.0);
	



	private final String stringValue;
	private final Double doubleValue;		
	/**
	 * Constructor of enumeration
	 * @param stringValue The string value (usually called "code") that represents one of the This Clima US4920 sensor outputs.
	 * @param doubleValue The mapping of the string value (code) to the numeric (Double) representation. 
	 */
	private ThiesClimaUS4920PrecipitationType(String stringValue, Double doubleValue) {
		this.stringValue = stringValue;
		this.doubleValue = doubleValue;
	}

	/**
	 * Method to obtain the precipitation type (sensor output) mapped to double value.
	 * @return The precipitation type double value.
	 */
	public Double getDoubleValue() {
		return doubleValue;
	}


	/**
	 * Method to obtain the precipitation type (sensor output) as a String-coded value.
	 * @return The precipitation type string value.
	 */
	public String getStringValue() {
		return stringValue;
	}


	/**
	 * Stores a map of key-value pairs - where keys are string values (codes) of the sensor output and the values are the enum 
	 * instances/constants associated with this sensor output.
	 */
	private static final Map<String,ThiesClimaUS4920PrecipitationType> ENUM_MAP;

	//Builds an immutable map of String codes to enum instance pairs.
	static {
		Map<String,ThiesClimaUS4920PrecipitationType> map = new ConcurrentHashMap<String, ThiesClimaUS4920PrecipitationType>();
		for (ThiesClimaUS4920PrecipitationType instance : ThiesClimaUS4920PrecipitationType.values()) {
			//we should not allow two instances to have the same code!
			//standard map behavior is to replace the value - but this should not happen!
			if(!map.containsKey(instance.getStringValue())) {
				map.put(instance.getStringValue(),instance);
			}
			else {
				throw new IllegalArgumentException("The precipitation type codes should all be different!");
			}

		}
		ENUM_MAP = Collections.unmodifiableMap(map);
	}
	
	
	/**
	 * To retrieve the enum instance by the given String code...
	 * @param code The code for which we want to retrieve the enum instance.
	 * @return The enum instance associated with the given code or null if no enum instance is associated with the given label.
	 */	
	public static ThiesClimaUS4920PrecipitationType get(String code) {
		return ENUM_MAP.get(code);
	}

}	