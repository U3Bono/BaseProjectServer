package iModel;

import base.basMVP.BaseIModel;
import entity.UserEntity;
import utils.*;

import java.util.HashMap;
import java.util.Map;

public class DetailModel implements BaseIModel {

    public void detail(final UserEntity userEntity, ObjectBack objectBack) {
        if (userEntity.getUser_id() == 0) {
            objectBack.error(SignalUtils.USER_MODEL_ERROR);
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put(FieldUtils.varName(userEntity, userEntity.getUser_id()), userEntity.getUser_id());
        if (DataBaseUtils.search(DBTableUtils.USER_TABLE, userEntity, map) == SignalUtils.DA_SEARCH_SUCCESS) {
            Map<String, Object> result = FieldUtils.getEntity(userEntity);
            result.remove(FieldUtils.varName(userEntity, userEntity.getUser_password()));//除去密码
            objectBack.success(JsonUtils.toJson(result));
        } else {
            objectBack.error(SignalUtils.USER_MODEL_ERROR);
        }
    }
}
