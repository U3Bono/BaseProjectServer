package iModel;

import base.basMVP.BaseIModel;
import entity.UserEntity;
import utils.DBTableUtils;
import utils.DataBaseUtils;
import utils.FieldUtils;
import utils.SignalUtils;

import java.util.HashMap;
import java.util.Map;

public class ModifyModel implements BaseIModel {

    public void modify(UserEntity userEntity, ObjectBack objectBack) {
        if (userEntity.getUser_id() == 1 | (userEntity.getUser_password() == null & userEntity.getUser_account() == null & userEntity.getUser_phone() == null & userEntity.getUser_ic() == null)) {
            objectBack.error(SignalUtils.USER_MODEL_ERROR);
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put(FieldUtils.varName(userEntity, userEntity.getUser_id()), userEntity.getUser_id());
        if (DataBaseUtils.modify(DBTableUtils.USER_TABLE, userEntity, map) == SignalUtils.DA_UPDATE_SUCCESS) {
            objectBack.success(String.valueOf(SignalUtils.USER_MODEL_SUCCESS));
        } else {
            objectBack.error(SignalUtils.USER_MODEL_ERROR);
        }
    }
}
