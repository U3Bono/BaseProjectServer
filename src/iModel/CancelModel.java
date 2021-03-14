package iModel;

import base.basMVP.BaseIModel;
import entity.UserEntity;
import utils.DataBaseUtils;

import java.util.HashMap;
import java.util.Map;

import static utils.DBTableUtils.USER_TABLE;
import static utils.FieldUtils.varName;
import static utils.SignalUtils.*;

public class CancelModel {

    public void cancelUser(UserEntity userEntity, final BaseIModel.ObjectBack objectBack) {
        if (userEntity.getUser_id() == 0) {
            objectBack.error(USER_MODEL_ERROR);
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put(varName(userEntity, userEntity.getUser_id()), userEntity.getUser_id());
        if (DataBaseUtils.delete(USER_TABLE, userEntity, map) == DA_UPDATE_SUCCESS) {
            objectBack.success(USER_MODEL_SUCCESS);
        } else {
            objectBack.error(USER_MODEL_ERROR);
        }
    }
}
