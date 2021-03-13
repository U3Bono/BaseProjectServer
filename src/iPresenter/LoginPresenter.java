package iPresenter;

import base.basMVP.BaseIModel;
import base.basMVP.BaseIPresenter;
import entity.UserEntity;
import iModel.LoginModel;
import servlet.user.LoginServlet;

public class LoginPresenter extends BaseIPresenter<LoginServlet> {

    LoginModel loginModel;

    public LoginPresenter() {
        loginModel = new LoginModel();
    }

    public void doLogin(UserEntity userEntity) {
        if (view == null || loginModel == null)
            return;
        loginModel.login(userEntity, new BaseIModel.ObjectBack() {
            @Override
            public void success(Object obj) {
                view.success(obj);
            }

            @Override
            public void error(int code) {
                view.error(code);
            }
        });
    }

}
