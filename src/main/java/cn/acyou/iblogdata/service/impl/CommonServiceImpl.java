package cn.acyou.iblogdata.service.impl;

import cn.acyou.iblogdata.dao.BaseMapper;
import cn.acyou.iblogdata.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author youfang
 * @version [1.0.0, 2019-06-24 14:34]
 * @since [司法公证]
 **/
@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private BaseMapper baseMapper;

    @Override
    public Long getSequence(String seqName) {
        Long buildOnlyNumber = baseMapper.getBuildOnlyNumber(seqName);
        if (buildOnlyNumber != null){
            return buildOnlyNumber;
        }
        baseMapper.createSequence(seqName);
        return 1L;
    }
}
