package javaweb中文乱码;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by don on 16/5/5.
 */
public class PageEncodingFilter implements Filter {

    private String encoding = "UTF-8";
    protected FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;
        //本过滤器默认编码是UTF-8，但也可以在web.xml配置文件里设置自己需要的编码
        if (filterConfig.getInitParameter("encoding") != null)
            encoding = filterConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest srequset, ServletResponse sresponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) srequset;
        String methodLowerCase = request.getMethod().toLowerCase();
        if("get".equals(methodLowerCase)){

            srequset = new MyRequestWrapper(srequset, encoding);
        }else if("post".equals(methodLowerCase)) {

            srequset.setCharacterEncoding(encoding);
        }

        filterChain.doFilter(srequset, sresponse);
    }

    public void destroy() {

        this.encoding = null;
    }
}

class MyRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest request;
    private String encoding;

    public MyRequestWrapper(ServletRequest request, String encoding) {

        super((HttpServletRequest)request);

        this.request = (HttpServletRequest)request;
        this.encoding = encoding;
    }

    @Override
    public String getParameter(String name) {

        String result = this.request.getParameter(name);

        if (result != null) {

            try {

                String encode = "ISO-8859-1";
                if(encode != null && !(this.encoding.toLowerCase().equals(encode.toLowerCase())))
                    result = new String(result.getBytes(encode), this.encoding);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}