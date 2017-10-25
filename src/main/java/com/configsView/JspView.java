package com.configsView;

import org.springframework.web.servlet.view.InternalResourceView;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JspView extends InternalResourceView
{
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String dispatcherPath = prepareForRendering(request, response);

        request.setAttribute("partial", dispatcherPath.substring(dispatcherPath.lastIndexOf("views/") + 6));

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/template.jsp");
        rd.include(request, response);
    }
}
