package base;

import base.basMVP.BaseIPresenter;
import base.basMVP.BaseIView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ResponseUtils;

import java.io.IOException;

public abstract class BaseServlet<T extends BaseIPresenter<V>, V extends BaseIView> extends HttpServlet {

    protected T iPresenter;
    protected HttpServletRequest req;
    protected HttpServletResponse resp;

    @Override
    public void init() throws ServletException {
        iPresenter = setPresenter();
        iPresenter.attachView((V) this);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        if (iPresenter != null) {
            iPresenter.detachView();
            iPresenter = null;
        }
    }

    protected abstract T setPresenter();

    public void error(int code) {
        System.out.println(code);
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    public void success(String result) {
        ResponseUtils.respBack(resp, result);
    }

}
