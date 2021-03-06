package vfh.httpInterface.service.buildings;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import vfh.httpInterface.commons.Page;
import vfh.httpInterface.commons.PageRequest;
import vfh.httpInterface.commons.StringUtil;
import vfh.httpInterface.commons.VariableUtils;
import vfh.httpInterface.commons.valid.annotation.MapValid;
import vfh.httpInterface.dao.buildings.BuildingsMapper;
import vfh.httpInterface.dao.buildings.BuildingsPriceMapper;
import vfh.httpInterface.service.ServiceException;

/**
 * TODO 楼盘业务逻辑
 * @author harry
 * <b> 有问题请联系qq:359705093</b>
 * @create 2016年1月11日
 */
@Service
@Transactional
public class BuildingsService {
	@Autowired
	private BuildingsMapper buildingsMapper;
	@Autowired
	private BuildingsPriceMapper buildingsPriceMapper;
	/**
	 * TODO 添加楼盘
	 * @author harry
	 * <b> 有问题请联系qq:359705093</b>
	 * @param entity
	 * @create 2016年1月11日
	 */
	public void insertBuildings(@MapValid("insert-buildings") Map<String, Object> entity){
		entity.put("status", 0);
        if(entity.get("open_date").equals("")) entity.put("open_date", null);
        if(entity.get("deliver_date").equals("")) entity.put("deliver_date", null);
        if(entity.get("cityId").equals("--请选择--")) entity.put("cityId", null);
        if(entity.get("areaId").equals("--请选择--")) entity.put("areaId", null);
		buildingsMapper.insert(entity);
	}
	/**
	 * TODO 更新楼盘信息
	 * @author harry
	 * <b> 有问题请联系qq:359705093</b>
	 * @param entity
	 * @create 2016年1月12日
	 */
	public void updateBuildings(@MapValid("update-buildings")Map<String, Object> entity){
        if(entity.get("open_date").equals("")) entity.put("open_date", null);
        if(entity.get("deliver_date").equals("")) entity.put("deliver_date", null);
        if(entity.get("cityId").equals("--请选择--")) entity.put("cityId", null);
        if(entity.get("areaId").equals("--请选择--")) entity.put("areaId", null);
		buildingsMapper.update(entity);
	}
	/**
	 * TODO 获取用户列表
	 * @author harry
	 * <b> 有问题请联系qq:359705093</b>
	 * @param filter
	 * @return
	 * @create 2016年1月12日
	 */
	public List<Map<String, Object>> findBuildings(Map<String, Object> filter) {
		return buildingsMapper.find(filter);
	}
	/**
	 * TODO 分页获取楼盘列表
	 * @author harry
	 * <b> 有问题请联系qq:359705093</b>
	 * @param pageRequest
	 * @param filter
	 * @return
	 * @create 2016年1月12日
	 */
	public Page<Map<String, Object>> findBuildingsList(PageRequest pageRequest, Map<String, Object> filter) {
        long total = buildingsMapper.count(filter);
        filter.putAll(pageRequest.getMap());
        List<Map<String, Object>> content = findBuildings(filter);

        return new Page<Map<String, Object>>(pageRequest, content, total);
    }
	/**
	 * TODO 根据id查询楼盘
	 * @author harry
	 * <b> 有问题请联系qq:359705093</b>
	 * @param id
	 * @return
	 * @create 2016年1月12日
	 */
	public Map<String, Object> getBuildings(Long id) { 
		if (null ==buildingsMapper.get(id).get("city_id") || "".equals(buildingsMapper.get(id).get("city_id"))) {
			buildingsMapper.get(id).put("city_id", "wu");
		}
		if (null ==buildingsMapper.get(id).get("area_id") || "".equals(buildingsMapper.get(id).get("area_id"))) {
			buildingsMapper.get(id).put("area_id", "wu");
		}

        return buildingsMapper.get(id);
    }
	/**
	 * TODO 删除楼盘
	 * @author harry
	 * <b> 有问题请联系qq:359705093</b>
	 * @param entity
	 * @create 2016年1月12日
	 */
	public void deleteBuildings(Map<String,Object> entity) {

        if ("admin".equals(entity.get("username"))) {
            throw new ServiceException("这是管理员帐号，不允许删除");
        }

        Long id = VariableUtils.typeCast(entity.get("id"),Long.class);
        buildingsMapper.delete(id);
    }
	 /**
	 * TODO 批量删除楼盘
	 * @author harry
	 * <b> 有问题请联系qq:359705093</b>
	 * @param ids
	 * @create 2016年1月12日
	 */
	public void deleteBuildingsList(List<Long> ids) {
        for(Long id : ids) {
            Map<String, Object> entity = getBuildings(id);
            if (MapUtils.isNotEmpty(entity)) {
            	deleteBuildings(entity);
            }
        }
    }
	public Page<Map<String, Object>> findBuildingsPriceList(PageRequest pageRequest, Map<String, Object> filter,Model model) {
		Long buildingsId=null;
		if(StringUtil.isNotEmptyObject(pageRequest.getMap().get("buildingsId"))){
			buildingsId=Long.parseLong(pageRequest.getMap().get("buildingsId").toString());
		}
		
		Map buildings=buildingsMapper.get(buildingsId);
		model.addAttribute("buildingsName",buildings.get("buildings_name").toString());
		long total = buildingsPriceMapper.count(filter);
        filter.putAll(pageRequest.getMap());
        List<Map<String, Object>> content = buildingsPriceMapper.find(filter);
        return new Page<Map<String, Object>>(pageRequest, content, total);
    }
}
