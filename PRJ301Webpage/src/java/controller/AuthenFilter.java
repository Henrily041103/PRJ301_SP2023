/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.account.AccountModel;
import utils.StringUtil;

/**
 *
 * @author Thanh
 */
public class AuthenFilter implements Filter {

    private static List<String> NON_AUTHEN_FUNCTIONS;
    private static final boolean DEBUG = true;
//    private static final String LOGIN_PAGE = "/login/login.jsp";
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public AuthenFilter() {
        NON_AUTHEN_FUNCTIONS = new ArrayList<>();
        NON_AUTHEN_FUNCTIONS.add("/login/login.do");
        NON_AUTHEN_FUNCTIONS.add("/login/register.do");
        NON_AUTHEN_FUNCTIONS.add("/shop/index.do");
        NON_AUTHEN_FUNCTIONS.add("/cart/add.do");
        NON_AUTHEN_FUNCTIONS.add("/cart/show.do");
        NON_AUTHEN_FUNCTIONS.add("/cart/back.do");
//        NON_AUTHEN_FUNCTIONS.add("/index.jsp");
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (DEBUG) {
            log("AuthenFilter:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (DEBUG) {
            log("AuthenFilter:DoAfterProcessing");
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;

            String url = req.getServletPath();
            if (url.equals("/index.jsp")) {
                url = "/shop/index.do";
            }
            req.setAttribute("url", url);

            if (NON_AUTHEN_FUNCTIONS.contains(url)) {
                chain.doFilter(request, response);
//                System.out.println("filtered");
                return;
            }

            String password = (String) req.getAttribute("password");
            if (password != null) {
                req.setAttribute("password", StringUtil.encryptPassword(password));
            }

            HttpSession session = req.getSession();
            AccountModel user = (AccountModel) session.getAttribute("current-user");

            //phanquyen
            //user dang nhap voi quyen ad va truy cap tai nguyen duoc cho phep cua role ROLE_AD
            if (user == null || !user.checkRole(url)) {
                req.setAttribute("rejected", true);
            }

            chain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            log(e.getMessage());
        }

    }

    /**
     * Return the filter configuration object for this filter.
     *
     * @return
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (DEBUG) {
                log("AuthenFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthenFilter()");
        }
        StringBuilder sb = new StringBuilder("AuthenFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                try (PrintStream ps = new PrintStream(response.getOutputStream()); PrintWriter pw = new PrintWriter(ps)) {
                    pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                    // PENDING! Localize this for next official release
                    pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                    pw.print(stackTrace);
                    pw.print("</pre></body>\n</html>"); //NOI18N
                }
                response.getOutputStream().close();
            } catch (IOException ex) {
            }
        } else {
            try {
                try (PrintStream ps = new PrintStream(response.getOutputStream())) {
                    t.printStackTrace(ps);
                }
                response.getOutputStream().close();
            } catch (IOException ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (IOException ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
