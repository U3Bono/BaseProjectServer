package iPresenter;

import base.basMVP.BaseIModel;
import base.basMVP.BaseIPresenter;
import entity.UserEntity;
import iModel.RegisterModel;
import servlet.user.RegisterServlet;

import static utils.SignalUtils.USER_PRESENTER_ERROR;

public class RegisterPresenter extends BaseIPresenter<RegisterServlet> {
    RegisterModel registerModel;

    public RegisterPresenter() {
        this.registerModel = new RegisterModel();
    }

    public void doRegister(UserEntity userEntity) {
        if (view == null | userEntity == null)
            return;
        registerModel.register(userEntity, new BaseIModel.ObjectBack() {
            @Override
            public void success(String result) {
                view.success(result);
            }

            @Override
            public void error(int code) {
                view.error(code);
            }
        });
    }
}
