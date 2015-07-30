package searchengine.manager.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import searchengine.Vo.PageVo;
import searchengine.manager.BaseManager;
import searchengine.mapper.BaseMapper;
import searchengine.model.BaseModel;

public abstract class BaseManagerImpl<T extends BaseModel> implements BaseManager<T> {

	private BaseMapper<T> baseMapper;

	@PostConstruct
	protected abstract void initBaseMapper();

	public void setBaseMapper(BaseMapper<T> baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public T getById(Long id) throws Exception {
		return baseMapper.getById(id);
	}

	@Override
	public Long getCount(T entity) throws Exception {
		return baseMapper.getCount(entity);
	}

	@Override
	public List<T> getList(PageVo<T> vo) throws Exception {
		return baseMapper.getList(vo);
	}

	@Override
	public void modify(T entity) throws Exception {
		entity.setCreateOn(null);
		baseMapper.modify(entity);
	}

	@Override
	public void delete(Long id) throws Exception {
		baseMapper.delete(id);
	}

	@Override
	public void add(T entity) throws Exception {
		entity.setCreateOn(new Date());
		baseMapper.add(entity);
	}

	@Override
	public T getById(String id) throws Exception {
		return baseMapper.getById(id);
	}

	@Override
	public void delete(String id) throws Exception {
		baseMapper.delete(id);
	}
}
