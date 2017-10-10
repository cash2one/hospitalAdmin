package com.jumper.hospital.dao;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.CommonVideo;

public interface CommonVideoDao extends BaseDao<CommonVideo, Integer> {

	public Page<CommonVideo> getCommonVideo(Page<CommonVideo> page);

	public CommonVideo getVideoByVideoName(String videoName, Integer videoId);

}
