package searchengine.manager;

import java.util.List;

import searchengine.model.ShortModel;

public interface SearchManager {
	
	public List<ShortModel> searchOnKey(String key) throws Exception;

}
