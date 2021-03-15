package iModel;

import base.basMVP.BaseIModel;
import entity.UserEntity;
import utils.*;

import java.util.HashMap;
import java.util.Map;

public class LoginModel implements BaseIModel {

    public void login(final UserEntity userEntity, ObjectBack objectBack) {
        String searchPhone = userEntity.getUser_phone();
        String searchPassword = userEntity.getUser_password();
        if (searchPhone == null | searchPassword == null) {
            objectBack.error(SignalUtils.USER_MODEL_ERROR);
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put(FieldUtils.varName(userEntity, userEntity.getUser_phone()), userEntity.getUser_phone());
        if (DataBaseUtils.search(DBTableUtils.USER_TABLE, userEntity, map) == SignalUtils.DA_SEARCH_SUCCESS) {
            Map<String, Object> result = new HashMap<>();
            result.put(FieldUtils.varName(userEntity, userEntity.getUser_id()), userEntity.getUser_id());
            objectBack.success(JsonUtils.toJson(result));
        } else {
            objectBack.error(SignalUtils.USER_MODEL_ERROR);
        }

    }

}
