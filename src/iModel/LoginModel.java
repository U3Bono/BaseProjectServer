package iModel;

import base.basMVP.BaseIModel;
import entity.LoginEntity;
import utils.DataBaseUtils;

import java.util.Map;

import static utils.DBTableUtils.USER_TABLE;
import static utils.FieldUtils.varName;
import static utils.SignalUtils.USER_LOGIN_ERROR;
import static utils.SignalUtils.USER_LOGIN_SUCCESS;

public class LoginModel {

    public void login(final LoginEntity loginEntity, final BaseIModel.ObjectBack objectBack) {
        if (loginEntity.getUser_password() == null | loginEntity.getUser_account() == null) {
            objectBack.error(USER_LOGIN_ERROR);
            return;
        }

        DataBaseUtils.search(USER_TABLE, loginEntity, loginEntity.getUser_account(), new DataBaseUtils.SearchBack() {
            @Override
            public void success(Map<String, Object> result) {
                String searchAccount = String.valueOf(result.get(varName(loginEntity, loginEntity.getUser_account())));
                String searchPassword = String.valueOf(result.get(varName(loginEntity, loginEntity.getUser_password())));
                if (!searchAccount.equals(loginEntity.getUser_account()) | !searchPassword.equals(loginEntity.getUser_password())) {
                    objectBack.error(USER_LOGIN_ERROR);
                }
                objectBack.success(USER_LOGIN_SUCCESS);
            }

            @Override
            public void failed(int code) {
                objectBack.error(USER_LOGIN_ERROR);
            }
        });

    }


}
