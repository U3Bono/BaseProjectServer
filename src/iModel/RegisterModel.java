package iModel;

import base.basMVP.BaseIModel;
import entity.UserEntity;
import utils.DataBaseUtils;

import java.util.HashMap;
import java.util.Map;

import static utils.DBTableUtils.USER_TABLE;
import static utils.FieldUtils.varName;
import static utils.InfoJudgeUtils.isPhone;
import static utils.RandomUtils.getTimeRandom;
import static utils.SignalUtils.*;

public class RegisterModel {

    public void register(final UserEntity userEntity, final BaseIModel.ObjectBack objectBack) {
        if (userEntity == null) {
            objectBack.error(USER_MODEL_ERROR);
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put(varName(userEntity, userEntity.getUser_phone()), userEntity.getUser_phone());
        if (DataBaseUtils.search(USER_TABLE, userEntity, map) == DA_SEARCH_SUCCESS) {
            objectBack.error(USER_MODEL_ERROR);
        } else {
            String user_account = userEntity.getUser_account();
            String user_password = userEntity.getUser_password();
            String user_phone = userEntity.getUser_phone();
            userEntity.setUser_id((int) getTimeRandom());
            if (user_account == null | user_password == null | user_phone == null | !isPhone(user_phone)) {
                objectBack.error(USER_MODEL_ERROR);
                return;
            }
            if (DataBaseUtils.add(USER_TABLE, userEntity) == DA_UPDATE_SUCCESS) {
                Map<String, Object> result = new HashMap<>();
                result.put(varName(userEntity, userEntity.getUser_id()), userEntity.getUser_id());
                objectBack.success(result);
            }
        }

    }

}
