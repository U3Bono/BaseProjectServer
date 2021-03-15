package iPresenter;

import base.basMVP.BaseIModel;
import base.basMVP.BaseIPresenter;
import entity.UserEntity;
import iModel.ModifyModel;
import servlet.user.ModifyServlet;

public class ModifyPresenter extends BaseIPresenter<ModifyServlet> {

    ModifyModel modifyModel;

    public ModifyPresenter() {
        modifyModel = new ModifyModel();
    }

    public void doModify(UserEntity userEntity) {
        if (view == null | userEntity == null)
            return;
        modifyModel.modify(userEntity, new BaseIModel.ObjectBack() {
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
