package searchengine.manager.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import searchengine.helper.SerachHelper;
import searchengine.manager.SearchManager;
import searchengine.model.ShortModel;
@Service
public class SearchManagerImpl implements SearchManager{
	@Override
	public List<ShortModel> searchOnKey(String keyWord) throws Exception {
		return SerachHelper.searchMultiField(keyWord);
	}
}
