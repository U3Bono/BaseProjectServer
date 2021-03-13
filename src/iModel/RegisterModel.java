package iModel;

import base.basMVP.BaseIModel;
import entity.UserEntity;
import utils.DataBaseUtils;

import static utils.DBTableUtils.USER_TABLE;
import static utils.InfoJudgeUtils.isPhone;
import static utils.RandomUtils.getTimeRandom;
import static utils.SignalUtils.*;

public class RegisterModel {

    public void register(UserEntity userEntity, BaseIModel.ObjectBack objectBack) {
        String user_account = userEntity.getUser_account();
        String user_password = userEntity.getUser_password();
        String user_phone = userEntity.getUser_phone();
        userEntity.setUser_id((int) getTimeRandom());
        if (user_account == null | user_password == null | user_phone == null | !isPhone(user_phone)) {
            objectBack.error(USER_MODEL_ERROR);
            return;
        }
        if (DataBaseUtils.add(USER_TABLE, userEntity) == DA_UPDATE_SUCCESS) {
            objectBack.success(USER_MODEL_SUCCESS);
        }
    }

}
