package com.withdog.service.afterash;

import java.util.List;

import com.withdog.common.Search;
import com.withdog.service.domain.AfterAsh;

public interface AfterAshDao {
	public void addAfterAsh(AfterAsh afterAsh) throws Exception;

	public AfterAsh getAfterAsh(int afterAshNo) throws Exception;

	public void updateAfterAsh(AfterAsh afterAsh) throws Exception;

	public List<AfterAsh> getAfterAshList(Search search) throws Exception;

	public void deleteAfterAsh(AfterAsh afterAsh) throws Exception;

}
