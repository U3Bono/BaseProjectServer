package iPresenter;

import base.basMVP.BaseIModel;
import base.basMVP.BaseIPresenter;
import entity.UserEntity;
import iModel.DetailModel;
import servlet.user.DetailServlet;

public class DetailPresenter extends BaseIPresenter<DetailServlet> {

    DetailModel detailModel;

    public DetailPresenter() {
        detailModel = new DetailModel();
    }

    public void getDetail(UserEntity userEntity){
        if(view == null | userEntity == null)
            return;
        detailModel.detail(userEntity, new BaseIModel.ObjectBack() {
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
