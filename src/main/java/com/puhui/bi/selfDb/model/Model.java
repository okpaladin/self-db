package com.puhui.bi.selfDb.model;

import java.io.Serializable;
import java.util.Map;

/**
 * 功能描述：
 * User: NanJiang
 * Date: 2016/9/5
 */
public abstract class Model<M extends Model> implements Serializable{

    private static final long serialVersionUID = -990334519494532591L;

    private Map<String, Object> attrs ;

}
