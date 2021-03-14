package iPresenter;

import base.basMVP.BaseIModel;
import base.basMVP.BaseIPresenter;
import entity.UserEntity;
import iModel.CancelModel;
import servlet.user.CancelServlet;

public class CancelPresenter extends BaseIPresenter<CancelServlet> {

    CancelModel cancelModel;

    public CancelPresenter() {
        cancelModel = new CancelModel();
    }

    public void doCancel(UserEntity userEntity) {
        if (view == null | userEntity == null)
            return;
        cancelModel.cancelUser(userEntity, new BaseIModel.ObjectBack() {
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
