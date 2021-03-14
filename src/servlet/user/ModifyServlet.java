package servlet.user;

import base.BaseServlet;
import base.basMVP.BaseIView;
import entity.UserEntity;
import iPresenter.ModifyPresenter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static utils.HttpHelper.requestToEntity;

public class ModifyServlet extends BaseServlet<ModifyPresenter, ModifyServlet> implements BaseIView {
    @Override
    protected ModifyPresenter setPresenter() {
        return new ModifyPresenter();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity userEntity = new UserEntity();
        requestToEntity(req, userEntity);
        iPresenter.doModify(userEntity);
    }

    @Override
    public void success(Object obj) {
        resp.setStatus(HttpServletResponse.SC_OK);
    }

}
