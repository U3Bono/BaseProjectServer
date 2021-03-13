package iModel;

import base.basMVP.BaseIModel;
import entity.UserEntity;
import utils.DataBaseUtils;

import java.util.Map;

import static utils.DBTableUtils.USER_TABLE;
import static utils.FieldUtils.varName;
import static utils.SignalUtils.USER_MODEL_ERROR;
import static utils.SignalUtils.USER_MODEL_SUCCESS;

public class LoginModel {

    public void login(final UserEntity userEntity, final BaseIModel.ObjectBack objectBack) {
        if (userEntity.getUser_password() == null | userEntity.getUser_account() == null) {
            objectBack.error(USER_MODEL_ERROR);
            return;
        }

        DataBaseUtils.search(USER_TABLE, userEntity, userEntity.getUser_account(), new DataBaseUtils.SearchBack() {
            @Override
            public void success(Map<String, Object> result) {
                String searchAccount = String.valueOf(result.get(varName(userEntity, userEntity.getUser_account())));
                String searchPassword = String.valueOf(result.get(varName(userEntity, userEntity.getUser_password())));
                if (!searchAccount.equals(userEntity.getUser_account()) | !searchPassword.equals(userEntity.getUser_password())) {
                    objectBack.error(USER_MODEL_ERROR);
                }
                objectBack.success(USER_MODEL_SUCCESS);
            }

            @Override
            public void failed(int code) {
                objectBack.error(USER_MODEL_ERROR);
            }
        });

    }


}
