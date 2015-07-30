package searchengine.manager.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import searchengine.helper.AnalysisHelper;
import searchengine.manager.AnalysisManager;
import searchengine.model.BaseModel;
@Service
public class AnalysisManagerImpl implements AnalysisManager{

	@Override
	public boolean AnalysisIndexs(List<BaseModel> bms) {
		for (BaseModel baseModel : bms) {
			AnalysisHelper.createIndexFromModel(baseModel);
		}
		return false;
	}
}
