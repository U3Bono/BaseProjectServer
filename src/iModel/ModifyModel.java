package iModel;

import base.basMVP.BaseIModel;
import entity.UserEntity;
import utils.DataBaseUtils;

import java.util.HashMap;
import java.util.Map;

import static utils.DBTableUtils.USER_TABLE;
import static utils.FieldUtils.varName;
import static utils.SignalUtils.*;

public class ModifyModel {

    public void modify(UserEntity userEntity, BaseIModel.ObjectBack objectBack) {
        if (userEntity.getUser_id() == 1 | (userEntity.getUser_password() == null & userEntity.getUser_account() == null & userEntity.getUser_phone() == null)) {
            objectBack.error(USER_MODEL_ERROR);
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put(varName(userEntity, userEntity.getUser_id()), userEntity.getUser_id());
        if (DataBaseUtils.modify(USER_TABLE, userEntity, map) == DA_UPDATE_SUCCESS) {
            objectBack.error(USER_MODEL_SUCCESS);
        } else {
            objectBack.error(USER_MODEL_ERROR);
        }
    }
}
