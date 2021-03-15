package iModel;

import base.basMVP.BaseIModel;
import entity.UserEntity;
import utils.*;

import java.util.HashMap;
import java.util.Map;

public class RegisterModel implements BaseIModel {

    public void register(final UserEntity userEntity, ObjectBack objectBack) {
        if (userEntity == null) {
            objectBack.error(SignalUtils.USER_MODEL_ERROR);
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put(FieldUtils.varName(userEntity, userEntity.getUser_phone()), userEntity.getUser_phone());
        if (DataBaseUtils.search(DBTableUtils.USER_TABLE, userEntity, map) == SignalUtils.DA_SEARCH_SUCCESS) {
            objectBack.error(SignalUtils.USER_MODEL_ERROR);
        } else {
            String user_password = userEntity.getUser_password();
            String user_phone = userEntity.getUser_phone();
            userEntity.setUser_id((int) RandomUtils.getTimeRandom());
            if (user_password == null | user_phone == null | !InfoJudgeUtils.isPhone(user_phone)) {
                objectBack.error(SignalUtils.USER_MODEL_ERROR);
                return;
            }
            if (DataBaseUtils.add(DBTableUtils.USER_TABLE, userEntity) == SignalUtils.DA_UPDATE_SUCCESS) {
                Map<String, Object> result = new HashMap<>();
                result.put(FieldUtils.varName(userEntity, userEntity.getUser_id()), userEntity.getUser_id());
                objectBack.success(JsonUtils.toJson(result));
            }
        }

    }

}
