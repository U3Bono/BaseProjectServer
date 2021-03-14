package servlet.user;

import base.BaseServlet;
import base.basMVP.BaseIView;
import entity.UserEntity;
import iPresenter.CancelPresenter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static utils.HttpHelper.requestToEntity;

public class CancelServlet extends BaseServlet<CancelPresenter, CancelServlet> implements BaseIView {

    @Override
    protected CancelPresenter setPresenter() {
        return new CancelPresenter();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity userEntity = new UserEntity();
        requestToEntity(req, userEntity);
        iPresenter.doCancel(userEntity);
    }

    @Override
    public void success(Object obj) {
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
