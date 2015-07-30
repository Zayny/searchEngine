package searchengine.manager.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import searchengine.helper.AnalysisHelper;
import searchengine.helper.SpiderHelper;
import searchengine.manager.SpiderManager;
import searchengine.model.ShortModel;

@Service
public class SpiderManagerImpl implements SpiderManager{


	@Override
	public boolean climb(String url,Integer deep) {
		return false;
	}

	@Override
	public boolean lagouClimb(String url) {
		try {
			List<ShortModel> msg = SpiderHelper.getMsg(url);
			for (ShortModel shortModel : msg) {
				AnalysisHelper.createIndexFromModel(shortModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
