package com.withdog.service.sns;

import org.json.simple.JSONObject;

import com.withdog.service.domain.Point;

public interface SnsService {
	
	public JSONObject kakaoPay(Point point,String uri) throws Exception;
		
}