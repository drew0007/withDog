package com.withdog.service.common;

import java.util.List;
import java.util.Map;

import com.withdog.common.Search;
import com.withdog.service.domain.Point;

//==> 회원관리에서 서비스할 내용 추상화/캡슐화한 Service  Interface Definition  
public interface CommonService {
	
	public Map<String,Object> getMyPointList(Point point) throws Exception;
	
	public void addPointinfo(Point point)throws Exception;
	
	public int getCurrentPoint(Point point)throws Exception;
	
	public List<Point> getMyPointList(Search search , String userId) throws Exception;
		
}