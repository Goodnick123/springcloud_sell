package com.immoc.user.utils;

import com.immoc.user.VO.ResultVO;
import com.immoc.user.enums.ResultEnum;

public class ResultVoUtil {
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
    public static ResultVO success() {
        ResultVO resultVo = new ResultVO();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        return resultVo;
    }
    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVo = new ResultVO();
        resultVo.setCode(resultEnum.getCode());
        resultVo.setMsg(resultEnum.getMessage());
        return resultVo;
    }
}
