package servlet.user;

import base.BaseServlet;
import base.basMVP.BaseIView;
import entity.UserEntity;
import iPresenter.ModifyPresenter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.HttpHelper;

import java.io.IOException;

public class ModifyServlet extends BaseServlet<ModifyPresenter, ModifyServlet> implements BaseIView {
    @Override
    protected ModifyPresenter setPresenter() {
        return new ModifyPresenter();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity userEntity = new UserEntity();
        HttpHelper.requestToEntity(req, userEntity);
        iPresenter.doModify(userEntity);
    }

}
