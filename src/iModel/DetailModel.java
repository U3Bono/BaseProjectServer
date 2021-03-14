package iModel;

import base.basMVP.BaseIModel;
import entity.UserEntity;
import utils.DataBaseUtils;

import java.util.HashMap;
import java.util.Map;

import static utils.DBTableUtils.USER_TABLE;
import static utils.FieldUtils.getEntity;
import static utils.FieldUtils.varName;
import static utils.SignalUtils.DA_SEARCH_SUCCESS;
import static utils.SignalUtils.USER_MODEL_ERROR;

public class DetailModel {

    public void detail(final UserEntity userEntity, final BaseIModel.ObjectBack objectBack) {
        if (userEntity.getUser_id() == 0) {
            objectBack.error(USER_MODEL_ERROR);
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put(varName(userEntity, userEntity.getUser_id()), userEntity.getUser_id());
        if (DataBaseUtils.search(USER_TABLE, userEntity, map) == DA_SEARCH_SUCCESS) {
            Map<String, Object> result = getEntity(userEntity);
            result.remove(varName(userEntity, userEntity.getUser_password()));//除去密码
            objectBack.success(result);
        } else {
            objectBack.error(USER_MODEL_ERROR);
        }
    }
}
